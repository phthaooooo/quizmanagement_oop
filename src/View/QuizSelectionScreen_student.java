package View;

import DAO.ConnectionDatabase;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuizSelectionScreen_student extends JFrame {
    private JList<String> quizList;
    private DefaultListModel<String> listModel;
    private JButton startButton;
    private Connection connection;
    private Map<String, String> quizIdMap;
    private static String type;
    private static Student student;

    public QuizSelectionScreen_student(Student student, String type) {
        this.type = type;
        this.student = student;
        ConnectionDatabase conn = new ConnectionDatabase();
        connection = conn.getC();
        initializeUI();
        loadQuizzes();
    }


    private void initializeUI() {
        setTitle("Quiz Selection");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Title Label
        JLabel titleLabel = new JLabel("Select a Quiz", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Quiz List
        listModel = new DefaultListModel<>();
        quizList = new JList<>(listModel);
        quizList.setFont(new Font("Arial", Font.PLAIN, 14));
        quizList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        quizList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(quizList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Start Button
        startButton = new JButton("Start Quiz");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBackground(new Color(30, 144, 254));
        startButton.setForeground(Color.WHITE);
        startButton.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Enable start button when a quiz is selected
        quizList.addListSelectionListener(e -> startButton.setEnabled(!quizList.isSelectionEmpty()));

        // Start button action
        startButton.addActionListener(e -> {
            String selectedQuiz = quizList.getSelectedValue();
            if (selectedQuiz != null && quizIdMap.containsKey(selectedQuiz)) {
                dispose();
                if (Objects.equals(type, "multiplechoice"))
                {
                    new MultipleChoiceQuiz_student(quizIdMap.get(selectedQuiz), student);
                }
                else
                {
                    new FillBlankQuiz_student(quizIdMap.get(selectedQuiz), student);
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadQuizzes() {
        quizIdMap = new HashMap<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT quizID, quiz_name, quiz_type FROM quiz");

            while (rs.next()) {
                String quizId = rs.getString("quizID");
                String quizName = rs.getString("quiz_name");
                String quizType = rs.getString("quiz_type");
                if (Objects.equals(quizType, type)) {
                    listModel.addElement(quizName);
                    quizIdMap.put(quizName, quizId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading quizzes: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizSelectionScreen_student(student, type));
    }
}
