package Model;

public class Student extends User
{
    public Student(String fullName, String userName, String password)
    {
        super(fullName, userName, password);
        this.setRole("Student");
    }
}
