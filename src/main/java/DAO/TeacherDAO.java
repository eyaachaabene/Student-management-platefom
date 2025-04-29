package DAO;

import model.StudentModel;
import model.TeacherModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
	

    public TeacherDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean insertTeacher(int userId, String name, String department) {
        String query = "INSERT INTO teachers (teacher_id, name, department) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, department);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTeacherNameById(int teacherId) {
        String query = "SELECT name FROM teachers WHERE teacher_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("name") : "Unknown";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

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
        TeacherModel teacher = new TeacherModel();
        String query = "SELECT * FROM teachers WHERE teacher_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
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
    
    public List<StudentModel> getStudentsByTeacherAndSubject(int teacherId, int subjectId) {
    	System.out.println("/n ");
    	System.out.println(teacherId);
    	System.out.println(subjectId);
        List<StudentModel> students = new ArrayList<>();
        String sql = "SELECT s.student_id, s.username, u.email, u.password, u.address, s.level " +
                "FROM students s " +
                "JOIN subject_student ss ON s.student_id = ss.student_id " +
                "JOIN subjects sub ON ss.subject_id = sub.subject_id " +
                "JOIN users u ON s.student_id = u.id " +  // Join users table correctly
                "WHERE ss.subject_id = ? AND sub.teacher_id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, subjectId);
            stmt.setInt(2, teacherId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create the student object with the values from the database
            	System.out.println(rs.getInt("student_id"));
                StudentModel student = new StudentModel(
                    rs.getInt("student_id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("username"),  
                    null,                       
                    rs.getString("address"),
                    rs.getString("level")
                );
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
 
        // Method to mark attendance
        public void markAttendance(int teacherId, int subjectId, int studentId, boolean isPresent) {
            // Step 1: Fetch the ss_id from the subject_student table
            String ssIdSql = "SELECT ss.ss_id FROM subject_student ss " +
                             "WHERE ss.subject_id = ? AND ss.student_id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(ssIdSql)) {

                stmt.setInt(1, subjectId);
                stmt.setInt(2, studentId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int ssId = rs.getInt("ss_id");  // Fetch the ss_id

                    // Step 2: Insert into the attendance table with the ss_id and current time
                    String insertSql = "INSERT INTO attendance (ss_id, att_time) VALUES (?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, ssId);  // Use ss_id from subject_student
                        insertStmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));  // Current time for att_time
                        insertStmt.executeUpdate();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    

    }
