<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.TeacherModel, model.SubjectModel, java.util.List" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Dashboard</title>
    <style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f7f8fa;
        padding: 20px;
        display: flex;
        flex-wrap: nowrap; /* Prevents content from wrapping */
        margin: 0;
        height: 100vh; /* Ensures full height */
    }

    .sidebar {
        width: 250px;
        height: 100vh;
        background-color: #d1d8e0; /* Soft gray-blue color */
        color: #333;
        padding: 30px 20px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        margin-right: 30px;
        flex-shrink: 0; /* Prevent sidebar from shrinking */
    }

    .sidebar h2 {
        font-size: 24px;
        font-weight: bold;
        color: #2E86C1;
        margin-bottom: 30px;
    }

    .sidebar ul {
        list-style: none;
        padding: 0;
    }

    .sidebar ul li {
        margin: 15px 0;
    }

    .sidebar ul li a {
        color: #333;
        text-decoration: none;
        font-weight: 600;
        padding: 8px 16px;
        background-color: #34C759; /* A muted green */
        border-radius: 5px;
        display: block;
        transition: background-color 0.3s;
    }

    .sidebar ul li a:hover {
        background-color: #2db54d; /* Slightly darker green on hover */
    }

    /* Profile Header */
    .profile-header {
        display: flex;
        align-items: center;
        margin-bottom: 30px;
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
        flex-grow: 1;
        width: 100%; /* Make sure it takes the full width available */
    }

    .profile-header img {
        border-radius: 50%;
        width: 80px;
        height: 80px;
        margin-right: 20px;
    }

    .profile-header div {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .profile-header h1 {
        margin: 0;
        font-size: 24px;
        color: #2E86C1;
    }

    .profile-header p {
        font-size: 16px;
        color: #6c757d;
    }

    .level-progress {
        margin-top: 10px;
        height: 8px;
        width: 100%;
        background-color: #e0e0e0;
        border-radius: 5px;
    }

    .level-progress-bar {
        height: 100%;
        width: 76%;
        background-color: #34C759;
        border-radius: 5px;
    }

    h3 {
        color: #2E86C1;
        margin-bottom: 20px;
        font-size: 22px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 15px;
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
        display: inline-block;
    }

    .button:hover {
        background-color: #1B4F72;
    }

    .button:active {
        background-color: #155a8a;
    }

    .no-subject {
        text-align: center;
        padding: 15px;
        color: #999;
        font-style: italic;
    }
</style>

</head>
<body>

<%
    TeacherModel teacher = (TeacherModel) request.getAttribute("teacher");
    List<SubjectModel> subjects = (List<SubjectModel>) request.getAttribute("subjects");
%>

<!-- Sidebar -->
<div class="sidebar">
    <h2>CLASSY</h2>
    <ul>
        <li><a href="#">Dashboard</a></li>
        <li><a href="#">Assignments</a></li>
        <li><a href="#">Messages</a></li>
        <li><a href="#">Settings</a></li>
    </ul>
</div>

<!-- Main Content -->
<div class="main-content">
    <div class="profile-header">
        <img src="https://i.ibb.co/RTscByxs/Black-Illustrated-School-Logo-removebg-preview.png" alt="Profile Picture" />
        <div>
            <h1><%= teacher.getName() %></h1>
            <p><%= teacher.getDepartment() %></p>
            <div class="level-progress">
                <div class="level-progress-bar"></div>
            </div>
        </div>
    </div>

    <h3>Your Subjects:</h3>

    <table>
        <thead>
            <tr>
                <th>Subject ID</th>
                <th>Subject Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <% if (subjects != null && !subjects.isEmpty()) {
               for (SubjectModel subject : subjects) { %>
            <tr>
                <td><%= subject.getSubjectId() %></td>
                <td><%= subject.getSubjectName() %></td>
                <td>
                    <!-- Action buttons for each subject -->
                    <a class="button" href="TeacherCourseServlet?teacherid=<%= teacher.getTeacherId() %>&subjectId=<%= subject.getSubjectId() %>">View Courses</a>
                    <a class="button" href="TeacherMarkAttendanceServlet?teacherid=<%= teacher.getTeacherId() %>&subjectId=<%= subject.getSubjectId() %>">Mark Attendance</a>
                </td>
            </tr>
        <%    }
           } else { %>
            <tr><td colspan="3">No subjects assigned.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
