package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/todolist_app";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "secretpassword";

    private static final Connection connection;

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
