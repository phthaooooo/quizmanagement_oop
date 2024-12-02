package View;

import DAO.ConnectionDatabase;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class RemoveUserUI extends JFrame implements ActionListener {
    
    Choice cUsername;
    JButton delete, back;
    
    RemoveUserUI() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel labelUserId = new JLabel("UserName");
        labelUserId.setBounds(50, 50, 100, 30);
        labelUserId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelUserId);
        
        cUsername = new Choice();
        cUsername.setBounds(200, 50, 150, 30);
        cUsername.setFont(new Font("Arial", Font.BOLD, 18));
        add(cUsername);
        
        try {
            ConnectionDatabase c = new ConnectionDatabase();
            String query = "select * from users";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                cUsername.add(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Role
        JLabel labelrole = new JLabel("Role");
        labelrole.setBounds(50, 100, 100, 30);
        labelrole.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelrole);
        
        JLabel lblrole = new JLabel();
        lblrole.setBounds(200, 100, 100, 30);
        lblrole.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(lblrole);

        try {
            ConnectionDatabase c = new ConnectionDatabase();
            String query = "select * from users where username = '"+cUsername.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                lblrole.setText(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cUsername.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    ConnectionDatabase c = new ConnectionDatabase();
                    String query = "select * from users where username = '"+cUsername.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        lblrole.setText(rs.getString("role"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        delete = new JButton("Delete");
        delete.setBounds(60, 180, 100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);
        
        back = new JButton("Back");
        back.setBounds(200, 180, 100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon("D:\\image\\delete.png");
        Image i2 = i1.getImage().getScaledInstance(430, 230, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(250, -50, 600, 400);
        add(image);
        
        setSize(800, 400);
        setLocation(300, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                ConnectionDatabase c = new ConnectionDatabase();
                String query = "delete from users where username = '"+cUsername.getSelectedItem()+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "User Deleted Sucessfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new RemoveUserUI();
    }
}