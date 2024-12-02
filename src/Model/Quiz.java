package Model;

public class Quiz {
    private String Quiz_ID, Quiz_title, Quiz_Type;
    private int total_question;
    private int total_score;
    private double score_per_question;

    public Quiz(){
    }
    public Quiz(String Quiz_ID, String Quiz_title,String Quiz_Type, int total_score, double score_per_question){
        this.Quiz_ID = Quiz_ID;
        this.Quiz_title = Quiz_title;
        this.Quiz_Type = Quiz_Type;
        this.total_score = total_score;
        this.score_per_question = score_per_question;
    }

    public String getQuiz_ID() {
        return Quiz_ID;
    }

    public void setQuiz_ID(String quiz_ID) {
        Quiz_ID = quiz_ID;
    }

    public String getQuiz_title() {
        return Quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        Quiz_title = quiz_title;
    }

    public double getScore_per_question() {
        return score_per_question;
    }

    public void setScore_per_question(double score_per_question) {
        this.score_per_question = score_per_question;
    }

    public int getTotal_question() {
        return total_question;
    }

    public void setTotal_question(int total_question) {
        this.total_question = total_question;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public String getQuiz_Type() {
        return Quiz_Type;
    }

    public void setQuiz_Type(String quiz_Type) {
        Quiz_Type = quiz_Type;
    }

    @Override
    public String toString() {
        return Quiz_ID + " " + Quiz_title + " " + Quiz_Type + " " + total_question + " " + total_score;
    }
}