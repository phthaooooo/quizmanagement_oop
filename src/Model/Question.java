package Model;

public class Question {
    private String Question_text, Question_answer;

    // constructors
    public Question() {

    }

    public Question(String Question_text, String Question_answer) {
        this.Question_text = Question_text;
        this.Question_answer = Question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        Question_answer = question_answer;
    }

    public String getQuestion_answer() {
        return this.Question_answer;
    }

    public String getQuestion_text() {
        return Question_text;
    }

    public void setQuestion_text(String question_text) {
        Question_text = question_text;
    }


}