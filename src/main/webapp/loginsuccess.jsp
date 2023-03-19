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
    <a href="./Restaurant Home Page.html">Home</a>
    <a href="menu.jsp">Order</a>
    <a href="Restaurant Contact.html">Contact</a>
    <a class="active" href="Restaurant About.html">About</a>
  </div>
</div>

<div class="container">
  <div class="text">
    <h1>Welcome to the admin webpage</h1>
    <p>Here you get full access to everything on the website.</p>
  </div>
</div>

<div class="container">
  <div class="text">
    <h1>Menu Page</h1>
    <div class = "links">
    <a href="menu.jsp">Order</a>
	</div>
  </div>
</div>

<div class="container">
  <div class="text">
    <h1>Kitchen Page</h1>
    <div class = "links">
    <a href="kitchen.jsp">Kitchen</a>
	</div>
  </div>
</div>

<div class="container">
  <div class="text">
    <h1>Waiter Menu Page</h1>
    <div class = "links">
    <a href="waiterMenu.jsp">Waiter Menu</a>
	</div>
  </div>
</div>

<footer>
  <p>Team 35</p>
</footer>
</body>
</html>