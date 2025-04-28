package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	   // JDBC URL for MySQL running locally
	private static final String URL = "jdbc:mysql://localhost:3306/projetjee?useSSL=false&serverTimezone=UTC";     // Bypass MySQL 8+ auth issues
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "12345"; // Your MySQL password

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            throw new RuntimeException(e); // STOP here if driver not found
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
            throw e; // STOP here if connection failed
        }
    }

}
