package DAO;

import Model.Result;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultQuery {
    public static void insertResult(Result result) {
        String query = "insert into student_results(student_id, quiz_id, quiz_type, total_questions, correct_answers, score)" +
                "values (?, ?, ?, ?, ?, ?)";
        try {
            ConnectionDatabase cdb = new ConnectionDatabase();
            PreparedStatement stmt = cdb.c.prepareStatement(query);
            stmt.setString(1, result.getStudent_id());
            stmt.setString(2, result.getQuiz_id());
            stmt.setString(3, result.getQuiz_type());
            stmt.setInt(4, result.getTotal_question());
            stmt.setInt(5, result.getCorrect_answer());
            stmt.setDouble(6, result.getScore());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
