<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.CourseModel" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subject Courses</title>
</head>
<body>
    <h1>Courses for Subject</h1>
    
    <h2>Courses</h2>
    <table>
        <thead>
            <tr>
                <th>Course Name</th>
                <th>Course Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<CourseModel> courses = (List<CourseModel>) request.getAttribute("courses");
                if (courses != null && !courses.isEmpty()) {
                    for (CourseModel course : courses) {
            %>
            <tr>
                <td><%= course.getCourseName() %></td>
                <td><%= course.getCoursedescription() %></td>
                <td>
                    <!-- Link to view the course PDF -->
                    <a href="ViewCoursePdf?courseId=<%= course.getCourseId() %>">View PDF</a>
                </td>
            </tr>
            <%   
                    }
                } else {
            %>
            <tr>
                <td colspan="3">No courses found for this subject.</td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <a href="Dashboard.jsp">Back to Dashboard</a>
</body>
</html>
