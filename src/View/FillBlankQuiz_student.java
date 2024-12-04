package View;

import DAO.ConnectionDatabase;
import DAO.ResultQuery;
import Model.Quiz;
import Model.Result;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FillBlankQuiz_student extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton, nextButton;
    private JLabel resultLabel;
    private String correctAnswer;
    private double mark;
    private int true_ans = 0;
    private int question_count = 0;
    private int currentQuestionId = 1;
    private String quizId;
    private static Student student;
    private Connection connection;
    private JLabel timerLabel;
    private Timer timer;
    private int remainingTime;
    private Quiz quiz;
    public FillBlankQuiz_student(String quizId, Student student, Quiz quiz) {
        this.student = student;
        this.quizId = quizId;
        this.quiz = quiz;
        ConnectionDatabase conn = new ConnectionDatabase();
        connection = conn.getC();
        initializeUI();
        loadQuestion(String.format("FB%02d", currentQuestionId));
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    private void initializeUI() {
        setTitle("Fill in the Blank Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Thời gian ban đầu (ví dụ 5 phút = 300 giây)
        remainingTime = 300 ;

        timerLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timerLabel.setBounds(450, 50, 100, 50);
        add(timerLabel);

        // Khởi tạo Timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Giảm thời gian còn lại
                remainingTime--;
                timerLabel.setText(formatTime(remainingTime));

                // Nếu hết thời gian
                if (remainingTime <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time is up! Quiz over.");
                    // Có thể thêm mã để dừng quiz ở đây.
                }
            }
        });

        // Bắt đầu đếm ngược khi chạy ứng dụng
        timer.start();

        // Question Label
        questionLabel = new JLabel("");
        questionLabel.setBounds(50, 50, 500, 30);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        // Answer field
        answerField = new JTextField();
        answerField.setBounds(50, 100, 200, 30);
        answerField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(answerField);

        // Submit Button
        submitButton = new JButton("Submit Answer");
        submitButton.setBounds(50, 150, 150, 35);
        submitButton.setBackground(new Color(30, 144, 254));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.addActionListener(this);
        add(submitButton);

        // Next Button
        nextButton = new JButton("Next Question");
        nextButton.setBounds(220, 150, 150, 35);
        nextButton.setBackground(new Color(30, 144, 254));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(this);
        nextButton.setEnabled(false);
        add(nextButton);

        // Result Label
        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 200, 500, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadQuestion(String questionId) {
        try {
            String query = "SELECT question_text, correct_answer FROM answerfilling WHERE quiz_id = ? and question_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, quizId);
            pstmt.setString(2, questionId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                submitButton.setEnabled(true);
                answerField.setEnabled(true);
                questionLabel.setText(rs.getString("question_text"));
                correctAnswer = rs.getString("correct_answer");
                answerField.setText("");
                resultLabel.setText("");
                question_count++;
                nextButton.setEnabled(false);
            } else {
                setMark((double) true_ans / question_count * 10);
                String massage = "Quiz completed!\n" + String.format("Result: %.2f", getMark());
                JOptionPane.showMessageDialog(this, massage);
                Result res = new Result(student.getUserName(), quizId, "Answer Filling", question_count, true_ans, mark);
                ResultQuery.insertResult(res);
                dispose();
                new QuizSelectionScreen_student(student, "Answer Filling");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading question: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String userAnswer = answerField.getText().trim();

            if (userAnswer.isEmpty()) {
                resultLabel.setText("Please enter an answer!");
                resultLabel.setForeground(Color.RED);
                return;
            }

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                resultLabel.setText("Correct! " + correctAnswer + " is the right answer!");
                resultLabel.setForeground(new Color(0, 150, 0));
                true_ans++;
            } else {
                resultLabel.setText("Incorrect. The correct answer is " + correctAnswer + ".");
                resultLabel.setForeground(Color.RED);
            }
            submitButton.setEnabled(false);
            answerField.setEnabled(false);
            nextButton.setEnabled(true);

        } else if (e.getSource() == nextButton) {
            currentQuestionId++;
            loadQuestion(String.format("FB%02d", currentQuestionId));
        }
    }

}