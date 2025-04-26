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

	
    // Function to load student dashboard by studentId
    public void loadStudentDashboard(int studentId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the courses and assignments for the student
        StudentDAO studentDAO = new StudentDAO();
        List<CourseModel> courses = studentDAO.getAllCourses(studentId);
        List<AssignmentModel> assignments = studentDAO.getAssignmentsByStudentId(studentId);

        // Set courses and assignments as request attributes
        request.setAttribute("courses", courses);
        request.setAttribute("assignments", assignments);

        // Forward to the student dashboard JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
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