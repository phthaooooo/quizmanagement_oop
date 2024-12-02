package Model;

public class Result
{
    private int result_id, total_question, correct_answer;
    private double score;
    private String student_id, quiz_id, quiz_type;

    public Result(String student_id, String quiz_id, String quiz_type, int total_question, int correct_answer, double score) {
        this.student_id = student_id;
        this.quiz_id = quiz_id;
        this.quiz_type = quiz_type;
        this.total_question = total_question;
        this.correct_answer = correct_answer;
        this.score = score;
    }

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public int getTotal_question() {
        return total_question;
    }

    public void setTotal_question(int total_question) {
        this.total_question = total_question;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_type() {
        return quiz_type;
    }

    public void setQuiz_type(String quiz_type) {
        this.quiz_type = quiz_type;
    }
}
