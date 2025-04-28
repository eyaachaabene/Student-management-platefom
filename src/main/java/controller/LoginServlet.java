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
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Initialize UserDAO
        UserDAO userDAO = new UserDAO();

        // Get user ID using email and password
        int userId = userDAO.getUserIdByEmailAndPassword(email, password);
        System.out.print(userId);
        if (userId != 0) {System.out.print("found student");
            // If a user is found with the given email and password, redirect to the appropriate dashboard
            request.setAttribute("userId", userId); // Save the user ID in the session

            // Fetch user role (optional, you can fetch role here to direct to the appropriate dashboard)
            User user = userDAO.login(email, password);
            String role = user.getRole();
            
            if ("student".equals(role)) {
                // Redirect to the student dashboard
                StudentServlet studentServlet = new StudentServlet();
                
                studentServlet.loadStudentDashboard(userId, request, response);
            } else if ("teacher".equals(role)) {
                // Handle teacher dashboard redirection
                TeacherServlet teacherServlet = new TeacherServlet();

                teacherServlet.loadTeacherDashboard(userId, request, response);
 

            } else {
                response.sendRedirect("error.jsp"); // Handle invalid role
            }}
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to login page if GET request is made
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
