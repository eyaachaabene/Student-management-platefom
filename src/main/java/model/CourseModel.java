package model;

public class CourseModel {
    private int courseId;
    private String courseName;
    private String pdfPath;

    // Constructor
    public CourseModel(int courseId, String courseName, String pdfPath) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.pdfPath = pdfPath;
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
}
