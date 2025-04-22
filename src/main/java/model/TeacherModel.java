package model;
import DAO.DatabaseConnection;
import java.sql.*;

public class TeacherModel {
    private int teacherId;
    private String name;
    private String department;

    // Getters and Setters
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Method to get a teacher by ID
    public String getTeacherNameById(int teacherId) {
        String teacherName = "Unknown";  // Default teacher name if not found
        String query = "SELECT name FROM teachers WHERE teacher_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    teacherName = rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherName;
    }
    }

