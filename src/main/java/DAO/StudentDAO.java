package DAO;

import model.StudentModel;
import model.SubjectModel;
import model.TeacherModel;
import model.AssignmentModel;
import model.CourseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Sign Up (Insert Student)
	
	public boolean insertStudent(int userId, String username,   Date dob, String level) {
	    String query = "INSERT INTO students (student_id, username, date_of_birth, level) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setInt(1, userId);  // userId is used as student_id (foreign key reference)
	        stmt.setString(2, username);  // username
	        stmt.setDate(3, dob);  // date_of_birth
	        stmt.setString(4, level);  // level (Freshman, Sophomore, etc.)

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;  // Return true if the student was inserted successfully
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;  // Return false if there was an error
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
	 public List<SubjectModel> getSubjectsByStudentId(int studentId) {
		    List<SubjectModel> subjects = new ArrayList<>();
		    String query = "SELECT s.subject_id, s.subject_name " +
		                   "FROM subject s " +
		                   "JOIN student_subjects ss ON s.subject_id = ss.subject_id " +
		                   "WHERE ss.student_id = ?";

		    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        stmt.setInt(1, studentId);

		        ResultSet resultSet = stmt.executeQuery();
		        while (resultSet.next()) {
		            subjects.add(new SubjectModel(
		                    resultSet.getInt("subject_id"),
		                    resultSet.getString("subject_name")
		            ));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return subjects;
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