package Model;

public class Teacher extends User {

    public Teacher(){
        super();
    }
    public Teacher(String userName, String password, String role) {
        super(userName, password, role);
        this.setRole("Teacher");

    }




}
