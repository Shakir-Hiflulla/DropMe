<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> DropMe</title>
    <link rel="stylesheet" href="css/aboutus.css">
    
</head>

<body>
    <jsp:include page="navbar.jsp" />
        <div class="about-container">
            <div class="about-image">
                <img src="css/2.webp" alt="Company Image">
            </div>
            <div class="about-content">
                <h1>About Us</h1>
                <p>
                    At DropMe, we are passionate about making travel easier and more accessible for everyone. 
                    Our platform connects passengers with trusted drivers to provide reliable, safe, and comfortable 
                    rides. Whether you're commuting to work, heading to the airport, or exploring new destinations, 
                    DropMe is here to make sure you arrive at your destination on time and in style. Our commitment 
                    to excellence drives us to continuously improve our services to meet the needs of our growing community.
                </p>
                <p>
                    Founded on the belief that transportation should be efficient, affordable, and hassle-free, 
                    we are proud to serve our customers with a solution that combines cutting-edge technology 
                    and customer-focused service. Join us on the road to the future of transportation!
                </p>
            </div>
        </div>

        
        <div class="contact-details">
            <h2>Contact Us</h2>
            <ul>
                <li><strong>Address:</strong> 1234 Main St, Cityville, Country</li>
                <li><strong>Phone:</strong> +123 456 7890</li>
                <li><strong>Email:</strong> support@dropme.com</li>
                <li><strong>Operating Hours:</strong> Mon - Fri, 9:00 AM - 6:00 PM</li>
            </ul>
        </div>
    </div>

   
    <div class="icons">
        <a href="#"><ion-icon name="logo-facebook"></ion-icon></a>
        <a href="#"><ion-icon name="logo-instagram"></ion-icon></a>
        <a href="#"><ion-icon name="logo-twitter"></ion-icon></a>
        <a href="#"><ion-icon name="logo-google"></ion-icon></a>
    </div>

    <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
</body>

</html>
