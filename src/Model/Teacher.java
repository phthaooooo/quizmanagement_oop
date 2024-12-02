package Model;

public class Teacher extends User {
    private String school, subject;
    public Teacher(String fullName, String userName, String password, String school, String subject) {
        super(fullName, userName, password);
        this.setRole("Model.Teacher");
        this.setSchool(school);
        this.setSubject(subject);
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
