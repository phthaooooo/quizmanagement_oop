package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionDatabase {
    public Connection c;
    public Statement s;

    public ConnectionDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///quiz", "root", "pnpt0809");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getC() {
        return c;
    }
}
