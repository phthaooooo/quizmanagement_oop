package Model;

public class Multiple_Choice_Question extends Question{
    private String quiz_id,question_id, question_text, opA, opB, opC, opD, key;
    // constructors
    public Multiple_Choice_Question(){
        super();
    }

    public Multiple_Choice_Question(String quiz_id,String question_id, String question_text, String opA, String opB, String opC, String opD, String key){
        this.quiz_id = quiz_id;
        this.question_id = question_id;
        this.question_text = question_text;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.key = key;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getOpA() {
        return opA;
    }

    public String getOpB() {
        return opB;
    }

    public String getOpC() {
        return opC;
    }

    public void setOpC(String opC) {
        this.opC = opC;
    }

    public String getOpD() {
        return opD;
    }

    public void setOpD(String opD) {
        this.opD = opD;
    }

    public void setOpB(String opB) {
        this.opB = opB;
    }

    public void setOpA(String opA) {
        this.opA = opA;
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
}