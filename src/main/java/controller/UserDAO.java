package controller;

import java.sql.*;

import DAO.DatabaseConnection;
import model.User;
public class UserDAO {
    

    public UserDAO() {
        
    }

    // Insert User (Sign Up)
    public int signUp(String email, String password, String role) {
        String query = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);  // Store the password as-is (plain text)
            stmt.setString(3, role);
            stmt.executeUpdate();
            
            // Get the generated userId (primary key)
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);  // Return the userId
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Return -1 if insertion failed
    }


    // Select User (Login)
    public User login(String email, String password) {
        try {Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("address"),
                                resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get User ID by Email and Password (For Login)
    public int getUserIdByEmailAndPassword(String email, String password) {
        try {Connection conn = DatabaseConnection.getConnection();
        System.out.println(email);
        System.out.println(password);
            String query = "SELECT id FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // If no matching user is found, return 0
    }
}
