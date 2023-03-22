<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="Menu.ViewMenu.MenuData"%>

<!DOCTYPE html>
<html>
<head> 
<title>Waiter Menu</title>
<link rel="stylesheet" href="Restaurant Style menu.css">
</head>
<body>

	<%
	System.out.println("adding staff menu");
	MenuData Menu = new MenuData();
	%>
	<div class="header">
		<a href="#default" class="logo">Diez Libras De Suciedad</a>
		<div class="header-right">
			<a href="./Restaurant Home Page.html">Home</a> <a class="active"
				href="./menu.jsp">Order</a> <a href="#contact">Contact</a> <a
				href="#about">About</a>
		</div>
	</div>

	<div class="submit">	  
		<button class="button" type="button">Submit Order</button>
	</div>
	
	 <div class="order">
    <button class="button" type="button" id="update-button">Add to order</button> 
    <select name="Item" id="order-item">
    <%
    out.println(Menu.fillItemList());
    %>
    </select>
    <input type="text" name="stock" id="order-field" placeholder="Quantity">
  </div>
	
	<div class="update">
	  <button class="button" type="button" id="update-button">Update Stock</button> 
	  <select name="Item" id="stock-item">
    <%
    out.println(Menu.fillItemList());
    %>
    </select>
    <input type="text" name="stock" id="stock-field" placeholder="Stock">
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