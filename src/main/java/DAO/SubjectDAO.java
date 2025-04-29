package DAO;

import model.SubjectModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    public List<SubjectModel> getAllSubjects() {
        List<SubjectModel> subjects = new ArrayList<>();
        String query = "SELECT * FROM subjects";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SubjectModel subject = new SubjectModel();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setTeacherId(rs.getInt("teacher_id"));
                subjects.add(subject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }


    public List<SubjectModel> getSubjectsByTeacherId(int teacherId) {
        List<SubjectModel> subjects = new ArrayList<>();
        String query = "SELECT * FROM subjects WHERE teacher_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SubjectModel subject = new SubjectModel();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setTeacherId(rs.getInt("teacher_id"));
                    subjects.add(subject);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }


    public boolean insertSubject(String subjectName, int teacherId) {
        String query = "INSERT INTO subjects (subject_name, teacher_id) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, subjectName);
            stmt.setInt(2, teacherId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateSubject(int subjectId, String subjectName, int teacherId) {
        String query = "UPDATE subjects SET subject_name = ?, teacher_id = ? WHERE subject_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, subjectName);
            stmt.setInt(2, teacherId);
            stmt.setInt(3, subjectId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 
    public boolean deleteSubject(int subjectId) {
        String query = "DELETE FROM subjects WHERE subject_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, subjectId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<SubjectModel> getSubjectsByStudentId(int studentId) {
    	
        List<SubjectModel> subjects = new ArrayList<>();
        String sql = "SELECT s.subject_id, s.subject_name " +
                     "FROM subject_student ss " +
                     "JOIN subjects s ON ss.subject_id = s.subject_id " +
                     "WHERE ss.student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	
                SubjectModel subject = new SubjectModel();
                subject.setSubjectId(rs.getInt("subject_id"));
                
                subject.setSubjectName(rs.getString("subject_name")); // Make sure your SubjectModel has subjectName field
                subjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

}

