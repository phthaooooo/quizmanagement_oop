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
    // Buttons
    JButton btnTakeQuiz, btnLogOut, btnResult;
    JPopupMenu quizMenu;
    private static Student student;
    public StudentUI(Student student) {
        this.student = student;
        // Setup JFrame
        setTitle("Student Portal");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header - Title
        JLabel headerLabel = new JLabel("Welcome to the Student Portal!", JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        headerLabel.setForeground(new Color(33, 37, 41)); // Dark gray
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Create Buttons
        btnTakeQuiz = new JButton("Take Quiz");
        btnTakeQuiz.setFont(new Font("Serif", Font.PLAIN, 14));
        btnTakeQuiz.setPreferredSize(new Dimension(150, 40));
        btnTakeQuiz.setBackground(new Color(72, 149, 239)); // Light blue
        btnTakeQuiz.setForeground(Color.WHITE);
        btnTakeQuiz.setFocusPainted(false);
        btnTakeQuiz.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTakeQuiz.addActionListener(this);

        btnResult = new JButton("My Result");
        btnResult.setFont(new Font("Serif", Font.PLAIN, 14));
        btnResult.setPreferredSize(new Dimension(150, 40));
        btnResult.setBackground(new Color(72, 149, 239)); // Light blue
        btnResult.setForeground(Color.WHITE);
        btnResult.setFocusPainted(false);
        btnResult.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnResult.addActionListener(this);

        btnLogOut = new JButton("Log Out");
        btnLogOut.setFont(new Font("Serif", Font.PLAIN, 14));
        btnLogOut.setPreferredSize(new Dimension(150, 40));
        btnLogOut.setBackground(new Color(220, 53, 69)); // Red
        btnLogOut.setForeground(Color.WHITE);
        btnLogOut.setFocusPainted(false);
        btnLogOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogOut.addActionListener(this);

        // Add Buttons to a Centered Panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(248, 249, 250)); // Light gray background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(btnTakeQuiz, gbc);


        gbc.gridy = 1;
        buttonPanel.add(btnResult, gbc);

        gbc.gridy = 2;
        buttonPanel.add(btnLogOut, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        // Footer - Info
        JLabel footerLabel = new JLabel("© 2024 Student Management System", JLabel.CENTER);
        footerLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(108, 117, 125)); // Muted gray
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(footerLabel, BorderLayout.SOUTH);

        // Quiz Menu
        quizMenu = new JPopupMenu();

        JMenuItem multipleChoiceOption = new JMenuItem("Multiple Choice Question");
        multipleChoiceOption.addActionListener(e -> new QuizSelectionScreen_student(student, "Multiple Choice"));
        quizMenu.add(multipleChoiceOption);

        JMenuItem answerFillingOption = new JMenuItem("Answer Filling Question");
        answerFillingOption.addActionListener(e -> new QuizSelectionScreen_student(student, "Answer Filling"));
        quizMenu.add(answerFillingOption);

        // Finalize JFrame
        setVisible(true);
    }
    /*private void displayQuiz() {
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
    }*/
    public static void main(String[] args) {
        new StudentUI(student);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*String text = e.getActionCommand();

        if (text.equals("Multiple Choice Question")) {
            new QuizSelectionScreen_student(student, "Multiple Choice");
        } else if (text.equals("Answer Filling Question")) {
            new QuizSelectionScreen_student(student, "Answer Filling");
        }
        else if (text.equals("Log Out")) {
            setVisible(false);
            new Login();
        }*/
        if (e.getSource() == btnTakeQuiz) {
            // Show quiz options
            quizMenu.show(btnTakeQuiz, btnTakeQuiz.getWidth() / 2, btnTakeQuiz.getHeight() / 2);
        } else if (e.getSource() == btnResult) {
            new ResultQuizofAS1(student);
        } else if (e.getSource() == btnLogOut) {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to log out?",
                    "Confirm Log Out",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                System.exit(0); // Exit the program
            }
        }
    }
}
