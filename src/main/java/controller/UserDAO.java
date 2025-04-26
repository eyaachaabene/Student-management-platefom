package controller;

import java.sql.*;

import DAO.DatabaseConnection;
import model.User;
public class UserDAO {
    

    public UserDAO() {
        
    }

    // Insert User (Sign Up)
    public boolean signUp(User user) {
        try {Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO users (email, password, address, role) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getRole());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1)); // Set the generated ID
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
