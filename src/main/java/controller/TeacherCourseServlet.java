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
@WebServlet("/TeacherCourseServlet")
public class TeacherCourseServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("subjectId"));
        System.out.println(request.getParameter("teacherid"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
       
        int teacherid = Integer.parseInt(request.getParameter("teacherid"));
        System.out.println("teacher id is "+teacherid);
        // Fetch courses for the subject
        CourseDAO courseDAO = new CourseDAO();
        List<CourseModel> courses = courseDAO.getCoursesBySubjectId(subjectId);

        // Set courses as a request attribute
        request.setAttribute("courses", courses);
        request.setAttribute("subjectId", subjectId);  // Optionally pass subjectId to the next page
    
        request.setAttribute("teacherId", teacherid);        // Forward to the courses JSP page
        request.getRequestDispatcher("teachercourseServlet.jsp").forward(request, response);
    }
}
