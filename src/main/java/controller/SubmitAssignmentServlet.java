package controller;

import DAO.AssignmentDAO;
import DAO.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.AssignmentModel;

import java.io.*;
import java.nio.file.*;

@WebServlet("/SubmitAssignmentServlet")
@MultipartConfig  // Required for file upload

public class SubmitAssignmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
      
        // Handle file upload (submission)
        Part filePart = request.getPart("submission");
        
        if (filePart != null) {
            String fileName = filePart.getSubmittedFileName();
            
            // Ensure the 'uploads' directory exists
            String uploadDir = getServletContext().getRealPath("/uploads");
            File uploadDirectory = new File(uploadDir);
            
            // If the directory doesn't exist, create it
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();  // Create the directory if it doesn't exist
            }
            
            // Define the full path to the uploaded file
            String uploadPath = uploadDir + File.separator + fileName;
            
            // Save the file to the server
            File file = new File(uploadPath);
            filePart.write(file.getAbsolutePath());

            // Update the submission in the database
            AssignmentDAO assignmentDAO = new AssignmentDAO();
            boolean updated = assignmentDAO.updateAssignmentSubmission(studentId, assignmentId, uploadPath);

            if (updated) {
                // Redirect to the StudentServlet to load the dashboard after successful submission
                response.sendRedirect("StudentServlet?studentId=" + studentId);
            } else {
                // Handle failure to update the submission
                response.sendRedirect("message.jsp?message=Submission Failed");
            }
        } else {
            // Handle failure to upload the file
            response.sendRedirect("message.jsp?message=No file uploaded");
        }
    }
}

