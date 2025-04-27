package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssignmentDAO {
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

}
