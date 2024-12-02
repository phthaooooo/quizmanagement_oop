package DAO;

import Model.Answer_Filling_Question;
import Model.Multiple_Choice_Question;

import java.sql.PreparedStatement;

public class QuestionQuery {
    public static boolean insertMC(Multiple_Choice_Question s) {
        String query = "insert into multiplechoice(question_id, quiz_id, question_text, option_a, option_b, option_c, option_d, correct_answer)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            ConnectionDatabase cdb = new ConnectionDatabase();

            PreparedStatement stmt = cdb.c.prepareStatement(query);
            stmt.setString(1, s.getQuestion_id());
            stmt.setString(2, s.getQuiz_id());// mc_questionID (giả sử là String)
            stmt.setString(3, s.getQuestion_text());      // Câu hỏi
            stmt.setString(4, s.getOpA());      // Đáp án 1
            stmt.setString(5, s.getOpB());      // Đáp án 2
            stmt.setString(6, s.getOpC());      // Đáp án 3
            stmt.setString(7, s.getOpD());      // Đáp án 4
            stmt.setString(8, s.getKey());// Đáp án đúng (key_question)
            int count = stmt.executeUpdate();
            return count != 0;
        } catch (Exception ae) {
            ae.printStackTrace();
            return false;
        }
    }

    public static boolean insertAS(Answer_Filling_Question s) {
        String query = "insert into answerfilling(question_id, quiz_id,question_text, correct_answer)" +
                "values (?, ?, ?, ?)";
        try{
            ConnectionDatabase cdb = new ConnectionDatabase();

            PreparedStatement stmt = cdb.c.prepareStatement(query);
            stmt.setString(1, s.getQuestion_id());
            stmt.setString(2, s.getQuiz_id());// mc_questionID (giả sử là String)
            stmt.setString(3, s.getQuestion_text());      // Câu hỏi
            stmt.setString(4, s.getKey());// Đáp án đúng (key_question)
            int count = stmt.executeUpdate();
            return count != 0;
        } catch (Exception ae) {
            ae.printStackTrace();
            return false;
        }
    }
}
