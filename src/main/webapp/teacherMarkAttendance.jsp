<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.StudentModel, model.SubjectModel, java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mark Attendance</title>
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
        .button {
            padding: 6px 12px;
            background-color: #2E86C1;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .button:hover {
            background-color: #1B4F72;
        }
    </style>
</head>
<body>

<h1>Mark Attendance for Subject</h1>

<form action="TeacherMarkAttendanceServlet" method="post">
    <table>
        <thead>
            <tr>
                <th>Student Name</th>
                <th>Present</th>
            </tr>
        </thead>
        <tbody>
        <% 
            List<StudentModel> students = (List<StudentModel>) request.getAttribute("students");
            if (students != null && !students.isEmpty()) {
                for (StudentModel student : students) {
        %>
            <tr>
                <td><%= student.getName() %></td>
                <td>
                    <input type="checkbox" name="attendance_<%= student.getId() %>" value="1">
                </td>
            </tr>
        <% 
                }
            } else {
        %>
            <tr>
                <td colspan="2">No students found.</td>
            </tr>
        <% } %>
        </tbody>
    </table>

    <input type="hidden" name="teacherId" value="<%= request.getAttribute("teacherId") %>">
    <input type="hidden" name="subjectId" value="<%= request.getAttribute("subjectId") %>">
    <input type="submit" class="button" value="Submit Attendance">
</form>

</body>
</html>
