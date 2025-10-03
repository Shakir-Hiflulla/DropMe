<%@page import="java.util.Random"%>
<%@page import="javax.print.attribute.standard.PrinterLocation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<%
	
	String role=(String)session.getAttribute("role");
	System.out.println(role);
	
	
	if(session.getAttribute("UID") != null){
		int id=(int)session.getAttribute("UID");
		
		System.out.println(id);
	}
	else if(session.getAttribute("DID") != null){
		int id=(int)session.getAttribute("DID");
		System.out.println(id);
	}else if(session.getAttribute("AID") != null){
		int id=(int)session.getAttribute("AID");
		System.out.println(id);
	}else
	{
		response.sendRedirect("index.jsp");
		
		
	}
%>
<!DOCTYPE html>
<html>
<head>
    <title>Webpage Design</title>
    <link rel="stylesheet" href="css/Profile.css">
	<meta charset="UTF-8">
	<script>
        function makeEditable() {
            
            var inputs = document.getElementsByTagName('input');
            for (var i = 0; i < inputs.length; i++) {
                inputs[i].removeAttribute("readonly");
            }

            
            var selects = document.getElementsByTagName('select');
            for (var j = 0; j < selects.length; j++) {
                selects[j].removeAttribute("disabled");
            }
           
            var confirmButton = document.getElementById('confirm');
            var setbutton = document.getElementById('edit');
			
            setbutton.style.display = 'none';
            confirmButton.style.display = 'inline';
           
            
        }

        function submitEdit() {
        	document.getElementById('editform').submit();
        }
        
        window.onload = function() {
		const triggerJoin = '${triggerJoin}';
        if (triggerJoin === 'true') {
            changeContent();
   		     }
   		 };
	    
        function changeContent() {
            const content = document.getElementById('content');

            content.classList.add('center-content');
            
            content.innerHTML = `
            <div class="form">
            	<h2>VEHICLE DETAILS</h2>
            <form id="editform" action="VehicleControl" method="post">
            	<p>Name:</p>
            	<input type="text" name="name" value="${veh.name}" required readonly>
            	<p>Vehicle Number:</p>
                <input type="tel" name="vnum" value="${veh.number}"  maxlength="10"pattern="[0-9]{10}"   required readonly>
                <p>Brand:</p>
                <input type="text" name="vbrand" value="${veh.brand}"  required readonly>
                <p>Type:</p>
                
                <input type="text" name="vtype" value="${veh.type}" required readonly>
                <p>Rate:${veh.rate}/Km</p>
                <p>Status:${veh.status}</p>
                <input type="hidden" name="rate" value="${veh.rate}">
                <input type="hidden" name="status" value="${veh.status}">
                <input type="hidden" name="id" value="${veh.id}">
                <input type="hidden" name="action" value="editVehicle">
                <button type="button" id="edit" class="btnn" onclick="makeEditable()">Edit Details</button>
                <button type="button" id="confirm" class="btnn" style="display:none;" onclick="submitEdit()">Confirm</button>
            </form>
            </div>
            `;
        }
  
    </script>
</head>
<body>

    <div class="main">
        <div class="content" id="content">
           
            <div class="form">
                <h2>PROFILE DETAILS</h2>
                
              		
                    <c:choose>
                        <c:when test="${role == 'admin'}">
                        	<form id="editform" action="cloginServlet" method="post">
							<p>Name:</p>
                        	<input type="text" name="name" value="${user.name}" required readonly>
                        	<p>Phone Number:</p>
		                    <input type="tel" name="tel" value="${user.phone}"  maxlength="10"pattern="[0-9]{10}" oninput="this.value = this.value.replace(/[^0-9]/g, '').slice(0,10)"  required readonly>
		                    <p>Email:</p>
		                    <input type="text" name="email" value="${user.email}"  required readonly>
		                    <p>Password:</p>
		                    <input type="text" name="pass" value="${user.password}" required readonly>
		                    <p>Status:</p>
		                    <select class="status" id="editableSelect" disabled>
						        <option value="Available">Available</option>
						        <option value="Not Available">Not Available</option>
					   		</select>
					   		 <input type="hidden" name="id" value="${user.id}">
					   		 <input type="hidden" name="action" value="edit-${role}">
                            <button type="button" id="edit" class="btnn" onclick="makeEditable()">Edit Details</button>
                            <button type="button" id="confirm" class="btnn" style="display:none;" onclick="submitEdit()">Confirm</button>
                        	</form>
                        </c:when>
                        <c:when test="${role == 'customer'}">
                        	<form id="editform" action="cloginServlet" method="post">
	                        <p>Name:</p>
                        	<input type="text" name="name" value="${user.name}" required readonly>
                        	<p>Phone Number:</p>
		                    <input type="tel" name="tel" value="${user.phone}"  maxlength="10"pattern="[0-9]{10}"   required readonly>
		                    <p>Email:</p>
		                    <input type="text" name="email" value="${user.email}"  required readonly>
		                    <p>Password:</p>
		                    <input type="text" name="pass" value="${user.password}" required readonly>
		                    <input type="hidden" name="id" value="${user.id}">
		                    <input type="hidden" name="action" value="edit-${role}">
		                    <button type="button" id="edit" class="btnn" onclick="makeEditable()">Edit Details</button>
                            <button type="button" id="confirm" class="btnn" style="display:none;" onclick="submitEdit()">Confirm</button>
                            </form>
                        </c:when>
                        <c:when test="${role == 'driver'}">
                        	<form id="editform" action="DriverControl" method="post">
                           <p>Name:</p>
                        	<input type="text" name="name" value="${user.name}" required readonly>
                        	<p>Phone Number:</p>
		                    <input type="tel" name="tel" value="${user.email}"  maxlength="10"pattern="[0-9]{10}"   required readonly>
		                    <p>Email:</p>
		                    <input type="text" name="email" value="${user.password}"  required readonly>
		                    <p>Password:</p>
		                    <input type="text" name="pass" value="${user.licenseNum}" required readonly>
		                     <p>License Number:</p>
		                    <input type="text" name="licence" value="${user.phone}"  required readonly>
		                    <input type="hidden" name="id" value="${user.id}">
		                    <input type="hidden" name="action" value="edit-${role}">
		                    <button type="button" id="edit" class="btnn" onclick="makeEditable()">Edit Details</button>
		                    <button type="button" id="confirm" class="btnn" style="display:none;" onclick="submitEdit()">Confirm</button>
		                     <button type="button" id="edit" class="btnn" onclick="changeContent()">Edit Vehicle Details</button>
		                     </form>
                            
                            
                        </c:when>
                        <c:otherwise>
                            <p>Role not recognized.</p>
                        </c:otherwise>
                    </c:choose>

                
            </div>
        </div>
    </div>
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>