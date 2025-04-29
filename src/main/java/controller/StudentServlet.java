package controller;

import DAO.StudentDAO;
import DAO.SubjectDAO;
import model.AssignmentModel;

import model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    public void loadStudentDashboard(int studentId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the courses and assignments for the student
        StudentDAO studentDAO = new StudentDAO();
        SubjectDAO subjectDAO=new SubjectDAO();
        List<SubjectModel> subjects = subjectDAO.getSubjectsByStudentId(studentId); // Fetch subjects
        List<AssignmentModel> assignments = studentDAO.getAssignmentsByStudentId(studentId);

        // Set courses and assignments as request attributes
        request.setAttribute("subjects", subjects);
        request.setAttribute("assignments", assignments);
        request.setAttribute("studentID",studentId);
        // Forward to the student dashboard JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
        dispatcher.forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
	}

}