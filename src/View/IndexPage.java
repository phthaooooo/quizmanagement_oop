package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexPage extends JFrame implements ActionListener {

    JButton login, register, exit;

    IndexPage() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        // heading name
        JLabel heading = new JLabel("LetsQuiz");
        heading.setBounds(380, 60, 300, 45);
        heading.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        // Button
        login = new JButton("Login");
        login.setBounds(400, 150, 120, 30);
        login.setBackground(new Color(30, 144, 254));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Arial", Font.BOLD, 16)); // Chọn font Arial, kiểu chữ thường, cỡ 16
        login.addActionListener(this);
        add(login);


        register = new JButton("Register");
        register.setBounds(400, 200, 120, 30);
        register.setBackground(new Color(30, 144, 254));
        register.setForeground(Color.WHITE);
        register.setFont(new Font("Arial", Font.BOLD, 16)); // Chọn font Arial, kiểu chữ thường, cỡ 16
        register.addActionListener(this);
        add(register);

        exit = new JButton("Exit");
        exit.setBounds(400, 250, 120, 30);
        exit.setBackground(new Color(30, 144, 254));
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Arial", Font.BOLD, 16)); // Chọn font Arial, kiểu chữ thường, cỡ 16
        exit.addActionListener(this);
        add(exit);

        setSize(1000, 500);
        setLocation(300, 150); // center
        setVisible(true);
    }
    public static void main(String[] args) {
        // Chạy chương trình
        new IndexPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login) {
            new Login();
        }
        else if (e.getSource() == register) {
            new Register();
        } else if (e.getSource() == exit) {
            setVisible(false);
        }
    }
}
