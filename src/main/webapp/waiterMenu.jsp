<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="Menu.ViewMenu.MenuData"%>

<!DOCTYPE html>
<html>
<head> 
<title>Welcome to the restaurant webpage</title>
<link rel="stylesheet" href="Restaurant Style menu.css">
<link rel="stylesheet" href="Restaurant Style.css">
</head>
<body>

	<%
	System.out.println("adding staff menu");
	MenuData Menu = new MenuData();
	%>
<div class="header">
  <a href="#default" class="logo">Restaurant</a>
  <div class="header-right">
    <a href="./waiterPage.jsp">Waiter Page</a> 
    <a class="active" href="./waiterMenu.jsp">Order</a> 
    <a href="#contact">Contact</a> 
    <a href="#about">About</a> 
    <a class="logout" href="./login.jsp">Log Out</a>
  </div>
</div>

	<div class="submit">	  
		<button class="button" type="button">Submit Order</button>
	</div>
	
	<div class="update">
	  <form action="menuupdate" method="post">
	  <button class="button" type="button" id="update-button">Update Stock</button> 
	  <select name="item-update" id="item-update">
    <%
    out.println(Menu.fillItemList());
    %>
    </select>
    <input type="text" name="stock-field" id="stock-field" placeholder="Stock">
    </form>
	</div>

	<div class="container">
		<div class="menu">
			<h2 class="menu-group-heading">Starter</h2>
			<div class="menu-group">
				<%
				out.println(Menu.getMenu("Starter"));
				%>

			</div>

		</div>
	</div>

	<div class="container">
		<div class="menu">
			<h2 class="menu-group-heading">Burger</h2>
			<div class="menu-group">
				<%
				out.println(Menu.getMenu("Burger"));
				%>

			</div>

		</div>
	</div>

	<div class="container">
		<div class="menu">
			<h2 class="menu-group-heading">pizza</h2>
			<div class="menu-group">
				<%
				out.println(Menu.getMenu("Pizza"));
				%>
			</div>

		</div>
	</div>

	<div class="container">
		<div class="menu">
			<h2 class="menu-group-heading">Seafood</h2>
			<div class="menu-group">
				<%
				out.println(Menu.getMenu("Seafood"));
				%>
			</div>
		</div>
	</div>
</body>
</html>