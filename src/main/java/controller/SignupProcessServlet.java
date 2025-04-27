package controller;
import java.io.IOException;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;  // This is the correct Statement class
import java.sql.ResultSet;
import java.sql.SQLException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.DatabaseConnection;
import DAO.UserDAO;
@WebServlet("/signupProcess")
public class SignupProcessServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role");

        // Check if password matches confirm password
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        // Call UserDAO to insert the user into the database
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.signUp(email, password, role);  // Insert user and get userId
        
        if (userId > 0) {
            // Redirect to the appropriate sign-up form based on role
            if ("student".equals(role)) {
            	System.out.println("hihihihi");
                // Redirect to the student sign-up form
                response.sendRedirect("studentSignup.jsp?userId=" + userId);
            } else {
                // Redirect to the teacher sign-up form (or dashboard)
                response.sendRedirect("teacherSignup.jsp?userId=" + userId);
            }
        } else {
            // Handle failure to insert the user
            request.setAttribute("error", "Sign-up failed. Please try again.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Since signup should only happen via POST, redirect to the signup page
        response.sendRedirect("signup.jsp");
    }

}