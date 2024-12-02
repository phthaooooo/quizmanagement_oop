package Model;

public class Answer_Filling_Question extends Question{
    private String quiz_id,question_id, key;
    // constructors
    public Answer_Filling_Question(){
        super();
    }

    public Answer_Filling_Question(String quiz_id,String question_id, String question_text, String key){
       super(question_text);
        this.quiz_id = quiz_id;
        this.question_id = question_id;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }
}
