
<%@page import="javax.print.attribute.standard.PrinterLocation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
    <title>Webpage Design</title>
    <link rel="stylesheet" href="css/contact.css">
	<meta charset="UTF-8">

</head>
<body>

    <div class="main">
    <jsp:include page="navbar.jsp" />
        <div class="content">
           
            <div class="form">
                <h2>Ask Question</h2>
                	<form action="ContactControl" method="post">
                    <input type="text" name="email" placeholder="Email" required>
                    <textarea name="question" rows="5" cols="10" placeholder="Ask Question" ></textarea>
                    <input type="hidden" name="action" value="ask">
                    
                    <button class="btnn">Post</button>
                </form>
            </div>
            <div class="faq">
                <h2>FAQs</h2>
                <c:forEach var="f" items="${faq}">
	                <p class="qstn"><c:out value="${f.question}"></c:out></p>
	                <p class="rply"><c:out value="${f.reply}"></c:out></p>	
                </c:forEach>
            </div>
        </div>
    </div>
    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>
</html>