package controller;

import java.io.IOException;
import java.util.*;
import model.*;
import DAO.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.*;

@WebServlet("/TeacherMarkAttendanceServlet")
public class TeacherMarkAttendanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherid"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));

        // Fetch the students for the given teacher and subject
        TeacherDAO teacherDAO = new TeacherDAO();
        List<StudentModel> students = teacherDAO.getStudentsByTeacherAndSubject(teacherId, subjectId);

        request.setAttribute("students", students);
        request.setAttribute("teacherId", teacherId);
        request.setAttribute("subjectId", subjectId);

        // Forward to JSP for marking attendance
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacherMarkAttendance.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));

        // Loop through the students and get the attendance data
        Map<Integer, Boolean> attendanceMap = new HashMap<>();
        for (String param : request.getParameterMap().keySet()) {
            if (param.startsWith("attendance_")) {
                int studentId = Integer.parseInt(param.split("_")[1]);
                boolean isPresent = request.getParameter(param) != null && request.getParameter(param).equals("1");

                // Store the attendance status (present or not) for each student
                attendanceMap.put(studentId, isPresent);
            }
        }

        // Insert attendance data into the database
        TeacherDAO teacherDAO = new TeacherDAO();
        for (Map.Entry<Integer, Boolean> entry : attendanceMap.entrySet()) {
            int studentId = entry.getKey();
            boolean isPresent = entry.getValue();
            teacherDAO.markAttendance(teacherId, subjectId, studentId, isPresent);
        }

        TeacherServlet teacherServlet = new TeacherServlet();

        teacherServlet.loadTeacherDashboard(teacherId, request, response);
    }

}
