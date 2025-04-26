package DAO;

import model.StudentModel;
import model.TeacherModel;
import model.AssignmentModel;
import model.CourseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Sign Up (Insert Student)
	
    public boolean signUp(String username, String password, String fullName, String email, Date dob) {
        try {
        	Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO students (username, password, full_name, email, date_of_birth) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password); // You should hash the password before storing it
            statement.setString(3, fullName);
            statement.setString(4, email);
            statement.setDate(5, dob);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login (Select Student)
    public boolean login(String username, String password) {
        try {
        	Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM students WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If a student exists, it returns true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	 public void submitAssignment(int assignmentId, String filePath) {
	        String query = "UPDATE student_assignments SET submission_pdf = ? WHERE assignment_id = ?";
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, filePath);
	            stmt.setInt(2, assignmentId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
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
 // Method to get all assignments for a student
    public List<AssignmentModel> getAssignmentsByStudentId(int studentId) {
        List<AssignmentModel> assignments = new ArrayList<>();
        System.out.println("eya");
        String query = "SELECT a.assignment_id, a.subject, a.description, a.deadline, a.teacher_id " +
                       "FROM assignments a " +
                       "JOIN student_assignments sa ON a.assignment_id = sa.assignment_id " +
                       "WHERE sa.student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the student ID parameter in the prepared statement
            stmt.setInt(1, studentId);

            // Execute the query
            ResultSet resultSet = stmt.executeQuery();

            // Iterate over the result set and populate the assignments list
            while (resultSet.next()) {
                AssignmentModel assignment = new AssignmentModel();
                assignment.setAssignmentId(resultSet.getInt("assignment_id"));
                assignment.setSubject(resultSet.getString("subject"));
                assignment.setDescription(resultSet.getString("description"));
                assignment.setDeadline(resultSet.getString("deadline"));

                // Fetch teacher information using the teacher_id
                TeacherModel teacher = new TeacherModel();
                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setName(getTeacherNameById(teacher.getTeacherId())); // Get teacher name by teacher_id
                assignment.setTeacher(teacher);

                // Add the assignment to the list
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignments;
    }

 // Method to get the teacher's name by teacher_id
    public String getTeacherNameById(int teacherId) {
        String teacherName = "Unknown";  // Default name if no teacher is found
        String query = "SELECT name FROM teachers WHERE teacher_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId); // Set the teacher_id parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                teacherName = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherName;
    }

}