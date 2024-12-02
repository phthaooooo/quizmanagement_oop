package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ResultQuizofAS extends JFrame{
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
        String[] columnNames = {"Quiz ID", "Quiz Name", "Student Name", "Total Score"};
        Object[][] data = {
        };

        JTable answerTable = new JTable(new DefaultTableModel(data, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Allow editing only in "Your Answer" column
            }
        };
        JScrollPane scrollPane = new JScrollPane(answerTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new GridLayout(2, 1));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(footerPanel, BorderLayout.SOUTH);

        // Finalize JFrame
        setVisible(true);
    }
    public static void main(String[] args) {
        new ResultQuizofAS();
    }
}
