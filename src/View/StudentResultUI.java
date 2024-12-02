package View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentResultUI extends JFrame implements ActionListener {

    public StudentResultUI(){
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
        headerPanel.add(new JLabel("John Doe")); // Example name
        headerPanel.add(new JLabel("Class:"));
        headerPanel.add(new JLabel("10A1")); // Example class
        headerPanel.add(new JLabel("Completion Time:"));
        headerPanel.add(new JLabel("10 minutes")); // Example time
        add(headerPanel, BorderLayout.NORTH);

        // Body - Result Table
        String[] columnNames = {"Question", "Selected Answer", "Correct Answer", "Result", "Score"};
        Object[][] data = {
                {"Question 1", "B", "C", "❌", 0},
                {"Question 2", "A", "A", "✅", 1}
        };
        JTable resultTable = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new GridLayout(2, 1));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        footerPanel.add(new JLabel("Total Score: 1/2"));
        footerPanel.add(new JLabel("Feedback: Needs improvement."));
        add(footerPanel, BorderLayout.SOUTH);

        // Finalize JFrame
        setVisible(true);
    }
    public static void main(String[] args) {
       new StudentResultUI(); // Ensures the UI is created on the Event Dispatch Thread
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class ResultScreen extends JFrame {
    public ResultScreen() {
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
        headerPanel.add(new JLabel("John Doe")); // Example name
        headerPanel.add(new JLabel("Class:"));
        headerPanel.add(new JLabel("10A1")); // Example class
        headerPanel.add(new JLabel("Completion Time:"));
        headerPanel.add(new JLabel("10 minutes")); // Example time
        add(headerPanel, BorderLayout.NORTH);

        // Body - Result Table
        String[] columnNames = {"Question", "Selected Answer", "Correct Answer", "Result", "Score"};
        Object[][] data = {
                {"Question 1", "B", "C", "❌", 0},
                {"Question 2", "A", "A", "✅", 1}
        };
        JTable resultTable = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new GridLayout(2, 1));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        footerPanel.add(new JLabel("Total Score: 1/2"));
        footerPanel.add(new JLabel("Feedback: Needs improvement."));
        add(footerPanel, BorderLayout.SOUTH);

        // Finalize JFrame
        setVisible(true);
    }
}

class AnswerFillingScreen extends JFrame {
    public AnswerFillingScreen() {
        setTitle("Answer Filling Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(3, 2));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        headerPanel.setBackground(new Color(248, 249, 250));
        headerPanel.add(new JLabel("Student Name:"));
        headerPanel.add(new JLabel("John Doe")); // Example name
        headerPanel.add(new JLabel("Class:"));
        headerPanel.add(new JLabel("10A1")); // Example class
        headerPanel.add(new JLabel("Completion Time:"));
        headerPanel.add(new JLabel("5 minutes")); // Example time
        add(headerPanel, BorderLayout.NORTH);

        // Body - Answer Filling Table
        String[] columnNames = {"Question", "Your Answer", "Correct Answer", "Result", "Score"};
        Object[][] data = {
                {"What is the capital of France?", "", "Paris", "❌", 0},
                {"Solve: 5 + 3", "", "8", "❌", 0}
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
        footerPanel.add(new JLabel("Total Score: 0/2"));
        footerPanel.add(new JLabel("Feedback: Keep trying."));
        add(footerPanel, BorderLayout.SOUTH);

        // Finalize JFrame
        setVisible(true);
    }
}