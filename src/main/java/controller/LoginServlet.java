package controller;

import java.io.*;

import model.*;
import DAO.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle POST requests for login
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Initialize UserDAO
        UserDAO userDAO = new UserDAO();

        // Get user ID using email and password
        int userId = userDAO.getUserIdByEmailAndPassword(email, password);
        System.out.print(userId);

        if (userId != 0) { // User found
            System.out.print("found ");
            // Fetch user details (like role) after successful login
            User user = userDAO.login(email, password);
            String role = user.getRole();
            
            // Set the user ID in the request
            request.setAttribute("userId", userId);

            if ("student".equals(role)) {
                System.out.print("student ");
                // Redirect to the student dashboard servlet
                response.sendRedirect("StudentServlet?studentId=" + userId);

            } else if ("teacher".equals(role)) {
                // Handle teacher dashboard redirection
                response.sendRedirect("TeacherServlet?teacherId=" + userId);

            } else {
                // Handle cases where the role is invalid or not found
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            }

        } else {
            // If the user is not found, redirect to an error page or show an error message
            response.sendRedirect("login.jsp?error=invalid_credentials");
        }
    }

    // Handle GET requests (typically when navigating to the login page)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to login page if GET request is made
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
