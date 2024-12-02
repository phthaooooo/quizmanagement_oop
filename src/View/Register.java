package View;

import DAO.UserQuery;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends JFrame implements ActionListener {
    private static JButton back, next;
    private static JTextField tfusername, tfrole, tfpassword, tfreEnter;
    private static JComboBox<String> comboBox;
    Register() {
        getContentPane().setBackground(new Color(0, 51, 102));
        setLayout(null);
        // heading name
        JLabel heading = new JLabel("REGISTER");
        heading.setBounds(380, 60, 300, 45);
        heading.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        //username
        JLabel username = new JLabel("username");
        username.setBounds(250, 150, 300, 20);
        username.setFont(new Font("Arial", Font.BOLD, 18));
        username.setForeground(new Color(30, 144, 254));
        add(username);

        tfusername = new JTextField();
        tfusername.setBounds(350, 150, 300, 25);
        tfusername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfusername);

        //role

        JLabel role = new JLabel("role");
        role.setBounds(250, 200, 300, 20);
        role.setFont(new Font("Arial", Font.BOLD, 18));
        role.setForeground(new Color(30, 144, 254));
        add(role);

        /*tfrole = new JTextField();
        tfrole.setBounds(350, 200, 300, 25);
        tfrole.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfrole);*/
        String[] options = {"Teacher", "Student"};

        // Tạo JComboBox từ mảng lựa chọn
        comboBox = new JComboBox<>(options);

        // Tùy chọn kiểu hiển thị
        comboBox.setPreferredSize(new Dimension(150, 30));  // Kích thước của combo box
        comboBox.setBounds(350, 200, 300, 25);
        add(comboBox);

        // password
        JLabel pw = new JLabel("password");
        pw.setBounds(250, 250, 300, 20);
        pw.setFont(new Font("Arial", Font.BOLD, 18));
        pw.setForeground(new Color(30, 144, 254));
        add(pw);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(350, 250, 300, 25);
        tfpassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfpassword);

        // re-enter pw
        JLabel repw = new JLabel("<html>re-enter<br>password</html>");
        repw.setBounds(250, 290, 300, 50);
        repw.setFont(new Font("Arial", Font.BOLD, 18));
        repw.setForeground(new Color(30, 144, 254));
        add(repw);

        tfreEnter = new JPasswordField();
        tfreEnter.setBounds(350, 290, 300, 25);
        tfreEnter.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfreEnter);
        // Button
        back = new JButton("Back");
        back.setBounds(200, 350, 120, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 16)); // Chọn font Arial, kiểu chữ thường, cỡ 16
        back.addActionListener(this);
        add(back);


        next = new JButton("Next");
        next.setBounds(600, 350, 120, 30);
        next.setBackground(new Color(30, 144, 254));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Arial", Font.BOLD, 16)); // Chọn font Arial, kiểu chữ thường, cỡ 16
        next.addActionListener(this);
        add(next);


        setSize(1000, 500);
        setLocation(300, 150); // center
        setVisible(true);
    }

    public static void main(String[] args) {
        new Register();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            String username = tfusername.getText();
            String password = tfreEnter.getText();
            String role = (String) comboBox.getSelectedItem();
            if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please filled the blank", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (username.equals("admin")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cannot create an account with this UserID. Please use a different UserID!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (verifyAccountNotExist(username)) {
                var user = new User(username, password, role);
                var isSuccess = UserQuery.insert(user);
                if (isSuccess) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Sign up successfully with your role",
                            "Notification",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    this.dispose();
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to sign up",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                resetAll();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "UserID đã tồn tại, thử lại với UserID khác!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
        private boolean verifyAccountNotExist(String username){
            var user = UserQuery.selectByUsername(username);
            return user == null;
        }
        private void resetAll () {
            tfusername.setText("");
            tfpassword.setText("");

        }
    }

