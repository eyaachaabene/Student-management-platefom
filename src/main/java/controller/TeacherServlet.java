package controller;

import java.io.IOException;
import java.util.List;

import DAO.TeacherDAO;
import DAO.SubjectDAO;
import model.TeacherModel;
import model.SubjectModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get teacherId from the request parameters
        String teacherIdParam = request.getParameter("teacherId");

        if (teacherIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Teacher ID is required.");
            return;
        }

        try {
            int teacherId = Integer.parseInt(teacherIdParam);
            // Call the method to load the teacher dashboard
            loadTeacherDashboard(teacherId, request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid teacher ID format.");
        }
    }

    // Handle POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests, typically for form submissions or actions
        // For simplicity, let's delegate to doGet
        doGet(request, response); // For now, let's just call doGet for consistency
    }

    // Method to load teacher dashboard data
    public void loadTeacherDashboard(int teacherId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Loading teacher dashboard for teacherId: " + teacherId);
        
        // Fetch teacher data and associated subjects
        TeacherDAO teacherDAO = new TeacherDAO();
        SubjectDAO subjectDAO = new SubjectDAO();

        TeacherModel teacher = teacherDAO.getTeacherById(teacherId);
        List<SubjectModel> subjects = subjectDAO.getSubjectsByTeacherId(teacherId);

        // Check if the teacher exists
        if (teacher == null) {
            request.setAttribute("error", "Teacher not found.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Set the teacher and subjects as request attributes
        request.setAttribute("teacher", teacher);
        request.setAttribute("subjects", subjects);
        request.setAttribute("teacherid", teacherId);
        // Forward the request to the teacher dashboard JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacherDashboard.jsp");
        dispatcher.forward(request, response);
    }

 
}
