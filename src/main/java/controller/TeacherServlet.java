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




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "loadDashboard"; // Default action
        }

        // Get userId from request parameter or session
        String userIdParam = request.getParameter("userId");
        if (userIdParam == null) {
            // Fallback to session attribute if userId is not in request
            userIdParam = (String) request.getSession().getAttribute("userId");
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid user ID.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        System.out.println("User ID in doGet: " + userId);

        switch (action) {
            case "loadDashboard":
                loadTeacherDashboard(userId, request, response);
                break;
            case "viewSubjects":
                viewSubjects(userId, request, response);
                break;
            case "viewCourses":
                viewCoursesForSubject(request, response);
                break;
            case "markAttendance":
                markAttendance(request, response);
                break;
            default:
                loadTeacherDashboard(userId, request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Delegate to doGet for simplicity
    }

    public void loadTeacherDashboard(int userId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Loading teacher dashboard for userId: " + userId);
         TeacherDAO teacherDAO=new TeacherDAO();
         SubjectDAO subjectDAO = new SubjectDAO();
        TeacherModel teacher = teacherDAO.getTeacherById(userId);
        List<SubjectModel> subjects = subjectDAO.getSubjectsByTeacherId(userId);

        if (teacher == null) {
            request.setAttribute("error", "Teacher not found.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("teacher", teacher);
        request.setAttribute("subjects", subjects);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacherDashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void viewSubjects(int userId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	SubjectDAO subjectDAO = new SubjectDAO();
    	List<SubjectModel> subjects = subjectDAO.getSubjectsByTeacherId(userId);
        request.setAttribute("subjects", subjects);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/teacherSubjects.jsp");
        dispatcher.forward(request, response);
    }

    private void viewCoursesForSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement later: Fetch courses for a subject
        response.getWriter().println("View courses for a subject (to be implemented)");
    }

    private void markAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement later: Mark attendance
        response.getWriter().println("Mark attendance for a subject (to be implemented)");
    }
}