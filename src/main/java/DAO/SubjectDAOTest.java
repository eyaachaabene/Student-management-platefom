package DAO;

import model.SubjectModel;

public class SubjectDAOTest {
    public static void main(String[] args) {
        SubjectDAO subjectDAO = new SubjectDAO();

       
        boolean insertSuccess1 = subjectDAO.insertSubject("French", 4);
        boolean insertSuccess2 = subjectDAO.insertSubject("Spanich", 3);
        boolean insertSuccess3 = subjectDAO.insertSubject("Design Thinking", 12);
        System.out.println("Insert Success: " + insertSuccess1);
        System.out.println("Insert Success: " + insertSuccess2);
        System.out.println("Insert Success: " + insertSuccess3);

      
        System.out.println("\nAll Subjects:");
        subjectDAO.getAllSubjects().forEach(subject -> {
            System.out.println("ID: " + subject.getSubjectId() + ", Name: " + subject.getSubjectName() + ", Teacher ID: " + subject.getTeacherId());
        });

       
        System.out.println("\nSubjects for Teacher ID 12:");
        subjectDAO.getSubjectsByTeacherId(12).forEach(subject -> {
            System.out.println("ID: " + subject.getSubjectId() + ", Name: " + subject.getSubjectName() + ", Teacher ID: " + subject.getTeacherId());
        });

       
        boolean updateSuccess = subjectDAO.updateSubject(1, "Advanced Mathematics 102", 12); // Example, you can check the actual subject_id from your data
        System.out.println("\nUpdate Success: " + updateSuccess);


    }
}
