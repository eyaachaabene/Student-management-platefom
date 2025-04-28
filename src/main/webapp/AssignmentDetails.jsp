<%@ page import="model.AssignmentModel" %>
<%@ page import="model.*" %>

<%-- Fetch the assignment details from the request --%>
<%
    AssignmentModel assignment = (AssignmentModel) request.getAttribute("assignment");

%>

<h1>Assignment Details</h1>

<p><strong>Subject:</strong> <%= assignment.getTitle() %></p>
<p><strong>Description:</strong> <%= assignment.getDescription() %></p>
<p><strong>Deadline:</strong> <%= assignment.getDeadline() %></p>

<h3>Submit Your Assignment</h3>
<form action="SubmitAssignmentServlet" method="POST" enctype="multipart/form-data">
    <!-- Hidden Fields for assignmentId and studentId -->
    <input type="hidden" name="assignmentId" value="<%= assignment.getAssignmentId() %>" />
   
    <input type="hidden" name="studentId" value="<%= request.getAttribute("studentID") %>" />
      
    <!-- File Upload Field -->
    <label for="submission">Submit PDF:</label>
    <input type="file" name="submission" accept="application/pdf" required /><br><br>

    <!-- Submit Button -->
    <button type="submit">Submit</button>
</form>


