<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Webpage Design</title>
    <link rel="stylesheet" href="css/register.css">
	<meta charset="UTF-8">
	<script>
        function validatePassword() {
            
            var password = document.getElementById("pass").value;
            var rePassword = document.getElementById("re-pass").value;
            
            
            if (password !== rePassword) {
               
                alert("Passwords do not match!");
                return false; 
            }
            return true; 
        }
    </script>
</head>
<body>

    <div class="main">
        <div class="navbar">
            <div class="icon">
                <h2 class="logo">DropMe</h2>
            </div>
        </div>
        <div class="content">
           
            <div class="form">
                <h2>Sign up Here</h2>
                	<form action="CRegisterServlet" method="post"  onsubmit="return validatePassword()">
                		<input type="text" name="name" placeholder="Enter Name Here" required >
                		<input type="tel" name="tel" placeholder="Enter Phone No Here" required>
						<input type="email" name="email" placeholder="Enter Email Here" required>
				        <input type="password" id="pass" name="pass" placeholder="Enter Password Here" required>
				        <input type="password"id="re-pass"  name="re-pass" placeholder="Re-Enter Password" required>
				        <input type="hidden" name="action" value="register" required>
				        <button class="btnn" onclick="validatePassword()">Sign up</button>
					</form>
            </div>
        </div>
    </div>
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>