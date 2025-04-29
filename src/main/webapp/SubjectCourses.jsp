<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.CourseModel" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subject Courses</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f8fa;
            padding: 20px;
        }

        .dashboard-header {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }

        .dashboard-header h1 {
            color: #2E86C1;
        }

        .dashboard-header p {
            font-size: 18px;
            color: #6c757d;
        }

        h2 {
            color: #2E86C1;
            margin-top: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
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
            font-size: 16px;
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
        }

        .button:hover {
            background-color: #1B4F72;
        }

        .button:active {
            background-color: #155a8a;
        }

        .no-courses {
            text-align: center;
            padding: 15px;
            color: #999;
            font-style: italic;
        }

        .logout-btn {
            margin-top: 30px;
            display: inline-block;
            padding: 10px 15px;
            background-color: #34C759;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 600;
        }

        .logout-btn:hover {
            background-color: #2db54d;
        }

        .error-message {
            color: red;
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- Dashboard Header with Subject Title -->
        <div class="dashboard-header">
            <h1>Courses for Subject :</h1><p><%= request.getAttribute("subjectName") %></p>
        </div>

        <!-- Courses Table -->
        <h2>Your Courses</h2>
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
                        <a class="button" href="ViewCoursePdf?courseId=<%= course.getCourseId() %>">View PDF</a>
                    </td>
                </tr>
                <%   
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3" class="no-courses">No courses found for this subject.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Error Message -->
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
        <div class="error-message">
            <p><%= errorMessage %></p>
        </div>
        <% } %>

        <!-- Back to Dashboard Button -->
        <a href="StudentServlet?studentId=<%= request.getParameter("studentId") %>" class="logout-btn">Back to Dashboard</a>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
