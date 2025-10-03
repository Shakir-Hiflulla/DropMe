<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/DriverBookings.css">
    <style>
    	
    </style>
</head>

<body>

    <div class="main">
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <div class="table-container">
                <h1>Bookings</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Reservation Holder</th>
                            <th>Phone Number</th>
                            <th>Pickup Location</th>
                            <th>Drop Location</th>
                            <th>Distance</th>
                            <th>Ride Cost</th>
                            <th>Date</th>
                            <th>Payment Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="bookings" items="${bookings}">
                    	<tr>
                            <td><c:out value="${bookings.name}"></c:out></td>
                            <td><c:out value="${bookings.tel}"></c:out></td>
                            <td><c:out value="${bookings.pickup_loc}"></c:out></td>
                            <td><c:out value="${bookings.drop_loc}"></c:out></td>
                            <td><c:out value="${bookings.distance}"></c:out> Km</td>
                            <td><c:out value="${bookings.cost}"></c:out> LKR.</td>
                            <td><c:out value="${bookings.date}"></c:out></td>
                            <td>
			                <c:choose>
			                    <c:when test="${bookings.payment == 'cash'}">
			                        Pending
			                    </c:when>
			                    <c:when test="${bookings.payment == 'card'}">
			                        Paid
			                    </c:when>
			                    <c:otherwise>
			                        Unknown Payment Status
			                    </c:otherwise>
			                </c:choose>
				            </td>
	            			<td>
		            			<form action="BookingControl" method="post">
			                    <input type="hidden" name="action" value="accept">
			                    <input type="hidden" name="b_id" value="${bookings.b_id}">
			                    <button class="action-button">Accept</button>
			                	</form>
		                	</td>
	                        </tr>
	                    	
	                    	</c:forEach>
                       
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</body>
</html>