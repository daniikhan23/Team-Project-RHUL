<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page import="Menu.ViewMenu.MenuData"%>

<!DOCTYPE html>
<html>
<head>
<title>Waiter Page</title>
<link rel="stylesheet" href="Restaurant Style waiter.css">
</head>

  <%
  System.out.println("adding staff menu");
  MenuData Menu = new MenuData();
  %>
  
<div class="header">
	<a href="#default" class="logo">Restaurant</a>
	<div class="header-right">
		<a class="active" href="./waiterPage.jsp">Waiter Page</a> 
		<a href="./waiterMenu.jsp">Order</a> 
		<a href="#contact">Contact</a> 
		<a href="#about">About</a> 
		<a class="logout" href="./login.jsp">Log Out</a>
	</div>
</div>

<div class="middle-line"></div>
<div class="box-style">

<div class="container">
  <div class="text">
  <form action="menuChange" method="post">
    <h1>Add/Remove item</h1>
      <div>
    <input type="submit" class="button" value="Add item: " name = "Add item: " id="login-submit"/>
    <input type="text" name="add-item" id="item-field" placeholder="Item">
    <input type="text" name="add-cost" id="item-field" placeholder="Cost">
    <input type="text" name="add-category" id="item-field" placeholder="Category">
      </div>
      <div class="remove">
		    <input type="submit" class="button" value="remove: " name = "remove: " id="login-submit"/>
		    <select name="Items" id="remove-item">
		    <%
		    out.println(Menu.fillItemList());
		    %>
		    </select>
		  </div>
    <br></br>
    </form>
  </div>
  
  
  
  <div class="text">
    <h1 >Customer help</h1>
  </div>
</div>
</div>

<div class="box-style">
<div class="container">
  <div class="text">
    <h1>Special order notes</h1>
  </div>
  
  <div class="text">
    <h1 >Order status</h1>
  </div>
</div>
</div>

<div class="box-style-2">
<div class="container">
  <div class="text">
    <h1>Msg from kitchen</h1>
  </div>
  
  <div>
    <div class ="text">
    <h1 >Msg to Kitchen</h1>
    </div>
  </div>
</div>
</div>