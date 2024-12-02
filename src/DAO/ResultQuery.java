package DAO;

import Model.Quiz;
import Model.Result;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Result> selectAll() {
        var list = new ArrayList<Result>();
        var query = "select * from student_results";
        try {
            ConnectionDatabase cdb = new ConnectionDatabase();
            PreparedStatement stmt = cdb.c.prepareStatement(query);
            var resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                list.add(
                        new Result(
                                resultSet.getString("student_id"),
                                resultSet.getString("quiz_id"),
                                resultSet.getString("quiz_type"),
                                resultSet.getInt("total_questions"),
                                resultSet.getInt("correct_answers"),
                                resultSet.getDouble("score")

                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}