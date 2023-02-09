<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Incoming Orders</title>
  <link rel="stylesheet" href="Kitchen Style.css">
  <meta http-equiv="refresh" content="30">
</head>
<body>
<div class="header">
  <a href="#default" class="logo">Restaurant</a>
  <div class="header-right">
    <a href="login.jsp">LOG OUT</a>
  </div>
</div>

<div id="order1">
  <button class="collapsible">Order #58834</button>
  <div class="content">
    <ul>
      <li>Cheeseburger</li>
      <li>BBQ Burger</li>
      <li>Traditional Pizza</li>
      <li>Cold Shrimp</li>
    </ul>
    <button class="order-out" onclick="document.getElementById('order1').style.display = 'none';">Order Out</button>
  </div>
</div>

<div id="order2">
  <button class="collapsible">Order #58835</button>
  <div class="content">
    <ul>
      <li>Mushroom Swiss Burger</li>
      <li>Lobster Pie Pizza</li>
      <li>Fried Mozzarella</li>
      <li>Crabby Sweets</li>
    </ul>
    <button class="order-out" onclick="document.getElementById('order2').style.display = 'none';">Order Out</button>
  </div>
</div>

<div id="order3">
  <button class="collapsible">Order #58836</button>
  <div class="content">
    <ul>
      <li>Two Number 9s</li>
      <li>Number 9 Large</li>
      <li>Number 6 w/ Extra Dip</li>
      <li>Number 7</li>
      <li>Number 45</li>
      <li>Number 45 w/ Cheese</li>
      <li>Large Soda</li>
    </ul>
    <button class="order-out" onclick="document.getElementById('order3').style.display = 'none';">Order Out</button>
  </div>
</div>

<script>
  var coll = document.getElementsByClassName("collapsible");
  var i;

  for (i = 0; i < coll.length; i++) {
    coll[i].addEventListener("click", function() {
      this.classList.toggle("active");
      var content = this.nextElementSibling;
      if (content.style.maxHeight){
        content.style.maxHeight = null;
      } else {
        content.style.maxHeight = content.scrollHeight + "px";
      }
    });
  }
</script>
</body>
</html>