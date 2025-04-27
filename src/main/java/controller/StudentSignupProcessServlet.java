package controller;


import java.io.IOException;


import java.sql.Connection;
import java.sql.Date;
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
import DAO.StudentDAO;
@WebServlet("/studentSignupProcess")
public class StudentSignupProcessServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int userId = Integer.parseInt(request.getParameter("userId"));
	        String username = request.getParameter("username");  // Full name (student's username)
	        String dateOfBirthStr = request.getParameter("date_of_birth");  // Date of birth as a string
	        String level = request.getParameter("level");  // Level (Freshman, Sophomore, etc.)

	        // Convert the date_of_birth string into a java.sql.Date
	        java.sql.Date dateOfBirth = null;
	        try {
	            dateOfBirth = java.sql.Date.valueOf(dateOfBirthStr);  // Convert the string to java.sql.Date
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
	            request.getRequestDispatcher("/studentSignup.jsp").forward(request, response);
	            return;
	        }

	        // Call StudentDAO to insert the student data into the database
	        StudentDAO studentDAO = new StudentDAO();
	        boolean studentInserted = studentDAO.insertStudent(userId,username, dateOfBirth, level);

	        if (studentInserted) {
	            // Redirect to the student dashboard or a welcome page
	            response.sendRedirect("login.jsp");
	        } else {
	            // Handle failure to insert student data
	            request.setAttribute("error", "Student sign-up failed. Please try again.");
	            request.getRequestDispatcher("/studentSignup.jsp").forward(request, response);
	        }
	    }
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect("signup.jsp");
}}