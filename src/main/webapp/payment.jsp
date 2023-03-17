<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="Order.Customer.CustomerOrder" %>
<!DOCTYPE html>
<html>
<head>
  <title>Payment</title>
  <link rel="stylesheet" href="payment.css" />

</head>
<body>
<div class="header">
  <a href="#default" class="logo">diez libras de suciedad</a>
  <div class="header-right">
    <a href="./Restaurant Home Page.html">Home</a>
	<a class="active" href="./menu.jsp">Order</a>
    <a href="#contact">Contact</a>
    <a href="Restaurant About.jsp">About</a>
  </div>
</div>
<%
CustomerOrder Order = new CustomerOrder();
double totalCost = Order.totalcost(1); //need to change for specific table
%>

<div class="container payment-content">
  <h1 class="payment-heading">Thank you for your order!</h1>
  
  
  <div class="order-details-container">
  <p class="current-order-heading">Your Current Order:</p>
  <ul class="current-order-details">
    <% out.println(Order.getCurrentOrder(1)); //need to change for specific table%>
  </ul>
 <p class="total">Total: £<% out.println(totalCost); %>
<a href="menu.jsp">
  <button class="order-more-button">Order More</button>
</a></p>
<div class="image-container">
  <div class="image">
    <img src="https://picsum">
  </div>
</div>  
<div class="payment-container">
  <p class="amount-due">Amount Due: £<span class="total-cost-amount"><%= totalCost %></span></p>
  
  <button class="pay-contactless-button" onclick="payContactless()">Pay Contactless</button>
  
</div>



<script>
  function payContactless() {
    var totalCostAmount = document.querySelector(".total-cost-amount");
    totalCostAmount.textContent = "0.00";
    totalCostAmount.classList.add("paid");
  }
</script>



</body>
</html>


