<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Order.Kitchen.KitchenOrder" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Incoming Orders</title>
  <link rel="stylesheet" href="Kitchen Style.css">
  <meta http-equiv="refresh" content="30">
</head>
<body>
<%KitchenOrder Order = new KitchenOrder(); %>
<div class="header">
  <a href="#default" class="logo">Restaurant</a>
  <div class="header-right">
    <a href="login.jsp">LOG OUT</a>
  </div>
</div>

<%out.println(Order.getOrder()); %>

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