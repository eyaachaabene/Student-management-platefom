package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AssignmentModel;
import model.TeacherModel;
public class AssignmentFetcher {
 
    // Query to fetch assignments for a specific student
    private static final String QUERY = "SELECT a.assignment_id, a.subject, a.description, a.deadline, a.teacher_id " +
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
                System.out.println("Subject: " + assignment.getSubject());
                System.out.println("Description: " + assignment.getDescription());
                System.out.println("Deadline: " + assignment.getDeadline());
                System.out.println("Teacher: " + assignment.getTeacher().getName());
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
                    assignment.setSubject(rs.getString("subject"));
                    assignment.setDescription(rs.getString("description"));
                    assignment.setDeadline(rs.getString("deadline"));

                    // Fetch teacher information
                    TeacherModel teacher = new TeacherModel();
                    teacher.setTeacherId(rs.getInt("teacher_id"));
                    teacher.setName(getTeacherNameById(teacher.getTeacherId()));  // Fetch teacher name using teacher_id
                    assignment.setTeacher(teacher);

                    assignments.add(assignment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignments;
    }

    public static String getTeacherNameById(int teacherId) {
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
