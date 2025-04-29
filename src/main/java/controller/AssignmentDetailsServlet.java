package controller;

import DAO.AssignmentDAO;
import model.AssignmentModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/AssignmentDetailsServlet")
public class AssignmentDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve assignmentId and studentId from the request parameters
        String assignmentIdParam = request.getParameter("assignmentId");
        String studentIdParam = request.getParameter("studentId");

        // Validate parameters
        if (assignmentIdParam == null || studentIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing assignmentId or studentId.");
            return;  // Stop further processing if parameters are missing
        }

        try {
            int assignmentId = Integer.parseInt(assignmentIdParam);
            int studentId = Integer.parseInt(studentIdParam);

            // Create AssignmentDAO instance to fetch assignment details
            AssignmentDAO assignmentDAO = new AssignmentDAO();
            AssignmentModel assignmentModel = assignmentDAO.getAssignmentById(assignmentId);

            if (assignmentModel == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Assignment not found.");
                return;  // Stop further processing if the assignment is not found
            }

            // Set assignment and student ID as request attributes
            request.setAttribute("assignment", assignmentModel);
            request.setAttribute("studentID", studentId);

            // Forward the request to the assignment details JSP
            request.getRequestDispatcher("AssignmentDetails.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid number format for assignmentId or studentId
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid assignmentId or studentId format.");
        }
    }
}
