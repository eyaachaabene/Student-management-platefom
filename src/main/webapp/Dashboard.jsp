<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.CourseModel" %>
<%@ page import="model.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
</head>
<body>
    <h1>Student Dashboard</h1>

 <h2>Your Assignments</h2>
<table>
    <thead>
        <tr>
            <th>Title</th>
            <th>Deadline</th>
            
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <% 
            List<AssignmentModel> assignments = (List<AssignmentModel>) request.getAttribute("assignments");
            if (assignments != null && !assignments.isEmpty()) {
                for (AssignmentModel assignment : assignments) {
        %>
        <tr>
              <td><%= assignment.getTitle() %></td >
            <td><%= assignment.getDeadline() %></td>

            <td>

<a href="AssignmentDetailsServlet?assignmentId=<%= assignment.getAssignmentId() %>&studentId=<%= request.getAttribute("studentID") %>">View Details</a>
            </td>
        </tr>
        <%   
                }
            } else {
    %>
        <tr>
            <td colspan="4">No assignments found for this student.</td>
        </tr>
        <% } %>
    </tbody>
</table>
<h2>Your Subjects</h2>
    <table>
        <thead>
            <tr>
                <th>Subject</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<SubjectModel> subjects = (List<SubjectModel>) request.getAttribute("subjects");
                if (subjects != null && !subjects.isEmpty()) {
                    for (SubjectModel subject : subjects) {
            %>
            <tr>
                <td><%= subject.getSubjectName() %></td>
                <td>
                    <!-- Link to view the courses related to this subject -->
                    <a href="SubjectCoursesServlet?subjectId=<%= subject.getSubjectId() %>&studentId=<%= request.getAttribute("studentID") %>">View Courses</a>
                </td>
            </tr>
            <%   
                    }
                } else {
            %>
            <tr>
                <td colspan="2">No subjects found for this student.</td>
            </tr>
            <% } %>
        </tbody>
    </table>

   <h2>Courses</h2>
    <table border="1">
        <tr>
            <th>Course Name</th>
            <th>Action</th>
        </tr>

        <% 
            List<CourseModel> courses = (List<CourseModel>) request.getAttribute("courses");
            if (courses != null && !courses.isEmpty()) {
                for (CourseModel course : courses) {
        %>
        <tr>
            <td><%= course.getCourseName() %></td>
           
            <td>
                <!-- Link to view the PDF -->
                <a href="ViewCoursePdf?courseId=<%= course.getCourseId() %>">View PDF</a>
            </td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="2">No courses found for this student.</td>
        </tr>
        <% } %>
    </table>

    <!-- Display error message if any -->
    <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
    <div style="color: red;">
        <p><%= errorMessage %></p>
    </div>
    <% } %>

    <a href="logout.jsp">Logout</a>
</body>
</html>
