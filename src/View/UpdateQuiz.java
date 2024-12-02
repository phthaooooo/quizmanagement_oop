package View;


import DAO.ConnectionDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateQuiz extends JFrame implements ActionListener {
    private static String nameQuiz;
    private JTable table;
    private DefaultTableModel model_mc;
    private DefaultTableModel model_as;
    private String quizID = null;
    private String quizType = null;
    private JButton saveButton;
    public UpdateQuiz(String nameQuiz) {
        this.nameQuiz = nameQuiz;
        setTitle("Update Quiz");
        setSize(1000, 700);  // Kích thước frame
        setLayout(new BorderLayout());  // Đặt layout chính

        // Hiển thị tên quiz
        JLabel titleLabel = new JLabel("Quiz: " + nameQuiz, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);  // Đặt titleLabel vào phía Bắc của frame

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);  // Gán ActionListener cho nút Save
        add(saveButton, BorderLayout.SOUTH);
        try {
            // Truy vấn SQL lấy câu hỏi từ quiz_name
            ConnectionDatabase cdb = new ConnectionDatabase();
            String getQuizIDQuery = "SELECT quiz_id, quiz_type FROM quiztable WHERE quiz_name = ?";
            PreparedStatement stmtQuizID = cdb.c.prepareStatement(getQuizIDQuery);
            stmtQuizID.setString(1, nameQuiz);
            ResultSet rsQuizID = stmtQuizID.executeQuery();

            // Kiểm tra xem quiz_name có tồn tại không

            if (rsQuizID.next()) {
                quizID = rsQuizID.getString("quiz_id");
                quizType = rsQuizID.getString("quiz_type");
            } else {
                JOptionPane.showMessageDialog(this, "Quiz name not found.");
                return;  // Nếu quiz_name không tồn tại, dừng việc hiển thị
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        if(quizType.equals("Multiple Choice")){
            String[] columnNames = {"Quiz ID", "Question ID", "Question Text", "Option A", "Option B", "Option C", "Option D", "Correct Answer"};
            model_mc = new DefaultTableModel(columnNames, 0);
            table = new JTable(model_mc);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);// Đặt JTable vào giữa frame
        } else if (quizType.equals("Answer Filling")) {
            String[] columnNames = {"Quiz ID", "Question ID", "Question Text", "Correct Answer"};
            model_as = new DefaultTableModel(columnNames, 0);
            table = new JTable(model_as);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
        }


        // Đặt nút Save ở phía dưới

        try {
            displayQuestions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public static void main (String[] args) {
        new UpdateQuiz(nameQuiz);
    }
    private void displayQuestions() throws SQLException {
        ConnectionDatabase cdb = new ConnectionDatabase();
        if(quizType.equals("Multiple Choice")){
            // Bước 2: Dùng quiz_id để truy vấn bảng answerfilling
            String sql = "SELECT question_id, question_text, option_a, option_b, option_c, option_d, correct_answer FROM multiplechoice WHERE quiz_id = ?";
            PreparedStatement stmt = cdb.c.prepareStatement(sql);
            stmt.setString(1, quizID);
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua kết quả và thêm các câu hỏi vào DefaultTableModel
            while (rs.next()) {
                String questionID = rs.getString("question_id");
                String questionText = rs.getString("question_text");
                String a = rs.getString("option_a");
                String b = rs.getString("option_b");
                String c = rs.getString("option_c");
                String d = rs.getString("option_d");
                String key = rs.getString("correct_answer");
                // Thêm dòng mới vào model của JTable
                model_mc.addRow(new Object[]{quizID, questionID, questionText, a, b, c, d, key});
            }
        } else if (quizType.equals("Answer Filling")) {
            String sql = "SELECT question_id, question_text, correct_answer FROM answerfilling WHERE quiz_id = ?";
            PreparedStatement stmt = cdb.c.prepareStatement(sql);
            stmt.setString(1, quizID);
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua kết quả và thêm các câu hỏi vào DefaultTableModel
            while (rs.next()) {
                String questionID = rs.getString("question_id");
                String questionText = rs.getString("question_text");
                String key = rs.getString("correct_answer");
                // Thêm dòng mới vào model của JTable
                model_as.addRow(new Object[]{quizID, questionID, questionText, key});
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            try{
                saveData();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    private void saveData() throws SQLException {
        ConnectionDatabase cdb = new ConnectionDatabase();
        if(quizType.equals("multiple choice")){
            for (int row = 0; row < model_mc.getRowCount(); row++) {
                String questionID = model_mc.getValueAt(row, 1).toString();
                String questionText = model_mc.getValueAt(row, 2).toString();
                String a = model_mc.getValueAt(row, 3).toString();
                String b = model_mc.getValueAt(row, 4).toString();
                String c = model_mc.getValueAt(row, 5).toString();
                String d = model_mc.getValueAt(row, 6).toString();
                String key = model_mc.getValueAt(row, 7).toString();

                // Cập nhật câu hỏi trong cơ sở dữ liệu
                String sql = "UPDATE multiplechoice SET question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_answer = ? WHERE question_id = ? AND quiz_id = ?";
                PreparedStatement stmt = cdb.c.prepareStatement(sql);
                stmt.setString(1, questionText);
                stmt.setString(2, a);
                stmt.setString(3, b);
                stmt.setString(4, c);
                stmt.setString(5, d);
                stmt.setString(6, key);
                stmt.setString(7, questionID);
                stmt.setString(8, quizID);
                stmt.executeUpdate();  // Thực thi câu lệnh UPDATE
            }
            cdb.c.close();
            JOptionPane.showMessageDialog(this, "Data has been saved successfully!");
        } /*else if (quizType.equals("answer filling")){
            for (int row = 0; row < model_as.getRowCount(); row++) {
                String questionID = model_as.getValueAt(row, 1).toString();
                String questionText = model_as.getValueAt(row, 2).toString();
                String key = model_as.getValueAt(row, 3).toString();

                // Cập nhật câu hỏi trong cơ sở dữ liệu
                String sql = "UPDATE answerfilling SET question_text = ?, correct_answer = ? WHERE question_id = ? AND quiz_id = ?";
                PreparedStatement stmt = cdb.c.prepareStatement(sql);
                stmt.setString(1, questionText);
                stmt.setString(2, key);
                stmt.setString(3, questionID);
                stmt.setString(4, quizID);
                stmt.executeUpdate();  // Thực thi câu lệnh UPDATE

            }
            cdb.c.close();
            JOptionPane.showMessageDialog(this, "Data has been saved successfully!");
        }*/
        // Duyệt qua từng dòng trong DefaultTableModel để cập nhật dữ liệu


        // Đóng kết nối

    }
}


