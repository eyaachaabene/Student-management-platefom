package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AssignmentModel;

public class AssignmentFetcher {
 
    // Query to fetch assignments for a specific student
    private static final String QUERY = "SELECT a.assignment_id, a.title, a.description, a.deadline " +
                                        "FROM student_assignments sa " +
                                        "JOIN assignments a ON sa.assignment_id = a.assignment_id " +
                                        "WHERE sa.student_id = ?";

    public static void main(String[] args) {
        // Fetch assignments for student_id = 2
        int studentId = 2;
        List<AssignmentModel> assignments = getAssignmentsByStudentId(studentId);

        // Display the assignments
        if (assignments.isEmpty()) {
            System.out.println("No assignments found for student ID: " + studentId);
        } else {
            for (AssignmentModel assignment : assignments) {
                System.out.println("Assignment ID: " + assignment.getAssignmentId());
                System.out.println("title: " + assignment.getTitle());
                System.out.println("Description: " + assignment.getDescription());
                System.out.println("Deadline: " + assignment.getDeadline());
                System.out.println("-----------------------------------");
            }
        }
    }

    public static List<AssignmentModel> getAssignmentsByStudentId(int studentId) {
        List<AssignmentModel> assignments = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(QUERY)) {

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

   
}
