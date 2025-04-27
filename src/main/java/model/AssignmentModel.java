package model;

import java.sql.*;

import DAO.DatabaseConnection;

public class AssignmentModel {
    private int assignmentId;
    private int courseId;
    private String title;
	private String description;
    private String deadline;
    private TeacherModel teacher;
	public AssignmentModel(int assignmentId,int courseId, String subject, String description, String deadline,
			TeacherModel teacher) {
		super();
		this.setCourseId(courseId);
		this.assignmentId = assignmentId;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.teacher = teacher;
	}
	



    public AssignmentModel() {
		super();
	}




	// Getters and setters
    public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setSubject(String subject) {
		this.title = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public TeacherModel getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherModel teacher) {
		this.teacher = teacher;
	}


    @Override
	public String toString() {
		return "AssignmentModel [assignmentId=" + assignmentId + ", subject=" + title + ", description=" + description
				+ ", deadline=" + deadline + ", teacher=" + teacher + "]";
	}




	public  AssignmentModel getAssignmentById(int assignmentId) {
        String query = "SELECT * FROM assignments WHERE assignment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, assignmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.assignmentId = rs.getInt("assignment_id");
                this.courseId = rs.getInt("course_id");
                this.title = rs.getString("subject");
                this.description = rs.getString("description");
                this.deadline = rs.getString("deadline");

                // Fetch teacher information
                TeacherModel teacher = new TeacherModel();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                this.teacher = teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }




	public int getCourseId() {
		return courseId;
	}




	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
}
