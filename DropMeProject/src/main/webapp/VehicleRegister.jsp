<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	

%>
<!DOCTYPE html>
<html>
<head>
    <title>Webpage Design</title>
    <link rel="stylesheet" href="css/VehicleRegister.css">
	<meta charset="UTF-8">
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
                <h2>Enter Vehicle details</h2>
                	<form action="DriverControl" method="post">
                		<input type="text" name="brand" placeholder="Vehicle Brand" required >
                		<input type="text" name="name" placeholder="Vehicle Name" required >
                		<input type="text" name="number" placeholder="Vehicle Number" required >
                		<select id="drop" name="type"  required>
	                        <option value="" disabled selected hidden>Select Vehicle Type</option>
	                        <option class="option" value="Sedan">Sedan</option>
	                        <option class="option" value="SUV">SUV</option>
	                        <option class="option" value="Minivan">Minivan</option>
	                        <option class="option" value="Luxury Car">Luxury Car</option>
	                        <option class="option" value="Convertible">Convertible</option>
	                        <input type="hidden" name="dname" value="<%= request.getParameter("name") %>" >
	                        <input type="hidden" name="dtel" value="<%= request.getParameter("tel") %>" >
	                        <input type="hidden" name="dmail" value="<%= request.getParameter("email") %>" >
	                        <input type="hidden" name="dlicense" value="<%= request.getParameter("license") %>" >
	                        <input type="hidden" name="dpass" value="<%= request.getParameter("pass") %>" >
	                        <input type="hidden" name="action" value="DReg" >
                    	</select>
				        <button class="btnn">Request Registration</button>
					</form>

            </div>
        </div>
    </div>
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>