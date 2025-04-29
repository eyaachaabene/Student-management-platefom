package controller;

import DAO.StudentDAO;
import DAO.SubjectDAO;
import model.AssignmentModel;
import model.StudentModel;
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
        // Get the studentId from the request parameters
        String studentIdParam = request.getParameter("studentId");
        if (studentIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing studentId parameter.");
            return;
        }

        try {
            int studentId = Integer.parseInt(studentIdParam);

            // Fetch the student's name and level, along with their subjects and assignments
            StudentDAO studentDAO = new StudentDAO();
            SubjectDAO subjectDAO = new SubjectDAO();

            // Retrieve the student's details (name and level)
            StudentModel student = studentDAO.getStudentById(studentId);
            List<SubjectModel> subjects = subjectDAO.getSubjectsByStudentId(studentId); // Fetch subjects
            List<AssignmentModel> assignments = studentDAO.getAssignmentsByStudentId(studentId); // Fetch assignments

            // Set the attributes for the student, subjects, and assignments
            request.setAttribute("studentID", studentId);
            request.setAttribute("studentName", student.getName());
            request.setAttribute("studentLevel", student.getLevel());
            request.setAttribute("subjects", subjects);
            request.setAttribute("assignments", assignments);

            // Forward to Dashboard.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid studentId format.");
        }
    }
}
