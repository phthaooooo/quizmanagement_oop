package View;

import DAO.ConnectionDatabase;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewUserUI_Admin extends JFrame implements ActionListener{

    JTable table;
    Choice cUsername;
    JButton search, update, back;
    
    ViewUserUI_Admin() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchlbl = new JLabel("Search by Username");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);
        
        cUsername = new Choice();
        cUsername.setBounds(180, 20, 150, 20);
        add(cUsername);
        
        try {
            ConnectionDatabase c = new ConnectionDatabase();
            ResultSet rs = c.s.executeQuery("select * from users");
            while(rs.next()) {
                cUsername.add(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        table = new JTable();
        
        try {
            ConnectionDatabase c = new ConnectionDatabase();
            ResultSet rs = c.s.executeQuery("select * from users");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
        update = new JButton("Update");
        update.setBounds(120, 70, 80, 20);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBounds(220, 70, 80, 20);
        back.addActionListener(this);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from users where username = '"+cUsername.getSelectedItem()+"'";
            try {
                ConnectionDatabase c = new ConnectionDatabase();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateUserUI_Admin(cUsername.getSelectedItem());
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewUserUI_Admin();
    }
}