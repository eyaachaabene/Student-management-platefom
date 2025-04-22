package controller;

import DAO.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.AssignmentModel;

import java.io.*;
import java.nio.file.*;

@WebServlet("/SubmitAssignmentServlet")
public class SubmitAssignmentServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
        Part filePart = request.getPart("submission");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = "D:\\uploads\\assignments\\" + fileName;

        // Save the submitted PDF
        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }

        // Update the database with the student's submission link
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.submitAssignment(assignmentId, filePath);

        response.sendRedirect("dashboard.jsp");
    }
}
