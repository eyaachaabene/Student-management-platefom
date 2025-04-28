package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.TeacherDAO;

@WebServlet("/teacherSignupProcess")
public class TeacherSignupProcessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int userId = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("name");
        String department = request.getParameter("department");

        TeacherDAO teacherDAO = new TeacherDAO();
        boolean teacherInserted = teacherDAO.insertTeacher(userId, name, department);


        if (teacherInserted) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Teacher registration failed. Please try again.");
            request.getRequestDispatcher("/teacherSignup.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Prevent direct access via GET
        response.sendRedirect("signup.jsp");
    }
}