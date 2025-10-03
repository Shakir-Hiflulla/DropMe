<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/VehicleRegister.css">
    <script >
    	window.onload = function() {
    	const triggerJoin = '${triggerJoin}';
        if (triggerJoin === 'true') {
        	 alert("Passwords or email is incorrect!");
   		 }
    	};
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
                <h2>Login Here</h2>
                	<form action="cloginServlet" method="post" >
                		<input type="hidden" name="action" value="login">
						<input type="email" name="email" placeholder="Enter Email Here" required>
				        <input type="password" name="pass" placeholder="Enter Password Here" required>
				        <p class="option-par">Select User type:<br></p>
				        <select id="select" name="role" id="role" >
							<option class="option" value="customer" style="background-color: #000;
							    color: #fff;
							    font-size: 15px;
							    padding: 10px;
							    font-family: sans-serif;">
							    Passenger
    						</option>
                    		<option class="option" value="admin" style="background-color: #000;
							    color: #fff;
							    font-size: 15px;
							    padding: 10px;
							    font-family: sans-serif;">Admin
							</option>
		                    <option class="option" value="driver" style="background-color: #000;
							    color: #fff;
							    font-size: 15px;
							    padding: 10px;
							    font-family: sans-serif;">Driver
							</option>
		                </select>
				        <button class="btnn" >Login</button>
					</form>

                <p class="link">Don't have an account
                    <a href="index.jsp?triggerJoin=true">Sign up </a> here<br>
                </p>

            </div>
        </div>
    </div>
   
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>