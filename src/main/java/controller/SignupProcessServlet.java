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
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role");

        // Check if password matches confirm password
        if (!password.equals(confirmPassword)) {
            // Redirect with error message in the query string
            response.sendRedirect("signup.jsp?error=Passwords do not match");
            return;
        }

        // Call UserDAO to insert the user into the database
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.signUp(email, password, role);  // Insert user and get userId

        if (userId > 0) {
            // Redirect to the appropriate sign-up form based on role
            if ("student".equals(role)) {
                response.sendRedirect("studentSignup.jsp?userId=" + userId);
            } else {
                response.sendRedirect("teacherSignup.jsp?userId=" + userId);
            }
        } else {
            // Handle failure to insert the user
            // Redirect with error message in the query string
            response.sendRedirect("signup.jsp?error=The user exists. Please login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Since signup should only happen via POST, redirect to the signup page
        response.sendRedirect("signup.jsp");
    }
}
