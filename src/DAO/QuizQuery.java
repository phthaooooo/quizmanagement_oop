package DAO;

import Model.Quiz;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizQuery {
    public static boolean insert(Quiz quiz) {
        var query = "insert into quiztable(quiz_id, quiz_name, quiz_type, total_score, total_question) values(?, ?, ?, ?, ?)";
        try {
            ConnectionDatabase cdb = new ConnectionDatabase();
            PreparedStatement stmt = cdb.c.prepareStatement(query);
            stmt.setString(1, quiz.getQuiz_ID());
            stmt.setString(2, quiz.getQuiz_title());
            stmt.setString(3, quiz.getQuiz_Type());
            stmt.setInt(4, quiz.getTotal_score());
            stmt.setInt(5, quiz.getTotal_question());
            var count = stmt.executeUpdate();
            return count != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<Quiz> selectAll() {
        var list = new ArrayList<Quiz>();
        var query = "select * from quiztable";
        try {
            ConnectionDatabase cdb = new ConnectionDatabase();
            PreparedStatement stmt = cdb.c.prepareStatement(query);
            var resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                list.add(
                        new Quiz(
                                resultSet.getString("quiz_id"),
                                resultSet.getString("quiz_name"),
                                resultSet.getString("quiz_type"),
                                resultSet.getInt("total_score"),
                                (double) resultSet.getInt("total_score")/ resultSet.getInt("total_question")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
