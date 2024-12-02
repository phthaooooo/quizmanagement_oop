package Model;

public class Student extends User
{
    public Student(){
        super();
    }
    public Student(String userName, String password, String role)
    {
        super(userName, password, role);
        this.setRole("Student");
    }
}
