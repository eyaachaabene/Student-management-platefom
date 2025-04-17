package DAO;

import model.StudentModel;
import model.CourseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Method to get all courses for a student
    public List<CourseModel> getAllCourses(int studentId) {
        List<CourseModel> courses = new ArrayList<>();
        String query = "SELECT c.course_id, c.course_name, c.course_description, c.pdf_path " +
                       "FROM courses c " +
                       "JOIN student_courses sc ON c.course_id = sc.course_id " +
                       "WHERE sc.student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                courses.add(new CourseModel(
                        resultSet.getInt("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("pdf_path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Method to get a specific course's PDF path
    public String getCoursePdfPath(int courseId) {
        String query = "SELECT pdf_path FROM courses WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("pdf_path");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no course found
    }

    // Method to enroll a student in a course
    public void enrollStudentInCourse(int studentId, int courseId) {
        // Ensure the student and course exist before inserting into student_courses
        if (isStudentExist(studentId) && isCourseExist(courseId)) {
            String query = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentId);
                stmt.setInt(2, courseId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student " + studentId + " enrolled in course " + courseId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Student or Course does not exist, cannot enroll.");
        }
    }

    // Method to check if a student exists
    private boolean isStudentExist(int studentId) {
        String query = "SELECT 1 FROM students WHERE student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();  // Returns true if the student exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to check if a course exists
    private boolean isCourseExist(int courseId) {
        String query = "SELECT 1 FROM courses WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();  // Returns true if the course exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}