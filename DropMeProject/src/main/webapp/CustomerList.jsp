<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>DropMe</title>
    <link rel="stylesheet" href="css/CustomerList.css">
    <style>
    	
    </style>
</head>

<body>

    <div class="main">
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <div class="table-container">
                <h1>Customer List</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Phone Number</th>
                            <th>Email</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="usr" items="${userlist}">
                    	<tr>
                            <td><c:out value="${usr.name}"></c:out></td>
                            <td><c:out value="${usr.phone}"></c:out></td>
                            <td><c:out value="${usr.email}"></c:out></td>
	            			<td>
		            			<form action="CRegisterServlet" method="post">
			                    <input type="hidden" name="action" value="dltCust">
			                    <input type="hidden" name="u_id" value="${usr.id}">
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
</html>