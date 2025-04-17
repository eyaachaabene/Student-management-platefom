package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	   // JDBC URL for MySQL running locally
    private static final String URL = "jdbc:mysql://localhost:3306/projetjee"; // Replace 'your_db_name' with your actual DB name
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "12345"; // Your MySQL password

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        
        try {
            // Load the MySQL driver class
            Class.forName("com.mysql.cj.jdbc.Driver"); // Required for newer MySQL versions
            
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        
        return connection;
    }
}
