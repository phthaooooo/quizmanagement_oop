package View;

import DAO.ConnectionDatabase;
import DAO.ResultQuery;
import Model.Result;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


// New Quiz Selection Screen
class QuizSelectionScreen extends JFrame {
    private JList<String> quizList;
    private DefaultListModel<String> listModel;
    private JButton startButton;
    private Connection connection;
    private Map<String, String> quizIdMap;
    private static Student student;

    public QuizSelectionScreen(Student student) {
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
                new MultipleChoiceQuiz(quizIdMap.get(selectedQuiz), student);
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
                if (Objects.equals(quizType, "multiplechoice")) {
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
        SwingUtilities.invokeLater(() -> new QuizSelectionScreen(student));
    }
}

// Modified MultipleChoiceQuiz class
// ... [Previous QuizSelectionScreen class code remains exactly the same] ...

// Modified MultipleChoiceQuiz class
public class MultipleChoiceQuiz extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton submitButton, nextButton;
    private ButtonGroup buttonGroup;
    private JLabel resultLabel;
    private String correctAnswer;
    private double mark;
    private int question_count = 0;
    private int true_answer = 0;
    private int currentQuestionId = 1;
    private Connection connection;
    private String quizId;
    private static Student student;

    public MultipleChoiceQuiz(String quizId, Student student) {
        this.student = student;
        this.quizId = quizId;
        ConnectionDatabase conn = new ConnectionDatabase();
        connection = conn.getC();
        initializeUI();
        loadQuestion(String.format("MC%02d", currentQuestionId));
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }


    private void initializeUI() {
        setTitle("Multiple Choice Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Question
        questionLabel = new JLabel("");
        questionLabel.setBounds(50, 50, 500, 30);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        // Options
        options = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton("");
            options[i].setBounds(70, 100 + (i * 40), 400, 30);
            options[i].setFont(new Font("Arial", Font.PLAIN, 14));
            options[i].setBackground(new Color(240, 240, 240));
            buttonGroup.add(options[i]);
            add(options[i]);
        }

        // Submit Button
        submitButton = new JButton("Submit Answer");
        submitButton.setBounds(70, 280, 150, 35);
        submitButton.setBackground(new Color(30, 144, 254));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.addActionListener(this);
        add(submitButton);

        // Next Button
        nextButton = new JButton("Next Question");
        nextButton.setBounds(240, 280, 150, 35);
        nextButton.setBackground(new Color(30, 144, 254));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(this);
        nextButton.setEnabled(false);
        add(nextButton);

        // Result Label
        resultLabel = new JLabel("");
        resultLabel.setBounds(70, 320, 400, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadQuestion(String questionId) {
        try {
            String query = "SELECT * FROM mc_question WHERE quizID = ? AND questionID = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, quizId);
            pstmt.setString(2, questionId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                submitButton.setEnabled(true);
                questionLabel.setText(rs.getString("question"));
                options[0].setText(rs.getString("op1"));
                options[1].setText(rs.getString("op2"));
                options[2].setText(rs.getString("op3"));
                options[3].setText(rs.getString("op4"));
                correctAnswer = rs.getString("key_question");
                question_count++;
                buttonGroup.clearSelection();
                resultLabel.setText("");
                nextButton.setEnabled(false);
            } else {
                setMark((double) true_answer / question_count * 10);
                String massage = "Quiz completed!\n" + String.format("Result: %.2f", getMark());
                JOptionPane.showMessageDialog(this, massage);
                Result res = new Result(student.getUserName(), quizId, "multiplechoice", question_count, true_answer, mark);
                ResultQuery.insertResult(res);
                dispose();
                new QuizSelectionScreen(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading question: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Check if any option is selected
            boolean answered = false;
            String selectedAnswer = "";

            for (JRadioButton option : options) {
                if (option.isSelected()) {
                    answered = true;
                    selectedAnswer = option.getText();
                    break;
                }
            }

            if (!answered) {
                resultLabel.setText("Please select an answer!");
                resultLabel.setForeground(Color.RED);
                return;
            }

            if (selectedAnswer.equals(correctAnswer)) {
                resultLabel.setText("Correct! Well done!");
                true_answer++;
                resultLabel.setForeground(new Color(0, 150, 0));
            } else {
                resultLabel.setText("Incorrect. The correct answer is: " + correctAnswer);
                resultLabel.setForeground(Color.RED);
            }
            submitButton.setEnabled(false);
            nextButton.setEnabled(true);

        } else if (e.getSource() == nextButton) {
            currentQuestionId++;
            loadQuestion(String.format("MC%02d", currentQuestionId));
        }
    }
}