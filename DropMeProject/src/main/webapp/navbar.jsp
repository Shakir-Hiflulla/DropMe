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
    <link rel="stylesheet" href="css/navbar.css">
    <script>
        function submitForm() {
            document.getElementById('myForm').submit(); 
        }
    </script>
</head>

<body>

    
        <div class="navbar">
            <div class="icon">
                <h2 class="logo">DropMe</h2>
            </div>

            <div class="menu">
            
                <ul>
                	
                <%if(role!=null){ %>
                <%if(role.equals("driver")){ %>
                    <li><a href="home.jsp">HOME</a></li>
                    <li><a href="AboutUs.jsp">ABOUT US</a></li>
                    <li><a href="BookingControl?action=login&did=<%=userId%>">VIEW BOOKINGS</a></li>
                    <li><a href="BookingControl?action=accepted&did=<%=userId%>">MY BOOKINGS</a></li>
                    <li><a href="ContactControl?action=getuserfaq">CONTACT</a></li>
                    
                    <% }else if(role.equals("customer")){ %>
                  	<li><a href="home.jsp">HOME</a></li>
                    <li><a href="AboutUs.jsp">ABOUT US</a></li>
                    <li><a href="VehicleControl?action=getVeh">VEHICLE & CHARGES</a></li>
                    <li><a href="BookingControl?action=cBooking&did=<%=userId%>">MY BOOKINGS</a></li>
                    <li><a href="ContactControl?action=getuserfaq">CONTACT</a></li>
                    <%}else if(role.equals("admin")){ %>
                    <li><a href="#">HOME</a></li>
                    <li><a href="CRegisterServlet?action=getCust">CUSTOMER DETAILS</a></li>
                    <li><a href="BookingControl?action=getVeh">VEHICLE & CHARGES</a></li>
                    <li><a href="DriverControl?action=getDri">DRIVER DETAILS</a></li>
                    <li><a href="BookingControl?action=getBks">BOOKING DETAILS</a></li>
                    <li><a href="ContactControl?action=getfaq">FAQ</a></li>
                    <%}else{ %>
                    
                    <%} %>
                </ul>
                	<div class="user-options">
	               		<a href="cloginServlet?action=profile&id=<%= id %>&role=<%= role %>" class="logout"><ion-icon name="person-circle"></ion-icon></a>
	               	 <form id="myForm" action="cloginServlet" method="post">
	               		<input type="hidden" name="action" value="logout">
	               		<a href="#" onclick="submitForm()" class="logout"><ion-icon name="log-out"></ion-icon> </a>
	               	</form>
					
           			</div>
                <%} %>
                
            </div>
        </div>
    
    
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>