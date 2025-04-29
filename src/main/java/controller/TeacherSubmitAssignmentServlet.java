package controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.AssignmentDAO;
import model.AssignmentModel;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/TeacherSubmitAssignmentServlet")
public class TeacherSubmitAssignmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get courseId from the request parameters and forward to the JSP page
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        request.setAttribute("courseId", courseId);
        
        // Forward the request to the JSP to create the assignment
        request.getRequestDispatcher("teacherSubmitAssignment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data from the request
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int teacherid = Integer.parseInt(request.getParameter("teacherid"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String deadline = request.getParameter("deadline");

        // Create a new AssignmentModel object
        AssignmentModel assignment = new AssignmentModel();
        assignment.setCourseId(courseId);
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setDeadline(deadline);  // Keep deadline as String

        // Call the insertAssignment method from the DAO
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        boolean isAssigned = assignmentDAO.insertAssignment(assignment);
        System.out.println("assignment ="+isAssigned);

        // If insertion was successful
        if (isAssigned) {
            System.out.println("assignment assigned");

            // Redirect to the TeacherCourseServlet with the necessary parameters
            response.sendRedirect("TeacherCourseServlet?teacherid=" + teacherid + "&subjectId=" + subjectId);
        } else {
            // If insertion failed, show an error message
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create assignment.");
        }
    }
}