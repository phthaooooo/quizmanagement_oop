package View;

import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentUI extends JFrame implements ActionListener {
    // Các nút trong thanh điều hướng
    JMenu takeQuiz, logOut;
    private static Student student;

    public StudentUI(Student student) {
        this.student = student;
        // Thiết lập JFrame
        setTitle("STUDENT");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // icon frame
        ImageIcon iconStudent = new ImageIcon("Student.png");
        setIconImage(iconStudent.getImage());

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        takeQuiz = new JMenu("Take Quiz");
        ImageIcon iconTakeQuiz = new ImageIcon("quiz.png");
        takeQuiz.setIcon(iconTakeQuiz);
        menubar.add(takeQuiz);

        JMenuItem mcQuestion = new JMenuItem("Multiple Choice Question");
        mcQuestion.addActionListener(this);
        ImageIcon icMCQuestion = new ImageIcon("option.png");
        mcQuestion.setIcon(icMCQuestion);
        takeQuiz.add(mcQuestion);

        JMenuItem answerFillingQuestion = new JMenuItem("Answer Filling Question");
        answerFillingQuestion.addActionListener(this);
        ImageIcon icAnswerFillingQuestion = new ImageIcon("answer.png");
        answerFillingQuestion.setIcon(icAnswerFillingQuestion);
        takeQuiz.add(answerFillingQuestion);


        logOut = new JMenu("Log Out");
        ImageIcon iconLogOut = new ImageIcon("logout.png");
        logOut.setIcon(iconLogOut);
        menubar.add(logOut);

        add(menubar, BorderLayout.NORTH);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentUI(student);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();

        if (text.equals("Multiple Choice Question")) {
            new QuizSelectionScreen(student);
        } else if (text.equals("Answer Filling Question")) {
            new fillQuizSelectionScreen(student);
        }
    }
}
