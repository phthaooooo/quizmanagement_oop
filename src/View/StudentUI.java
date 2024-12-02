package View;

import DAO.QuizQuery;
import Model.Quiz;
import Model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentUI extends JFrame implements ActionListener {
    // Các nút trong thanh điều hướng
    JMenu takeQuiz, logOut;
    private static Student student;
    DefaultTableModel model;
    public StudentUI(Student student) {
        this.student = student;
        // Thiết lập JFrame
        setTitle("STUDENT");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // icon frame
        ImageIcon iconStudent = new ImageIcon("image/Student.png");
        setIconImage(iconStudent.getImage());

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        takeQuiz = new JMenu("Take Quiz");
        ImageIcon iconTakeQuiz = new ImageIcon("image/speech-bubble_17221393.png");
        takeQuiz.setIcon(iconTakeQuiz);
        menubar.add(takeQuiz);

        JMenuItem mcQuestion = new JMenuItem("Multiple Choice Question");
        mcQuestion.addActionListener(this);
        ImageIcon icMCQuestion = new ImageIcon("image/option.png");
        mcQuestion.setIcon(icMCQuestion);
        takeQuiz.add(mcQuestion);

        JMenuItem answerFillingQuestion = new JMenuItem("Answer Filling Question");
        answerFillingQuestion.addActionListener(this);
        ImageIcon icAnswerFillingQuestion = new ImageIcon("image/answer.png");
        answerFillingQuestion.setIcon(icAnswerFillingQuestion);
        takeQuiz.add(answerFillingQuestion);

        String[] columnNames = {"Quiz ID", "Quiz Name", "Date Created", "Quiz Type", "Total Score", "Total Question"};
        model = new DefaultTableModel(columnNames, 0);
        Object[][] data = {
        };
        //model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 950, 200);
        add(scrollPane);

        logOut = new JMenu("Log Out");
        ImageIcon iconLogOut = new ImageIcon("image/logout.png");
        logOut.setIcon(iconLogOut);
        menubar.add(logOut);
        JMenuItem logOutItem = new JMenuItem("Log Out");
        logOutItem.setIcon(iconLogOut);
        logOutItem.addActionListener(this);
        logOut.add(logOutItem);

        displayQuiz();
        add(menubar, BorderLayout.NORTH);
        setVisible(true);
    }
    private void displayQuiz() {
        List<Quiz> quizzes = QuizQuery.selectAll();

        // Xóa tất cả các hàng hiện tại khỏi model (nếu có)
        model.setRowCount(0);

        // Duyệt qua danh sách câu đố và thêm từng câu đố vào model
        for (Quiz quiz : quizzes) {
            Object[] rowData = {
                    quiz.getQuiz_ID(),
                    quiz.getQuiz_title(),
                    quiz.getTime(),
                    quiz.getQuiz_Type(),
                    quiz.getTotal_score(),
                    quiz.getTotal_question()
            };
            model.addRow(rowData);
        }
    }
    public static void main(String[] args) {
        new StudentUI(student);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();

        if (text.equals("Multiple Choice Question")) {
            new QuizSelectionScreen_student(student, "Multiple Choice");
        } else if (text.equals("Answer Filling Question")) {
            new QuizSelectionScreen_student(student, "Answer Filling");
        }
        else if (text.equals("Log Out")) {
            setVisible(false);
            new Login();
        }
    }
}
