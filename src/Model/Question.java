package Model;

public class Question {
    private String Question_text;
    // constructors
    public Question() {
    }
    public Question(String Question_text) {
        this.Question_text = Question_text;
    }
    public String getQuestion_text() {
        return Question_text;
    }
    public void setQuestion_text(String question_text) {
        Question_text = question_text;
    }
}

