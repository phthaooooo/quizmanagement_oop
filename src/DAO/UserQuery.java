package DAO;

import Model.Teacher;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {
    public static boolean insert(User user) {
        var query = "insert into users(username, password, role) values(?, ?, ?)";
        try {
            ConnectionDatabase cdb = new ConnectionDatabase();
            PreparedStatement stmt = cdb.c.prepareStatement(query);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            var count = stmt.executeUpdate();
            return count != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User selectByUsername(String username) {
        User user = new User();
        var query = "select * from users where username = ?";
        try{
            ConnectionDatabase cdb = new ConnectionDatabase();
            PreparedStatement stmt = cdb.c.prepareStatement(query);
            stmt.setString(1, user.getUserName());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static User selectByAccount(String username, String password) {
        User user = new User();
        var query = "select * from users where username = ? and password = ?";
        try{
            ConnectionDatabase cdb = new ConnectionDatabase();
            PreparedStatement stmt = cdb.c.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                if ("Teacher".equals(role)) {
                    user = new Teacher(rs.getString("username"), rs.getString("password"), rs.getString("role"));
                } else {
                    user = new User();
                    user.setUserName(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

}
