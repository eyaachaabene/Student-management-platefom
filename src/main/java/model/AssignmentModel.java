package model;

import java.sql.*;
import java.text.SimpleDateFormat;

import DAO.DatabaseConnection;

public class AssignmentModel {
    private int assignmentId;
    private int courseId;
    private String title;
	private String description;
    private String deadline;

    private TeacherModel teacher;
	public AssignmentModel(int assignmentId,int courseId, String title, String description, String deadline,
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
		// TODO Auto-generated constructor stub
	}





	public AssignmentModel(int assignmentId, int courseId, String title, String description, String deadline) {
		super();
		this.assignmentId = assignmentId;
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
	}





	public int getAssignmentId() {
		return assignmentId;
	}





	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}





	public int getCourseId() {
		return courseId;
	}





	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}





	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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





	public void setDeadline(String deadlineDate) {
		this.deadline = deadlineDate;
	}


	public TeacherModel getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherModel teacher) {
		this.teacher = teacher;
	}


    @Override
	public String toString() {
		return "AssignmentModel [assignmentId=" + assignmentId + ", title=" + title + ", description=" + description
				+ ", deadline=" + deadline + ", teacher=" + teacher + "]";
	}





	
}
