<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/VehicleCharges.css">
    <script>
        // Function to enable rate input and show confirm button
        function enableRateInput(vehicleId) {
            const rateInput = document.getElementById('rate-' + vehicleId);
            const confirmButton = document.getElementById('confirm-' + vehicleId);
            const setbutton = document.getElementById('set-' + vehicleId);
			
            setbutton.style.display = 'none';
            // Enable rate input
            rateInput.disabled = false;

            // Show the confirm button
            confirmButton.style.display = 'inline';
        }

        // Function to handle confirm click
        function submitRate(vehicleId) {
            const rateInput = document.getElementById('rate-' + vehicleId).value;

            // Check if the rate input is valid
            if (rateInput === "" || isNaN(rateInput) || rateInput <= 0) {
                alert("Please enter a valid rate.");
                return;
            }
            
            document.getElementById('amount-' + vehicleId).value=rateInput;

            // Submit form programmatically
            document.getElementById('rate-form-' + vehicleId).submit();
        }
    </script>
</head>

<body>

    <div class="main">
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <div class="table-container">
                <h1>Vehicle List</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Brand</th>
                            <th>Vehicle Number</th>
                            <th>Rate</th>
                            <th>Type</th>
                            <th>Status</th>
                            <th>Driver ID</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="v" items="${vcl}">
                    	<tr>
                            <td><c:out value="${v.name}"></c:out></td>
                            <td><c:out value="${v.brand}"></c:out></td>
                            <td><c:out value="${v.number}"></c:out></td>
                            <td>
                                <input type="number" id="rate-${v.id}" name="rate" value="${v.rate}" disabled> 
                            </td>
                            <td><c:out value="${v.type}"></c:out></td>
                            <td><c:out value="${v.status}"></c:out></td>
                            <td><c:out value="${v.driverId}"></c:out></td>
                            <td>
                            <c:if test="${v.status == 'Approved'}">
                            	<form id="rate-form-${v.id}" action="VehicleControl" method="post">
                                    <input type="hidden" name="action" value="setRate">
                                    <input type="hidden" name="rate" id="amount-${v.id}" >
                                    <input type="hidden" name="v_id" value="${v.id}">

                                    <button type="button" id="set-${v.id}" class="action-button" onclick="enableRateInput(${v.id})">Set Rate</button>
                                    <button type="button" id="confirm-${v.id}" class="action-button" style="display:none;" onclick="submitRate(${v.id})">Confirm</button>
                                </form>
                            </c:if>
                                <form action="VehicleControl" method="post">
                                    <input type="hidden" name="action" value="aprove">
                                    <input type="hidden" name="v_id" value="${v.id}">
                                     <button class="action-button">Approve</button>
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