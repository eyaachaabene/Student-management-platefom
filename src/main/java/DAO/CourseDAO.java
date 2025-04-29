package DAO;
import model.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDAO {

	public List<CourseModel> getCoursesBySubjectId(int subjectId) {
        List<CourseModel> courses = new ArrayList<>();
        String sql = "SELECT course_id, course_name, course_description, pdf_path " +
                     "FROM courses " +
                     "WHERE subjectid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, subjectId); // Set the subjectId parameter
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CourseModel course = new CourseModel();
                course.setCourseId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setCoursedescription(rs.getString("course_description"));
                course.setPdfPath(rs.getString("pdf_path"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }
}
