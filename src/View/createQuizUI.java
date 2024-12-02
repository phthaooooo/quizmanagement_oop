package View;

import DAO.QuizQuery;
import Model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createQuizUI extends JFrame implements ActionListener {
    private static String s;
    JTextField quizID, quizType, quizName, total_score, total_question;
    JButton next;
    JComboBox comboBox;
    public createQuizUI(String s){
        this.s = s;
        getContentPane().setBackground(new Color(0, 51, 102));
        setLayout(null);
        // heading name
        JLabel heading = new JLabel("Quiz");
        heading.setBounds(380, 60, 300, 45);
        heading.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        //username
        JLabel quizid = new JLabel("Quiz ID");
        quizid.setBounds(200, 150, 300, 20);
        quizid.setFont(new Font("Arial", Font.BOLD, 18));
        quizid.setForeground(new Color(30, 144, 254));
        add(quizid);

        quizID = new JTextField();
        quizID.setBounds(300, 150, 300, 25);
        quizID.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(quizID);
        // quizName
        JLabel quizname = new JLabel("Quiz Name");
        quizname.setBounds(200, 200, 300, 20);
        quizname.setFont(new Font("Arial", Font.BOLD, 18));
        quizname.setForeground(new Color(30, 144, 254));
        add(quizname);

        quizName = new JTextField();
        quizName.setBounds(300, 200, 300, 25);
        quizName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(quizName);

        //Quiz type
        JLabel quiztype = new JLabel("Quiz Type");
        quiztype.setBounds(200, 250, 300, 20);
        quiztype.setFont(new Font("Arial", Font.BOLD, 18));
        quiztype.setForeground(new Color(30, 144, 254));
        add(quiztype);

        String[] options = {"Multiple Choice", "Answer Filling"};
        // Tạo JComboBox từ mảng lựa chọn
        comboBox = new JComboBox<>(options);
        // Tùy chọn kiểu hiển thị
        comboBox.setPreferredSize(new Dimension(150, 30));  // Kích thước của combo box
        comboBox.setBounds(300, 250, 300, 25);
        add(comboBox);

        JLabel totalscore = new JLabel("Total Score");
        totalscore.setBounds(200, 300, 300, 20);
        totalscore.setFont(new Font("Arial", Font.BOLD, 17));
        totalscore.setForeground(new Color(30, 144, 254));
        add(totalscore);

        total_score = new JTextField();
        total_score.setBounds(300, 300, 300, 25);
        total_score.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(total_score);

        JLabel perscore = new JLabel("Total Question");
        perscore.setBounds(160, 350, 300, 20);
        perscore.setFont(new Font("Arial", Font.BOLD, 17));
        perscore.setForeground(new Color(30, 144, 254));
        add(perscore);

        total_question = new JTextField();
        total_question.setBounds(300, 350, 300, 25);
        total_question.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(total_question);

        next = new JButton("Next");
        next.setBounds(600, 400, 120, 30);
        next.setBackground(new Color(30, 144, 254));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Arial", Font.BOLD, 16)); // Chọn font Arial, kiểu chữ thường, cỡ 16
        next.addActionListener(this);
        add(next);

        setSize(1000, 500);
        setLocation(300, 150); // center
        setVisible(true);
    }
    public static void main(String[] args) {
        new createQuizUI(s);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            String quizid = quizID.getText();
            String quizname = quizName.getText();
            String quiztype = (String) comboBox.getSelectedItem();
            int totalscore = Integer.parseInt(total_score.getText());
            int totalquestion = Integer.parseInt(total_question.getText());
            double scorePerQuestion = totalscore / (double) totalquestion;
            Quiz quiz = new Quiz(quizid, quizname, quiztype, totalscore, scorePerQuestion);
            boolean isSuccess = QuizQuery.insert(quiz);
            if (e.getSource() == next){
                if(quizID.getText().isEmpty() || quizName.getText().isEmpty() || total_score.getText().isEmpty() || total_question.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill the blank",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else{
                    if (isSuccess) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Added quiz successfully",
                                "Notification",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        if(quiztype.equals("Multiple Choice")) {
                            new MultipleChoice(quiz.getQuiz_ID());
                        } else{
                            new AnswerFilling(quiz.getQuiz_ID());
                        }
                    }else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Falied, please try again",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        }

}
