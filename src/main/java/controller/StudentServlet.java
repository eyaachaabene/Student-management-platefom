package controller;

import DAO.StudentDAO;
import DAO.SubjectDAO;
import model.AssignmentModel;
import model.SubjectModel;
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

    private static final long serialVersionUID = 1L;

    // Move the logic of loading the dashboard into the doGet method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the studentId from the request parameters or session
        String studentIdParam = request.getParameter("studentId");
        if (studentIdParam == null) {
            // Handle error or redirect if studentId is missing
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing studentId parameter.");
            return;
        }

        try {
            int studentId = Integer.parseInt(studentIdParam);

            // Fetch the subjects and assignments for the student
            StudentDAO studentDAO = new StudentDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            
            List<SubjectModel> subjects = subjectDAO.getSubjectsByStudentId(studentId); // Fetch subjects
            List<AssignmentModel> assignments = studentDAO.getAssignmentsByStudentId(studentId); // Fetch assignments
            
            // Set the subjects, assignments, and studentId as request attributes
            request.setAttribute("subjects", subjects);
            request.setAttribute("assignments", assignments);
            request.setAttribute("studentID", studentId);

            // Forward the request to the Dashboard.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid studentId format
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid studentId format.");
        }
    }
}
