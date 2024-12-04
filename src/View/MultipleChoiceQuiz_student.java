package View;

import DAO.ConnectionDatabase;
import DAO.ResultQuery;
import Model.Quiz;
import Model.Result;
import Model.Student;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


// Modified MultipleChoiceQuiz class
public class MultipleChoiceQuiz_student extends JFrame implements ActionListener {
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
    private static Quiz quiz;
    public MultipleChoiceQuiz_student(String quizId, Student student, Quiz quiz) {
        this.student = student;
        this.quizId = quizId;
        this.quiz = quiz;
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

    private Timer timer;
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
            String query = "SELECT question_text, option_a, option_b, option_c, option_d, correct_answer FROM multiplechoice WHERE quiz_id = ? AND question_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, quizId);
            pstmt.setString(2, questionId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                submitButton.setEnabled(true);
                questionLabel.setText(rs.getString("question_text"));
                options[0].setText(rs.getString("option_a"));
                options[1].setText(rs.getString("option_b"));
                options[2].setText(rs.getString("option_c"));
                options[3].setText(rs.getString("option_d"));
                correctAnswer = rs.getString("correct_answer");
                question_count++;
                buttonGroup.clearSelection();
                resultLabel.setText("");
                nextButton.setEnabled(false);
            } else {
                setMark((double) true_answer / question_count * 10);
                String massage = "Quiz completed!\n" + String.format("Result: %.2f", getMark());
                JOptionPane.showMessageDialog(this, massage);
                Result res = new Result(student.getUserName(), quizId, "Multiple Choice", question_count, true_answer, mark);
                ResultQuery.insertResult(res);
                dispose();
                new QuizSelectionScreen_student(student,  "Multiple Choice");
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