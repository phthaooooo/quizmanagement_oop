package View;

import DAO.QuizQuery;
import Model.Quiz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AllQuiz extends JFrame implements ActionListener {
    JButton back;
    JFrame frame;
    DefaultTableModel model;
    public AllQuiz() {

        frame = new JFrame("Multiple Choice") ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(null);
        String[] columnNames = {"Quiz ID", "Quiz Name", "Date Created", "Quiz Type"};
        model = new DefaultTableModel(columnNames, 0);
        Object[][] data = {
                {"Q01", "Technology", "2024-12-10", "multiple choice"}
        };
        //model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 950, 200);
        frame.add(scrollPane);
        back = new JButton("Back");
        back.setBounds(700, 550, 150, 30);
        back.addActionListener(this);
        frame.add(back);

        displayQuiz();
        frame.setVisible(true);
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
                    quiz.getQuiz_Type(),
                    quiz.getTotal_score(),
                    quiz.getTotal_question()
            };
            model.addRow(rowData);
        }
    }
    public static void main(String[] args) {
        new AllQuiz();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            frame.dispose();

        }
    }
}
