<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="signupProcess" method="POST">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

    <label for="role">Are you signing up as a Teacher or Student?</label><br>
    <input type="radio" id="student" name="role" value="student" checked> Student<br>
    <input type="radio" id="teacher" name="role" value="teacher"> Teacher<br><br>

    <button type="submit">Sign Up</button>
</form>

</body>
</html>