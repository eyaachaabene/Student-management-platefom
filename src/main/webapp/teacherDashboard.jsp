<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.TeacherModel, model.SubjectModel, java.util.List" %>
<html>
<head>
    <title>Teacher Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h1 {
            color: #2E86C1;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        table, th, td {
            border: 1px solid #aaa;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        a.button {
            padding: 6px 12px;
            background-color: #2E86C1;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-right: 5px;
        }
        a.button:hover {
            background-color: #1B4F72;
        }
    </style>
</head>
<body>

<%
    TeacherModel teacher = (TeacherModel) request.getAttribute("teacher");
    List<SubjectModel> subjects = (List<SubjectModel>) request.getAttribute("subjects");
%>

<h1>Welcome, <%= teacher.getName() %>!</h1>
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
                    <a class="button" href="Teacherviewcoursesservlet?teacherid=<%= teacher.getTeacherId() %>&subjectId=<%= subject.getSubjectId() %>">View Courses</a>
                    <a class="button" href="TeacherMarkAttendanceServlet?teacherid=<%= teacher.getTeacherId() %>&subjectId=<%= subject.getSubjectId() %>">Mark Attendance</a>
                </td>
            </tr>
    <%    }
       } else { %>
           <tr><td colspan="3">No subjects assigned.</td></tr>
    <% } %>
    </tbody>
</table>

<!--
<br><br>
<a class="button" href="TeacherServlet?action=addSubject">Add New Subject</a>
-->

</body>
</html>
