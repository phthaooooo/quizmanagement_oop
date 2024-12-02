package View;

import DAO.ConnectionDatabase;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateUserUI extends JFrame implements ActionListener{
    
    JTextField tfpassword;
    JComboBox cbroles;
    JButton add, back;
    String username;
    
    public UpdateUserUI(String username) {
        this.username = username;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(180, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        // Username
        JLabel labelusername = new JLabel("Username");
        labelusername.setBounds(50, 100, 200, 30);
        labelusername.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelusername);
        
        JLabel lblusername = new JLabel();
        lblusername.setBounds(250, 100, 200, 30);
        add(lblusername);
        
        // Password
        JLabel labelpassword = new JLabel("Password");
        labelpassword.setBounds(50, 150, 200, 30);
        labelpassword.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelpassword);
        
        tfpassword = new JTextField();
        tfpassword.setBounds(250, 150, 200, 30);
        add(tfpassword);
               
        // Role
        JLabel labelrole = new JLabel("Role");
        labelrole.setBounds(50, 200, 200, 30);
        labelrole.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelrole);
        
        String roles[] = {"Admin", "Teacher", "Student"};
        cbroles = new JComboBox(roles);
        cbroles.setBackground(Color.WHITE);
        cbroles.setBounds(250, 200, 200, 30);
        add(cbroles); 
        
        try {
            ConnectionDatabase c = new ConnectionDatabase();
            String query = "select * from users where username = '"+username+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                lblusername.setText(rs.getString("username"));
                tfpassword.setText(rs.getString("password"));
                cbroles.setSelectedItem(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        add = new JButton("Update Details");
        add.setBounds(130, 300, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(320, 300, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
        
        setSize(650, 550);
        setLocation(350, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String password = tfpassword.getText();
            String role = (String) cbroles.getSelectedItem();
            
            try {
                ConnectionDatabase conn = new ConnectionDatabase();
                String query = "update users set password = '"+password+"', role = '"+role+"' where username = '"+username+"'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateUserUI("");
    }
}