package DAO;

import model.AssignmentModel;
import model.TeacherModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    public boolean insertTeacher(int userId, String name, String department) {  // Changed parameter
        String query = "INSERT INTO teachers (teacher_id, name, department) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, department);  
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add this method to match your model's getTeacherNameById functionality
    public String getTeacherNameById(int teacherId) {
        String query = "SELECT name FROM teachers WHERE teacher_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("name") : "Unknown";
            
        } catch (SQLException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }
    
    public List<AssignmentModel> getAssignmentsByTeacherId(int teacherId) {
        List<AssignmentModel> assignments = new ArrayList<>();
        String query = "SELECT a.* FROM assignments a " +
                       "JOIN courses c ON a.course_id = c.course_id " +
                       "JOIN subjects s ON c.subjectid = s.subject_id " +
                       "WHERE s.teacher_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                AssignmentModel assignment = new AssignmentModel();
                assignment.setAssignmentId(rs.getInt("assignment_id"));
                assignment.setCourseId(rs.getInt("course_id"));
                assignment.setTitle(rs.getString("title"));
                assignment.setDescription(rs.getString("description"));
                assignment.setDeadline(rs.getString("deadline"));
                assignment.setPdfLink(rs.getString("pdf_link"));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
    
}