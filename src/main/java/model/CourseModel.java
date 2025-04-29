package model;

public class CourseModel {
    private int courseId;
    private String courseName;
    private String coursedescription;
    private String pdfPath;

    // Constructor
    public CourseModel(int courseId,String coursedescription,String courseName, String pdfPath) {
    	this.coursedescription=coursedescription;
        this.courseId = courseId;
        this.courseName = courseName;
        this.pdfPath = pdfPath;
    }

    public CourseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

	public String getCoursedescription() {
		return coursedescription;
	}

	public void setCoursedescription(String coursedescription) {
		this.coursedescription = coursedescription;
	}
}
