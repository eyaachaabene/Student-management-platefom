<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.CourseModel" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher's Courses</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f8fa;
            padding: 20px;
        }

        h1, h2, h3 {
            color: #2E86C1;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        table {
            width: 100%;
            margin-top: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 15px;
            text-align: center;
        }

        th {
            background-color: #2E86C1;
            color: white;
        }

        td {
            font-size: 14px;
            color: #333;
        }

        .button {
            padding: 8px 16px;
            background-color: #2E86C1;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 600;
            transition: background-color 0.3s;
            display: inline-block;
        }

        .button:hover {
            background-color: #1B4F72;
        }

        .button:active {
            background-color: #155a8a;
        }

        .no-course {
            text-align: center;
            color: #999;
            font-style: italic;
        }

        .back-button {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 15px;
            background-color: #34C759;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 600;
        }

        .back-button:hover {
            background-color: #2db54d;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Courses for Subject</h1>

        <!-- Display Subject and Teacher IDs -->
        <p>Subject ID: <%= request.getParameter("subjectId") %></p>
        <p>Teacher ID: <%= request.getParameter("teacherid") %></p>

        <h2>Courses</h2>
        <table>
            <thead>
                <tr>
                    <th>Course Name</th>
                    <th>Course Description</th>
                    <th>Actions</th>
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
                        <a class="button" href="ViewCoursePdf?courseId=<%= course.getCourseId() %>">View PDF</a>
                        <a class="button" href="TeacherSubmitAssignmentServlet?courseId=<%= course.getCourseId() %>&teacherid=<%= request.getParameter("teacherid") %>&subjectId=<%= request.getParameter("subjectId") %>">Add Assignment</a>
                    </td>
                </tr>
                <%   
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3" class="no-course">No courses found for this subject.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <br>
        <a href="TeacherServlet?teacherId=<%= request.getParameter("teacherid") %>" class="back-button">Back to Dashboard</a>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
