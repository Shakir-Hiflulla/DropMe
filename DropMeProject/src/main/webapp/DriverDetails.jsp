<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/DriverDetails.css">
    <style>
    	
    </style>
</head>

<body>

    <div class="main">
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <div class="table-container">
                <h1>Driver Details</h1>
                <table>
                    <thead>
                        <tr>
                        	<th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>License Number</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="d" items="${dri}">
                    	<tr>
                    		<td><c:out value="${d.id}"></c:out></td>
                            <td><c:out value="${d.name}"></c:out></td>
                            <td><c:out value="${d.email}"></c:out></td>
                            <td><c:out value="${d.phone}"></c:out></td>
                            <td><c:out value="${d.licenseNum}"></c:out></td>
	            			<td>
		            			<form action="DriverControl" method="post">
			                    <input type="hidden" name="action" value="dltDri">
			                    <input type="hidden" name="d_id" value="${d.id}">
			                    <button class="action-button">Delete</button>
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