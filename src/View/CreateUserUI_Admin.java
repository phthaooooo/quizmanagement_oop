package View;

import DAO.ConnectionDatabase;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class CreateUserUI_Admin extends JFrame implements ActionListener{
    JTextField tfusername, tfpassword;
    JComboBox cbroles;
    JButton add, back;
    
    CreateUserUI_Admin() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Heading
        JLabel heading = new JLabel("Create User");
        heading.setBounds(220, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        // Username
        JLabel labelusername = new JLabel("Username");
        labelusername.setBounds(50, 100, 200, 30);
        labelusername.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(250, 100, 200, 30);
        add(tfusername);
        
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

        // Button
        add = new JButton("Create User");
        add.setBounds(100, 280, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(300, 280, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
        
        setSize(600, 450);
        setLocation(350, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String username = tfusername.getText();
            String password = tfpassword.getText();
            String role = (String) cbroles.getSelectedItem();
            
            try {
                ConnectionDatabase conn = new ConnectionDatabase();
                String query = "INSERT INTO users (username, password, role) " +
                "VALUES ('" + username + "', '" + password + "', '" + role + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "User added successfully");
                setVisible(false);
                new AdminUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CreateUserUI_Admin();
    }
}