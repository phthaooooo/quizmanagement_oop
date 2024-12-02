package Model;

public class Admin extends User{
    Admin(){
        super();
    }

    public Admin(String UserName, String password, String role){
        super(UserName, password, role);
        this.setRole("Admin");
    }
    }
