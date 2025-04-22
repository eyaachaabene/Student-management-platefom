package controller;

import DAO.StudentDAO;
import model.AssignmentModel;
import model.CourseModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get student credentials from the form submission
        String studentIdStr = request.getParameter("student_id");
        String password = request.getParameter("password"); // You can hash and compare passwords in a real application

        if (studentIdStr != null && password != null) {
            try {
                int studentId = Integer.parseInt(studentIdStr);

                // Validate the student (for now, we are skipping the password validation)
                // You can add your password validation logic here

                // If student is valid, fetch the courses for this student
                StudentDAO studentDAO = new StudentDAO();
                List<CourseModel> courses = studentDAO.getAllCourses(studentId);

                // Store the student ID in session for further requests
                HttpSession session = request.getSession();
                session.setAttribute("studentId", studentId);

                // Set the courses list as a request attribute to be used in JSP
                request.setAttribute("courses", courses);

                // Forward to the studentDashboard.jsp page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Dashboard.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                // Handle invalid student ID format
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student ID.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Student ID and password must be provided.");
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Integer studentId = (Integer) session.getAttribute("studentId");

	    if (studentId == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }

	    // Fetch courses and assignments
	    StudentDAO studentDAO = new StudentDAO();
	    System.out.println("meme");
	    List<CourseModel> courses = studentDAO.getAllCourses(studentId);
	    System.out.println("meme");
	    List<AssignmentModel> assignments = studentDAO.getAssignmentsByStudentId(studentId);

	    // Set attributes for courses and assignments
	    request.setAttribute("courses", courses);
	    request.setAttribute("assignments", assignments);

	    // Forward to dashboard JSP
	    request.getRequestDispatcher("/WEB-INF/Dashboard.jsp").forward(request, response);
	}

}