package View;

import Model.Teacher;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TeacherUI extends JFrame implements ActionListener {
    // Các nút trong thanh điều hướng
    JMenu newQuiz, updateQuiz, allQuiz, stuRes, logOut;
    JTextField searchQuiz;
    JButton search;
    private static Teacher teacher;
    public TeacherUI(Teacher teacher) {
        this.teacher = teacher;
        // Thiết lập JFrame
        setTitle("TEACHER");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        // icon frame
        ImageIcon iconTeacher = new ImageIcon("teacher.png");
        setIconImage(iconTeacher.getImage());

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        newQuiz = new JMenu("New Quiz");
        ImageIcon icNewQuiz = new ImageIcon("add.png");
        newQuiz.setIcon(icNewQuiz);
        menubar.add(newQuiz);

        JMenuItem mc = new JMenuItem("Multiple Choice");
        mc.addActionListener(this);
        ImageIcon icMC = new ImageIcon("option.png");
        mc.setIcon(icMC);
        newQuiz.add(mc);

        JMenuItem answerFilling = new JMenuItem("Answer Filling");
        answerFilling.addActionListener(this);
        ImageIcon icAnswerFilling = new ImageIcon("answer.png");
        answerFilling.setIcon(icAnswerFilling);
        newQuiz.add(answerFilling);

        allQuiz = new JMenu("All Quiz");
        ImageIcon iconAllQuiz = new ImageIcon("grid.png");
        allQuiz.setIcon(iconAllQuiz);
        menubar.add(allQuiz);
        JMenuItem allquiz = new JMenuItem("All Quiz Created");
        allquiz.setIcon(iconAllQuiz);
        allquiz.addActionListener(this);
        allQuiz.add(allquiz);

        stuRes = new JMenu("Student Result");
        ImageIcon iconStuRes = new ImageIcon("medical-result.png");
        stuRes.setIcon(iconStuRes);
        menubar.add(stuRes);
        JMenuItem stuResMCItem = new JMenuItem("Multiple Choice Quiz");
        stuRes.setIcon(iconStuRes);
        stuResMCItem.addActionListener(this);
        stuRes.add(stuResMCItem);
        JMenuItem stuResASItem = new JMenuItem("Answer Filling Quiz");
        stuRes.setIcon(iconStuRes);
        stuResASItem.addActionListener(this);
        stuRes.add(stuResASItem);

        logOut = new JMenu("Log Out");
        ImageIcon iconLogOut = new ImageIcon("logout.png");
        logOut.setIcon(iconLogOut);
        menubar.add(logOut);
        JMenuItem logOutItem = new JMenuItem("Log Out");
        logOutItem.setIcon(iconLogOut);
        logOutItem.addActionListener(this);
        logOut.add(logOutItem);  // Thêm JMenuItem vào JMenu


        JLabel s = new JLabel("SEARCH QUIZ");
        s.setBounds(200, 150, 200, 20);
        add(s);
        searchQuiz = new JTextField();
        searchQuiz.setBounds(350, 150, 200, 20);
        ImageIcon icSearch = new ImageIcon("search-results_5358562.png");
        s.setIcon(icSearch);
        add(searchQuiz);
        search = new JButton("Search");
        search.setBounds(600, 150, 100, 20);
        search.setBackground(new Color(0, 51, 102));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Arial", Font.BOLD, 11)); // Chọn font Arial, kiểu chữ thường, cỡ 16
        search.addActionListener(this);
        add(search);

        add(menubar, BorderLayout.NORTH);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TeacherUI(teacher);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String text = e.getActionCommand();

        if(text.equals("Multiple Choice")) {
            new createQuizUI(text);
        } else if (text.equals("Answer Filling")) {
            new createQuizUI(text);
        } else if (text.equals("Multiple Choice Quiz")) {
            new ResultQuizofAS();
        } else if (text.equals("Answer Filling Quiz")) {
            new ResultQuizofAS();
        } else if (text.equals("All Quiz Created")) {
            //new TeacherUI(teacher).setVisible(true);
            new AllQuiz();
        }
    }
}

