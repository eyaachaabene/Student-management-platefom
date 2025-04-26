<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="studentSignupProcess" method="POST">
    <input type="hidden" name="userId" value="${param.userId}" />
    
    <label for="username">Full Name:</label>
    <input type="text" id="username" name="username" required><br><br>
    
    <label for="date_of_birth">Date of Birth:</label>
    <input type="date" id="date_of_birth" name="date_of_birth" required><br><br>
    
    <label for="level">Level (e.g., Freshman):</label>
    <input type="text" id="level" name="level" required><br><br>
    
    <button type="submit">Complete Sign Up</button>
</form>

</body>
</html>