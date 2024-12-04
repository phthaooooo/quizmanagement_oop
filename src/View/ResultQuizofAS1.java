package View;

import DAO.ResultQuery;
import Model.Result;
import Model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ResultQuizofAS1 extends JFrame {
    private static Student student;
    DefaultTableModel model;
    public ResultQuizofAS1(Student student) {
        this.student = student;
        setTitle("Quiz Result");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(3, 2));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        headerPanel.setBackground(new Color(248, 249, 250));
        headerPanel.add(new JLabel("Student Name:"));
        headerPanel.add(new JLabel(student.getUserName())); // Example name
        headerPanel.add(new JLabel("Completion Time:"));
        headerPanel.add(new JLabel("5 minutes")); // Example time
        add(headerPanel, BorderLayout.NORTH);

        // Body - Answer Filling Table
        String[] columnNames = {"Quiz ID", "Quiz Type", "Correct Answer", "Score"};
        Object[][] data = {
        };
        model = new DefaultTableModel(columnNames, 0);
        JTable answerTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Allow editing only in "Your Answer" column
            }
        };
        JScrollPane scrollPane = new JScrollPane(answerTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Footer

        displayResult();
        // Finalize JFrame
        setVisible(true);
    }
    public static void main(String[] args) {
        new ResultQuizofAS1(student);
    }
    public void displayResult() {
        List<Result> results = ResultQuery.selectAll();

        // Xóa tất cả các hàng hiện tại khỏi model (nếu có)
        model.setRowCount(0);

        // Duyệt qua danh sách câu đố và thêm từng câu đố vào model
        for (Result r : results) {
            Object[] rowData = {
                    r.getQuiz_id(),
                    r.getQuiz_type(),
                    r.getCorrect_answer(),
                    r.getScore(),

            };
            model.addRow(rowData);
        }
    }

}