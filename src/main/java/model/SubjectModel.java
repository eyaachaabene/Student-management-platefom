package model;

public class SubjectModel {
    private int subjectId;
    private String subjectName;
    private int teacherId; 


    public SubjectModel(int subjectId, String subjectName, int teacherId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.teacherId = teacherId;
    }
    
    public SubjectModel(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    

    public SubjectModel(String subjectName, int teacherId) {
        this.subjectName = subjectName;
        this.teacherId = teacherId;
    }

    public SubjectModel() {}

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
