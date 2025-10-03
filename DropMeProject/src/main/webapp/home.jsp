<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String role = (String) session.getAttribute("role");
Integer id = null;
int userId = 0;

if (role != null) {
    switch (role.toLowerCase()) {
        case "driver":
            id = (Integer) session.getAttribute("DID");
            userId=id;
            break;
        case "customer":
            id = (Integer) session.getAttribute("UID");
            userId=id;
            break;
        case "admin":
            id = (Integer) session.getAttribute("AID");
            userId=id;
            break;
        default:
        	response.sendRedirect("index.jsp");
    }
}

%>
<!DOCTYPE html>
<html>
<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/home.css">
    <style>
    	
    </style>
</head>

<body>

    <div class="main">
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <h1>Your Ride Awaits<br><span>Ride with</span> <br>Comfort</h1>
            <p class="par">"Welcome to DropMe, your trusted partner for seamless travel bookings.<br>
             Whether you're planning a quick ride or a long journey, we make it easy, convenient, and reliable.<br> 
             Book your ride in just a few clicks and enjoy a smooth experience every time."
            </p>
			<%if (role != null) {
			    switch (role.toLowerCase()) {
		        case "driver":
		        	%><button class="cn"><a href="BookingControl?action=login&did=<%=userId%>">View Bookings</a></button><%
		        	break;
		        case "customer":
		        	%><button class="cn"><a href="VehicleControl?action=getVeh">Book a ride</a></button><%
		        	break;
		        default:
		        	%><button class="cn" onclick="changeContent()">Join Us</button>
		            <button class="cn"><a href="clogin.jsp">To Login</a></button><%
		        	
		    }
		} %>

        </div>
    </div>
    
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</body>
</html>