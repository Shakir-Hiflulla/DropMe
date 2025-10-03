<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/AdminContact.css">
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
            if (rateInput === "" ) {
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
                            <th>Faq ID</th>
                            <th>Question</th>
                            <th>Answer</th>
                            <th>Email</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="f" items="${faq}">
                    	<tr>
                            <td><c:out value="${f.id}"></c:out></td>
                            <td><c:out value="${f.question}"></c:out></td>
                            <td>
                                <input type="text" id="rate-${f.id}" name="rate" value="${f.reply}" disabled>
                            </td>
                            <td><c:out value="${f.email}"></c:out></td>
                            <td><c:out value="${f.pStatus}"></c:out></td>
                           	<td>
                            	<form id="rate-form-${f.id}" action="ContactControl" method="post">
                                    <input type="hidden" name="action" value="answer">
                                    <input type="hidden" name="answr" id="amount-${f.id}" >
                                    <input type="hidden" name="f_id" value="${f.id}">

                                    <button type="button" id="set-${f.id}" class="action-button" onclick="enableRateInput(${f.id})">Answer</button>
                                    <button type="button" id="confirm-${f.id}" class="action-button" style="display:none;" onclick="submitRate(${f.id})">Confirm</button>
                                </form>
							<c:choose>
		                        <c:when test="${f.pStatus == 'Archived'}">
			                        <form  action="ContactControl" method="post">
                                    <input type="hidden" name="action" value="publish">
                                    <input type="hidden" name="sts" value="Published">
                                    <input type="hidden" name="f_id" value="${f.id}">
                                    <button class="action-button">Publish</button>
                                	</form>
		                        </c:when>
		                        <c:when test="${f.pStatus == 'Published'}">
		                        	<form  action="ContactControl" method="post">
                                    <input type="hidden" name="action" value="publish">
                                    <input type="hidden" name="sts" value="Archived">
                                    <input type="hidden" name="f_id" value="${f.id}">
                                    <button  class="action-button">Archive</button>
                                	</form>
                               
		                        </c:when>
		                        <c:otherwise>
		                            <p>no sts</p>
		                        </c:otherwise>
		                    </c:choose>
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
</html>l>