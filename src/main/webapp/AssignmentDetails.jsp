<%@ page import="model.AssignmentModel" %>
<%@ page import="model.*" %>

<%-- Fetch the assignment details from the request --%>
<%
    AssignmentModel assignment = (AssignmentModel) request.getAttribute("assignment");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assignment Details</title>

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

        form {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        label {
            font-weight: bold;
        }

        input[type="file"] {
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ddd;
        }

        button {
            padding: 10px 20px;
            background-color: #34C759;
            color: white;
            border-radius: 5px;
            font-weight: bold;
            border: none;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #2db54d;
        }

        button:active {
            background-color: #2b9c47;
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
    </style>
</head>
<body>

    <div class="container">
        <!-- Dashboard Header with Assignment Title -->
        <div class="dashboard-header">
            <h1>Assignment Details: </h1>
            <p><strong>title:</strong> <%= assignment.getTitle() %></p>
            <p><strong>Description:</strong> <%= assignment.getDescription() %></p>
            <p><strong>Deadline:</strong> <%= assignment.getDeadline() %></p>
        </div>

        <!-- Form to Submit Assignment -->
        <h2>Submit Your Assignment</h2>
        <form action="SubmitAssignmentServlet" method="POST" enctype="multipart/form-data">
            <!-- Hidden Fields for assignmentId and studentId -->
            <input type="hidden" name="assignmentId" value="<%= assignment.getAssignmentId() %>" />
            <input type="hidden" name="studentId" value="<%= request.getAttribute("studentID") %>" />

            <!-- File Upload Field -->
            <label for="submission">Submit PDF:</label>
            <input type="file" name="submission" accept="application/pdf" required />

            <br><br>

            <!-- Submit Button -->
            <button type="submit">Submit</button>
        </form>

        <!-- Logout Button -->
        <a href="login.jsp" class="logout-btn">Logout</a>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
