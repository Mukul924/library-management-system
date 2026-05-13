package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getConnection() throws SQLException{
        String URL = "jdbc:mysql://localhost:3306/Library_db";
        String USERNAME = "YOUR_DB_USERNAME";
        String PASSWORD = "YOUR_DB_PASSWORD"; 
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

}
