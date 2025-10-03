<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html lang="en">

<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/index.css">
    
    <script>
	    function getParameterByName(name) {
	        const urlParams = new URLSearchParams(window.location.search);
	        return urlParams.get(name);
	    }
	
	    
	    window.onload = function() {
	        const triggerJoin = getParameterByName('triggerJoin');
	        if (triggerJoin === 'true') {
	            changeContent();
	        }
	    };
	    
        function changeContent() {
            const content = document.getElementById('content');

            content.classList.add('center-content');
            
            content.innerHTML = `
                <h1>Select your profile type</h1>
                <div class="button-container">
                    <button class="cn"><a href="DriverRegister.jsp">Register as a Driver</a></button>
                    <button class="cn"><a href="CRegister.jsp">Register as a Passenger</a></button>
                </div>
            `;
        }
    </script>
</head>
<body>
    <div class="main">
        <jsp:include page="navbar.jsp"/>
        <div class="content" id="content">
            <h1>Your Ride Awaits<br><span>Ride with</span> <br>Comfort</h1>
            <p class="par">"Welcome to DropMe, your trusted partner for seamless travel bookings.<br>
             Whether you're planning a quick ride or a long journey, we make it easy, convenient, and reliable.<br> 
             Book your ride in just a few clicks and enjoy a smooth experience every time."
            </p>

            <button class="cn" onclick="changeContent()">Join Us</button>
            <button class="cn"><a href="clogin.jsp">To Login</a></button>

          
        </div>
    </div>
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>