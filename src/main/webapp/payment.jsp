<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="Order.Customer.CustomerOrder" %>
<!DOCTYPE html>
<html>
<head>
  <title>Payment</title>
  <link rel="stylesheet" href="payment.css" />
  <link rel="stylesheet" href="Payment .css" />
  <link rel="stylesheet" href="Restaurant Style menu updated.css">
  <link rel="stylesheet" href="DropDown.css" />
</head>
<body>
<div class="header">
  <a href="#default" class="logo">Diez Libras De Suciedad</a>
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
</div>
  
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
    <form action = "CustomerOrderItem" method = "post">
      <h1>Payment Details</h1>
      <label for="name">Name on Card:</label>
      <input type="text" id="name" name="name"><br><br>
      <label for="card">Card Number:</label>
      <input type="text" id="card" name="card"><br><br>
      <label for="expiry">Expiration Date:</label>
      <input type="text" id="expiry" name="expiry"><br><br>
      <label for="cvv">CVV:</label>
      <input type="text" id="cvv" name="cvv"><br><br>
      <div class="dropdown">
      <select id="myDropdown" name = "myDropdown">
        <option value="0">Table Number?</option>
        <option value="1">Table 1</option>
        <option value="2">Table 2</option>
        <option value="3">Table 3</option>
        <option value="4">Table 4</option>
        <option value="5">Table 5</option>
        <option value="6">Table 6</option>
        <option value="7">Table 7</option>
        <option value="8">Table 8</option>
        <option value="9">Table 9</option>
        <option value="10">Table 10</option>
      </select>
    </div>
	  <p class="amount-due">Amount Due: £<span class="total-cost-amount"><%= totalCost %></span></p>
  		
  	  <input class="pay-contactless-button" onclick="payContactless()" type="submit" value="Contactless Payment" id="login-submit"/>
  
    </form>

  </div>
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


