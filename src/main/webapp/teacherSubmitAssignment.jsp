<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.CourseModel" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.*" %>
<%@ page import="DAO.CourseDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Assignment for Course</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f8fa;
            padding: 20px;
        }

        h1 {
            color: #2E86C1;
            margin-bottom: 20px;
        }

        .form-container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        .btn-submit {
            background-color: #34C759;
            color: white;
            font-weight: 600;
            border-radius: 5px;
        }

        .btn-submit:hover {
            background-color: #2db54d;
        }

        .btn-back {
            margin-top: 15px;
            background-color: #2E86C1;
            color: white;
            font-weight: 600;
            border-radius: 5px;
        }

        .btn-back:hover {
            background-color: #1B4F72;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Create Assignment for Course</h1>

        <% 
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            CourseDAO courseDAO = new CourseDAO();
            String name = courseDAO.getCourseNameById(courseId);
        %>

        <h2>Course Name: <%= name %></h2>

        <div class="form-container">
            <form action="TeacherSubmitAssignmentServlet" method="POST">
                <!-- Hidden fields to carry parameters -->
                <input type="hidden" name="courseId" value="<%= request.getParameter("courseId") %>">
                <input type="hidden" name="teacherid" value="<%= request.getParameter("teacherid") %>">
                <input type="hidden" name="subjectId" value="<%= request.getParameter("subjectId") %>">

                <div class="form-group">
                    <label for="title">Assignment Title:</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>

                <div class="form-group">
                    <label for="description">Assignment Description:</label>
                    <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
                </div>

                <div class="form-group">
                    <label for="deadline">Deadline:</label>
                    <input type="date" class="form-control" id="deadline" name="deadline" required>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-submit btn-block">Submit Assignment</button>
                </div>
            </form>

            <a href="TeacherCourseServlet?teacherid=<%= request.getParameter("teacherid") %>&courseId=<%= request.getParameter("courseId") %>&subjectId=<%= request.getParameter("subjectId") %>" class="btn btn-back btn-block">Back to Courses</a>
        </div>

    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
