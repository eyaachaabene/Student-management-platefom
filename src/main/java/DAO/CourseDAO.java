package DAO;
import model.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDAO {

	public List<CourseModel> getCoursesBySubjectAndStudent(int subjectId, int studentId) {
	    List<CourseModel> courses = new ArrayList<>();
	    String query = "SELECT c.course_id, c.course_name, c.course_description, c.pdf_path " +
	                   "FROM courses c " +
	                   "JOIN student_courses sc ON c.course_id = sc.course_id " +
	                   "WHERE c.subjectid = ? AND sc.student_id = ?";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, subjectId);
	        stmt.setInt(2, studentId);

	        ResultSet resultSet = stmt.executeQuery();
	        while (resultSet.next()) {
	            courses.add(new CourseModel(
	                    resultSet.getInt("course_id"),
	                    resultSet.getString("course_name"),
	                    resultSet.getString("course_description"),
	                    resultSet.getString("pdf_path")
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return courses;
	}

}
