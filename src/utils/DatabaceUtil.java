package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaceUtil {
    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userjon_db",
            "userjon_db", "123456");
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
