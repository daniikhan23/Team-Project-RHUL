<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@page import="Login.Database.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Webpage</title>
  <link rel="stylesheet" href="loginsuccess.css">
</head>
<body>
<div class="header">
  <a href="#default" class="logo">Restaurant</a>
  <div class="header-right">
    <a href="login.jsp">Log out</a>
  </div>
</div>

<div class="container">
  <div class="text">
    <h1>Welcome to the Admin Page</h1>
  </div>
</div>

<div class="button-group">
	<a href="Restaurant Home Page.html" class="button">Home</a>
	<a href="Restaurant Contact.html" class="button">Contact</a>
	<a href="Restaurant About.html" class="button">About Us</a>
    <a href="menu.jsp" class="button">Restaurant Menu Page</a>
    <a href="waiterMenu.jsp" class="button">Waiter Menu</a>
    <a href="waiterPage.jsp" class="button">Waiter Page</a>
    <a href="Kitchen.jsp" class="button">Kitchen Section</a>
 </div>

<footer>
  <p>Team 35</p>
</footer>

</body>
</html>