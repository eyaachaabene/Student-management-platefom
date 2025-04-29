package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.AssignmentModel;

public class AssignmentDAO { 
	public static List<AssignmentModel> getAssignmentsByStudentId(int studentId) {
		 String query = "SELECT a.assignment_id, a.title, a.description, a.deadline " +
                 "FROM student_assignments sa " +
                 "JOIN assignments a ON sa.assignment_id = a.assignment_id " +
                 "WHERE sa.student_id = ?";
        List<AssignmentModel> assignments = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set the student ID parameter
            stmt.setInt(1, studentId);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AssignmentModel assignment = new AssignmentModel();
                    assignment.setAssignmentId(rs.getInt("assignment_id"));
                    assignment.setCourseId(rs.getInt("course_id"));
                    assignment.setTitle(rs.getString("title"));
                    assignment.setDescription(rs.getString("description"));
                    assignment.setDeadline(rs.getString("deadline"));

                  

                    assignments.add(assignment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignments;
    }

	// Method to update the assignment submission
    public boolean updateAssignmentSubmission(int studentId, int assignmentId, String submissionPdfPath) {
        String query = "UPDATE student_assignments SET submission_pdf = ?, submission_date = NOW() " +
                       "WHERE student_id = ? AND assignment_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the submission PDF path
            stmt.setString(1, submissionPdfPath);
            stmt.setInt(2, studentId);  // Student ID
            stmt.setInt(3, assignmentId);  // Assignment ID

            // Execute the update
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Return true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if the update failed
    }
 // Method to convert String deadline to java.sql.Date for database insertion
    private java.sql.Date convertToSqlDate(String deadlineStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(deadlineStr);
            return new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertAssignment(AssignmentModel assignment) {
        String sql = "INSERT INTO assignments (course_id, title, description, deadline) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, assignment.getCourseId());
            stmt.setString(2, assignment.getTitle());
            stmt.setString(3, assignment.getDescription());
            
            // Convert deadline to SQL Date
            stmt.setDate(4, convertToSqlDate(assignment.getDeadline()));  // Convert from String to SQL Date
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get an assignment by its ID
    public AssignmentModel getAssignmentById(int assignmentId) {
        String query = "SELECT * FROM assignments WHERE assignment_id = ?";
        AssignmentModel assignment = null;
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, assignmentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                assignment = new AssignmentModel(); // Create a new AssignmentModel object
                assignment.setAssignmentId(rs.getInt("assignment_id"));
                assignment.setCourseId(rs.getInt("course_id"));
                assignment.setTitle(rs.getString("title"));
                assignment.setDescription(rs.getString("description"));
                assignment.setDeadline(rs.getString("deadline"));  // Keep as String
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return assignment;  // Return the assignment or null if not found
    }

}
