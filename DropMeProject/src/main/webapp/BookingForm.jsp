<%@page import="java.util.Random"%>
<%@page import="javax.print.attribute.standard.PrinterLocation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
	String vId = request.getParameter("vehicle");
	String costString = request.getParameter("cost");
	double cost = 0.0;
	
	
	if (costString != null && !costString.isEmpty()) {
	    try {
	        cost = Double.parseDouble(costString);
	    } catch (NumberFormatException e) {
	        e.printStackTrace(); 
	    }
	}
	
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
    <link rel="stylesheet" href="css/bookingForm.css">
	<meta charset="UTF-8">
	<script>
        
        function calculateDistance() {
        
            var pickup = document.getElementById("pickup").value;
            var drop = document.getElementById("drop").value;
            var distanceField = document.getElementById("distance");
 			var costField = document.getElementById("cost");
 			var total=document.getElementById("total");
 			
            var costPerKm = parseFloat(costField.value) || 0;

            if (pickup === drop) {
                distanceField.value = 0;
                total.value = (0 * costPerKm).toFixed(2);
                
            } else {
                var randomDistance = Math.floor(Math.random() * (100 - 50 + 1)) + 50;
                distanceField.value = randomDistance;
                var totalCost = randomDistance * costPerKm;
                total.value = totalCost.toFixed(2);
            }
        }
        
          function setPlaceholder(input) {
            if (input.value === "") {
              input.type = 'text';
              input.placeholder = "Enter Date and Time";
            }
          }
        </script>

</head>
<body>

    <div class="main">
    <div class="navbar">
            <div class="icon">
                <h2 class="logo">DropMe</h2>
            </div>
         </div>
        <div class="content">
           
            <div class="form">
                <h2>ENTER BOOKING DETAILS</h2>
                	<form action="BookingControl" method="post">
                    <input type="text" name="name" placeholder="Name" required>
                    <input type="tel" name="tel" placeholder="Phone"  maxlength="10" pattern="[0-9]{10}" oninput="this.value = this.value.replace(/[^0-9]/g, '').slice(0,10)" required>
                    <select id="pickup" name="pickup" onchange="calculateDistance()" required>
                        <option value="" disabled selected hidden>Select pickup location</option>
                        <option class="option" value="colombo">Colombo</option>
                        <option class="option" value="kandy">Kandy</option>
                        <option class="option" value="mawanella">Mawanella</option>
                        <option class="option" value="Kurunegala">Kurunegala</option>
                        <option class="option" value="Galle">Galle</option>
                        <option class="option" value="Jaffna">Jaffna</option>
                    </select>
                    <select id="drop" name="drop" onchange="calculateDistance()" required>
                        <option value="" disabled selected hidden>Select drop location</option>
                        <option class="option" value="colombo">Colombo</option>
                        <option class="option" value="kandy">Kandy</option>
                        <option class="option" value="mawanella">Mawanella</option>
                        <option class="option" value="Kurunegala">Kurunegala</option>
                        <option class="option" value="Galle">Galle</option>
                        <option class="option" value="Jaffna">Jaffna</option>
                    </select>
                    <input type="text" id="distance" name="distance" placeholder="Distance(Km)" required readonly>
                    <input type="text" id="total" name="total" placeholder="Cost(LKR.)"  required readonly>
                    <input type="text" id="datetime" name="date" placeholder="Enter Date and Time" onfocus="(this.type='datetime-local')" onblur="setPlaceholder(this)" required>
                    <select name="payment" required>
                        <option value="" disabled selected hidden>Select payment method</option>
                        <option class="option" value="card">Card</option>
                        <option class="option" value="settlement">Cash</option>
                    </select>
                    <input type="hidden" id="cost" name="cost" value="<%= cost %>" required readonly>
                    <input type="hidden"  name="vid" value="<%= vId %>" >
                    <input type="hidden" name="action" value="book">
                    <button class="btnn">Book</button>
                </form>
            </div>
        </div>
    </div>
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>