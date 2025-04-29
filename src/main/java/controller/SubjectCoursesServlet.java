package controller;

import java.io.IOException;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import DAO.*;
@WebServlet("/SubjectCoursesServlet")
public class SubjectCoursesServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        // Fetch courses for the subject
        CourseDAO courseDAO = new CourseDAO();
        List<CourseModel> courses = courseDAO.getCoursesBySubjectId(subjectId);

        // Set courses as a request attribute
        request.setAttribute("courses", courses);
        request.setAttribute("subjectId", subjectId);  // Optionally pass subjectId to the next page
        request.setAttribute("studentId", studentId);
        // Forward to the courses JSP page
        request.getRequestDispatcher("SubjectCourses.jsp").forward(request, response);
    }
}
