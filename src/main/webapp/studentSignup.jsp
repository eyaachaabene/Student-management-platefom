<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Sign Up</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f5f5f5;
            font-family: Inter, sans-serif;
        }
        
        .container {
            width: 500px;
            height: auto;
            padding: 30px 0;
        }
        
        .signup-box {
            width: 400px;
            margin: 0 auto;
            padding: 30px;
            background: linear-gradient(154deg, white 0%, rgba(228.65, 255, 237.43, 0.60) 100%);
            border-radius: 10px;
            border: 1px solid #EFF0F6;
        }
        
        .signup-title {
            text-align: center;
            color: black;
            font-size: 20px;
            font-weight: 700;
            margin-bottom: 20px;
        }
        
        .form-group {
            margin-bottom: 25px;
        }
        
        .form-label {
            display: block;
            margin-bottom: 8px;
            color: black;
            font-size: 14px;
            font-weight: 600;
        }
        
        .form-input {
            width: 100%;
            height: 40px;
            padding: 0 15px;
            background: white;
            border: 1px solid #01020C;
            border-radius: 6px;
            font-size: 14px;
            box-sizing: border-box;
        }
        
        .form-input:focus {
            outline: none;
            border-color: #34C759;
        }
        
        .signup-btn {
            width: 100%;
            padding: 12px;
            background: #34C759;
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            margin-top: 0px;
            transition: background 0.3s;
        }
        
        .signup-btn:hover {
            background: #2db54d;
        }
        
        .signup-link {
            text-align: center;
            margin-top: 15px;
            font-size: 14px;
        }

        .signup-link a {
            color: #34C759;
            text-decoration: none;
            font-weight: 600;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="signup-box">
            <!-- Logo Spot -->
            <div style="display: flex; justify-content: center; margin-bottom: 20px;">
                <img 
                    src="https://i.ibb.co/RTscByxs/Black-Illustrated-School-Logo-removebg-preview.png" 
                    alt="Logo"
                    style="max-width: 100%; height: 80px; object-fit: contain;"
                />
            </div>

            <div class="signup-title">Student Sign Up</div>
            
            <form action="studentSignupProcess" method="POST">
                <input type="hidden" name="userId" value="${param.userId}" />
                
                <div class="form-group">
                    <label class="form-label" for="username">Full Name:</label>
                    <input type="text" class="form-input" id="username" name="username" required><br><br>
                </div>

                <div class="form-group">
                    <label class="form-label" for="date_of_birth">Date of Birth:</label>
                    <input type="date" class="form-input" id="date_of_birth" name="date_of_birth" required><br><br>
                </div>

                <div class="form-group">
                    <label class="form-label" for="level">Level (e.g., Freshman):</label>
                    <input type="text" class="form-input" id="level" name="level" required><br><br>
                </div>

                <button type="submit" class="signup-btn">Complete Sign Up</button>
            </form>

            <div class="signup-link">
                Already have an account? <a href="login.jsp">Login here</a>
            </div>
        </div>
    </div>
</body>
</html>
