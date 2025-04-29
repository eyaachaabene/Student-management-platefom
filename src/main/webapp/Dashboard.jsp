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

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f8fa;
            padding: 20px;
        }

        /* Sidebar styles */
        .sidebar {
            background-color: #2E86C1;
            color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            height: 100%;
        }

        .sidebar h2 {
        color:white;
            font-size: 24px;
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
            color: white;
            text-decoration: none;
            font-weight: 600;
            padding: 8px 16px;
            background-color: #34C759; /* Green color */
            border-radius: 5px;
            display: block;
            transition: background-color 0.3s;
        }

        .sidebar ul li a:hover {
            background-color: #2db54d; /* Darker green on hover */
        }

        /* Main content styles */
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

        .no-assignment {
            text-align: center;
            padding: 15px;
            color: #999;
            font-style: italic;
        }

        .no-subject {
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

        .level-progress {
            margin-top: 10px;
            height: 10px;
            width: 100%;
            background-color: #e0e0e0;
            border-radius: 5px;
        }

        .level-progress-bar {
            height: 100%;
            width: 76%;  /* Adjust the width based on the student's progress */
            background-color: #34C759;
            border-radius: 5px;
        }

        .error-message {
            color: red;
        }
    </style>
</head>
<body>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-3">
                <div class="sidebar">
                    <h2>Student Dashboard</h2>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Assignments</a></li>
                        <li><a href="#">Subjects</a></li>
                        <li><a href="#">Settings</a></li>
                    </ul>
                </div>
            </div>

            <!-- Main Content -->
            <div class="col-md-9">
                <div class="dashboard-header">
                    <h1>Student : <%= request.getAttribute("studentName") %></h1>
                    <p>Level: <%= request.getAttribute("studentLevel") %> Year</p>
                    <div class="level-progress">
                        <div class="level-progress-bar"></div> <!-- You can dynamically adjust the width based on the student's progress -->
                    </div>
                </div>

                <!-- Assignments Table -->
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
                            <td><%= assignment.getTitle() %></td>
                            <td><%= assignment.getDeadline() %></td>
                            <td>
                                <a class="button" href="AssignmentDetailsServlet?assignmentId=<%= assignment.getAssignmentId() %>&studentId=<%= request.getAttribute("studentID") %>">View Details</a>
                            </td>
                        </tr>
                        <%   
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="3" class="no-assignment">No assignments found for this student.</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>

                <!-- Subjects Table -->
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
                            Integer studentId = (Integer) request.getAttribute("studentID");

                            if (subjects != null && !subjects.isEmpty() && studentId != null) {
                                for (SubjectModel subject : subjects) {
                        %>
                        <tr>
                            <td><%= subject.getSubjectName() %></td>
                            <td>
                                <a class="button" href="SubjectCoursesServlet?subjectId=<%= subject.getSubjectId() %>&studentId=<%= studentId %>">View Courses</a>
                            </td>
                        </tr>
                        <% 
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="2" class="no-subject">No subjects found for this student.</td>
                        </tr>
                        <% 
                            }
                        %>
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

                <!-- Logout Button -->
                <a href="login.jsp" class="logout-btn">Logout</a>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
