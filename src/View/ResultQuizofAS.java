package View;

import DAO.QuizQuery;
import DAO.ResultQuery;
import Model.Quiz;
import Model.Result;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ResultQuizofAS extends JFrame{
    DefaultTableModel model;
    public ResultQuizofAS() {
        setTitle("Answer Filling Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(3, 2));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        headerPanel.setBackground(new Color(248, 249, 250));// Example time
        add(headerPanel, BorderLayout.NORTH);

        // Body - Answer Filling Table
        String[] columnNames = {"Student ID", "Quiz Type", "Total Questions", "Correct Answers", "Score", "Quiz ID"};
        model = new DefaultTableModel(columnNames, 0); // Khởi tạo model với tiêu đề cột

        JTable answerTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Allow editing only in "Your Answer" column
            }
        };

        JScrollPane scrollPane = new JScrollPane(answerTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);
        displayResult();
        // Footer

        // Finalize JFrame
        setVisible(true);
    }
    private void displayResult() {
        List<Result> results = ResultQuery.selectAll();

        // Xóa tất cả các hàng hiện tại khỏi model (nếu có)
        model.setRowCount(0);

        // Duyệt qua danh sách câu đố và thêm từng câu đố vào model
        for (Result r : results) {
            Object[] rowData = {
                    r.getStudent_id(),
                    r.getQuiz_type(),
                    r.getTotal_question(),
                    r.getCorrect_answer(),
                    r.getScore(),
                    r.getQuiz_id()
            };
            model.addRow(rowData);
        }
    }
    public static void main(String[] args) {
        new ResultQuizofAS();
    }
}
