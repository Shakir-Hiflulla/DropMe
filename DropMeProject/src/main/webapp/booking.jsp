<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Webpage Design</title>
    <link rel="stylesheet" href="css/Booking.css">
</head>

<body>

    <div class="main">
        <jsp:include page="navbar.jsp" />
        <div class="content">

			<div class="all_list">
			<c:forEach var="veh" items="${veh}">
			<c:if test="${veh.rate!='0.0'}">
   				<div class="list">
                    <img src="css/1.png" alt="Image">
                    <span>Vehicle:${veh.name}</span>
                    <span>Number: ${veh.number}</span>
                    <span>Brand: ${veh.brand}</span>
                    <span>Type: ${veh.type}</span>
                    <span>Rate: ${veh.rate} </span>
                    <form action="BookingForm.jsp" method="post">
                    	<input type="hidden" name="action" value="book" >
                    	<input type="hidden" name="cost" value="${veh.rate}" >
                    	<input type="hidden" name="vehicle" value="${veh.id}" >
                    	<button class="cn">Book</button>
                    </form>
                          
                </div>
			</c:if>
			</c:forEach>
						
            </div>


        </div>
    </div>

    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>

</html>