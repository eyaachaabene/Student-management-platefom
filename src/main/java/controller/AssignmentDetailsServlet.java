package controller;

import DAO.StudentDAO;
import model.AssignmentModel;
import model.TeacherModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;

@WebServlet("/AssignmentDetailsServlet")
public class AssignmentDetailsServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get assignmentId from the request parameter
        int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
        AssignmentModel assignmentModel = new AssignmentModel();
        // Fetch the assignment details using the AssignmentDAO or StudentDAO
        AssignmentModel assignment=null;
		try {
			 assignment = assignmentModel.getAssignmentById(assignmentId);		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Set assignment object as request attribute to pass to the JSP
        request.setAttribute("assignment", assignment);

        // Forward the request to the assignment details JSP
        request.getRequestDispatcher("/WEB-INF/assignmentDetails.jsp").forward(request, response);
    }
}
