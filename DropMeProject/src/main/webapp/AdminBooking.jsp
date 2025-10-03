<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/AdminBooking.css">
    <style>
    	
    </style>
</head>

<body>

    <div class="main">
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <div class="table-container">
                <h1>Booking Details</h1>
                <table>
                    <thead>
                        <tr>
                        	<th>ID</th>
                            <th>Reservation Holder</th>
                            <th>Phone Number</th>
                            <th>Pickup Location</th>
                            <th>Drop Location</th>
                            <th>Distance</th>
                            <th>Ride Cost</th>
                            <th>Date</th>
                            <th>Payment Method</th>
                            <th>Booking Status</th>
                            <th>Vehicle ID</th>
                            <th>Driver ID</th>
                            <th>Customer ID</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="b" items="${book}">
                    	<tr>
                    		<td><c:out value="${b.b_id}"></c:out></td>
                            <td><c:out value="${b.name}"></c:out></td>
                            <td><c:out value="${b.tel}"></c:out></td>
                            <td><c:out value="${b.pickup_loc}"></c:out></td>
                            <td><c:out value="${b.drop_loc}"></c:out></td>
                            <td><c:out value="${b.distance}"></c:out></td>
                            <td><c:out value="${b.cost}"></c:out></td>
                            <td><c:out value="${b.date}"></c:out></td>
                            <td><c:out value="${b.payment}"></c:out></td>
                            <td><c:out value="${b.bk_status}"></c:out></td>
                            <td><c:out value="${b.vid}"></c:out></td>
                            <td><c:out value="${b.bk_did}"></c:out></td>
                            <td><c:out value="${b.cid}"></c:out></td>
	            			<td>
		            			<form action="BookingControl" method="post">
			                    <input type="hidden" name="action" value="cnclBook">
			                    <input type="hidden" name="b_id" value="${b.b_id}">
			                    <button class="action-button">Cancel</button>
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
</html>tml>