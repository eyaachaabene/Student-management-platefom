package DAO;

import DAO.TeacherDAO;
import model.TeacherModel;

import java.util.List;

public class TeacherDAOTest {
    public static void main(String[] args) {
        TeacherDAO teacherDAO = new TeacherDAO();

        // Test: Get name by ID
        System.out.println("=== Get Teacher Name by ID ===");
        System.out.println("Teacher with ID 3: " + teacherDAO.getTeacherNameById(3));
        System.out.println("Teacher with ID 4: " + teacherDAO.getTeacherNameById(4));
        System.out.println("Teacher with ID 12: " + teacherDAO.getTeacherNameById(12));
        System.out.println();

        // Test: Get full Teacher info by ID
        System.out.println("=== Get Full Teacher Info by ID ===");
        TeacherModel teacher3 = teacherDAO.getTeacherById(3);
        TeacherModel teacher4 = teacherDAO.getTeacherById(4);
        TeacherModel teacher12 = teacherDAO.getTeacherById(12);

        if (teacher3 != null)
            System.out.println("Teacher 3: " + teacher3.getName() + ", " + teacher3.getDepartment());
        if (teacher4 != null)
            System.out.println("Teacher 4: " + teacher4.getName() + ", " + teacher4.getDepartment());
        if (teacher12 != null)
            System.out.println("Teacher 12: " + teacher12.getName() + ", " + teacher12.getDepartment());
        System.out.println();

        // Test: Get all teachers
        System.out.println("=== Get All Teachers ===");
        List<TeacherModel> allTeachers = teacherDAO.getAllTeachers();
        for (TeacherModel t : allTeachers) {
            System.out.println("ID: " + t.getTeacherId() + ", Name: " + t.getName() + ", Department: " + t.getDepartment());
        }
    }
}
