package controller;


import java.io.*;
import java.nio.file.*;

import DAO.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ViewCoursePdf")
public class ViewCoursePdf extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        // Fetch PDF path for the course using courseId (you can get this from the database or course object)
        StudentDAO studentDAO = new StudentDAO();
        String pdfPath = studentDAO.getCoursePdfPath(courseId); // You need to implement this method

        if (pdfPath != null) {
        	
            // Set response content type to PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=course.pdf");

            // Read and send the PDF file
            Path pdfFilePath = Paths.get(pdfPath);
            Files.copy(pdfFilePath, response.getOutputStream());
            
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "PDF not found for this course.");
        }
    }
}
