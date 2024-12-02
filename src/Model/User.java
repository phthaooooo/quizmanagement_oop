package Model;

public class User {
    private String userID, userName, password, role;
    private static int id = 1;

    public User(){

    }
    public User( String userName, String password, String role ) {
        this.userID = String.format("U%04d", id++);
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return  userName + " " + password + " " + role;
    }
}
