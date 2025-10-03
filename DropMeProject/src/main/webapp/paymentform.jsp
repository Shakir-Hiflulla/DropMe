<%@page import="java.util.Random"%>
<%@page import="javax.print.attribute.standard.PrinterLocation"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("UID") != null){
	int id=(int)session.getAttribute("UID");
	System.out.println(id);
}
else{
	response.sendRedirect("index.jsp");
	
} 
%>
<!DOCTYPE html>
<html>
<head>
    <title>Webpage Design</title>
    <link rel="stylesheet" href="css/paymentform.css">
	<meta charset="UTF-8">
	<style>
        input[type=number]::-webkit-inner-spin-button, 
        input[type=number]::-webkit-outer-spin-button { 
            -webkit-appearance: none; 
            margin: 0; 
        }

        input[type=number] {
            -moz-appearance: textfield;
        }
    </style>
	<script>
    </script>
</head>
<body>

    <div class="main">
        <div class="content">
           
            <div class="form">
                <h2>ENTER PAYMENT DETAILS</h2>
                	<form action="PaymentControl" method="post">
                		
                		<input type="text" name="cname" placeholder="Card holder Name" required >
                		<input type="number" name="Cnumber" placeholder="Card Number" required>
                		<input type="text" id="Edate" name="Edate" placeholder="Enter Date and Time" onfocus="(this.type='date')" onblur="setPlaceholder(this)" required>
                		<input type="number" name="cvv" placeholder="CVV/CVC" required>
                		<input type="text" name="amount" placeholder="${booked.cost}" required readonly >
                		<textarea name="description" rows="5" cols="10" placeholder="Payment description(optional)" ></textarea>
                		
                		<input type="hidden" name="name" value="${booked.name}">
                		<input type="hidden" name="tel" value="${booked.tel}" >
                		<input type="hidden" name="pickup_loc" value="${booked.pickup_loc}" >
                		<input type="hidden" name="drop_loc" value="${booked.drop_loc}" >
                		<input type="hidden" name="distance" value="${booked.distance}" >
                		<input type="hidden" name="cost" value="${booked.cost}" >
                		<input type="hidden" name="date" value="${booked.date}" >
                		<input type="hidden" name="payment" value="${booked.payment}" >
                		<input type="hidden" name="vid" value="${booked.vid}">
                		<input type="hidden" name="cid" value="${booked.cid}">
                		<input type="hidden" name="did" value="${booked.bk_did}">
                		
                		
		                <input type="hidden" name="action" value="pay">
		                <button class="btnn">Confirm</button>
					</form>
            </div>
        </div>
    </div>
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>