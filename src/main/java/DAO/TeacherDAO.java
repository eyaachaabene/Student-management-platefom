package DAO;

<<<<<<< Updated upstream
=======
import model.AssignmentModel;
>>>>>>> Stashed changes
import model.TeacherModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
<<<<<<< Updated upstream

    public boolean insertTeacher(int userId, String name, String department) {
        String query = "INSERT INTO teachers (teacher_id, name, department) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, department);
            return stmt.executeUpdate() > 0;

=======
    public boolean insertTeacher(int userId, String name, String department) {  // Changed parameter
        String query = "INSERT INTO teachers (teacher_id, name, department) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, department);  
            return stmt.executeUpdate() > 0;
            
>>>>>>> Stashed changes
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

<<<<<<< Updated upstream
    public String getTeacherNameById(int teacherId) {
        String query = "SELECT name FROM teachers WHERE teacher_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("name") : "Unknown";

=======
    // Add this method to match your model's getTeacherNameById functionality
    public String getTeacherNameById(int teacherId) {
        String query = "SELECT name FROM teachers WHERE teacher_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("name") : "Unknown";
            
>>>>>>> Stashed changes
        } catch (SQLException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }
<<<<<<< Updated upstream

    public List<TeacherModel> getAllTeachers() {
        List<TeacherModel> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                TeacherModel teacher = new TeacherModel();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setName(rs.getString("name"));
                teacher.setDepartment(rs.getString("department"));
                teachers.add(teacher);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public TeacherModel getTeacherById(int teacherId) {
        TeacherModel teacher = null;
        String query = "SELECT * FROM teachers WHERE teacher_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                teacher = new TeacherModel();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setName(rs.getString("name"));
                teacher.setDepartment(rs.getString("department"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public boolean updateTeacher(int teacherId, String name, String department) {
        String query = "UPDATE teachers SET name = ?, department = ? WHERE teacher_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setInt(3, teacherId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTeacher(int teacherId) {
        String query = "DELETE FROM teachers WHERE teacher_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
=======
    
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
>>>>>>> Stashed changes
