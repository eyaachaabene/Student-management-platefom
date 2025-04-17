package controller;



import DAO.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EnrollStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the student ID and course ID from the request parameters
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        int courseId = Integer.parseInt(request.getParameter("course_id"));

        // Create an instance of the StudentDAO
        StudentDAO studentDAO = new StudentDAO();

        // Enroll the student in the course
        studentDAO.enrollStudentInCourse(studentId, courseId);

        // Redirect the student to the dashboard or show a success message
        response.sendRedirect("studentDashboard.jsp");
    }
}
