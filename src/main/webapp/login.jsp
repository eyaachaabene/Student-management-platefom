<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
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
        
        .login-box {
            width: 400px;
            margin: 0 auto;
            padding: 30px;
            background: linear-gradient(154deg, white 0%, rgba(228.65, 255, 237.43, 0.60) 100%);
            border-radius: 10px;
            border: 1px solid #EFF0F6;
        }
        
        .login-title {
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
        
        .login-btn {
            width: 100%;
            padding: 12px;
            background: #34C759;
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            transition: background 0.3s;
        }
        
        .login-btn:hover {
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
            text-decoration: underline;}
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-box">
      
            <div style="display: flex; justify-content: center; margin-bottom: 20px;">
                <img 
                    src="https://i.ibb.co/RTscByxs/Black-Illustrated-School-Logo-removebg-preview.png" 
                    alt="Logo"
                    style="max-width: 100%; height: 80px; object-fit: contain;"
                />
            </div>
 <!-- Check if there is an error query parameter and display it -->
            <div class="error-message" 
                 style="display: ${param.error != null ? 'block' : 'none'};">
                ${param.error}
            </div>
            <div class="login-title">Login</div>
            
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label class="form-label" for="email">Email:</label>
                    <input type="text" class="form-input" id="email" name="email" required><br><br>
                </div>
                
                <div class="form-group">
                    <label class="form-label" for="password">Password:</label>
                    <input type="password" class="form-input" id="password" name="password" required><br><br>
                </div>
                
                <button type="submit" class="login-btn">Login</button>
            </form>

            <div class="signup-link">
                Don't have an account? <a href="signup.jsp">Sign Up here</a>
            </div>
        </div>
    </div>
</body>
</html>
