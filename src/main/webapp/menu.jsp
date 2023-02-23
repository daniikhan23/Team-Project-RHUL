<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="Menu.ViewMenu.MenuData" %>
<%@ page import="Order.Customer.CustomerOrder" %>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome to the restaurant webpage</title>
  <link rel="stylesheet" href="Restaurant Style menu.css">
  <link rel="stylesheet" href="DropDown.css" />
  <link rel="stylesheet" href="SubmitOrder.css" />
</head>
<body>

<%
System.out.println("adding menu");
MenuData Menu = new MenuData();
CustomerOrder Order = new CustomerOrder();
%>
<div class="header">
  <a href="#default" class="logo">Restaurant</a>
  <div class="header-right">
    <a href="./Restaurant Home Page.html">Home</a>
	<a class="active" href="./menu.jsp">Order</a>
    <a href="#contact">Contact</a>
    <a href="Restaurant About.jsp">About</a>
  </div>
</div>

<div class = "tableno">
   <div class="dropdown">
      <select id="myDropdown">
        <option value="0">Select an option</option>
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
</div>

<div class = "submittingbutton">
    <button type="" onclick="showModal()">Order Now</button>
    <div id="modal" class="modal">
      <div class="modal-content">
        <span type="input" class="close" onclick="hideModal()">&times;</span>
        <h1>Your Cart</h1>
        <p>Your order details:</p>
        <ul id="order-details">
          <!-- Order details will be inserted here -->
          <% out.println(Order.getCurrentOrder(1)); %>
        </ul>
        <p class="total">Total: £<% out.println(Order.totalcost(1)); %></p>

        <h2>Would you like to place the order?</h2>
        <p>This action cannot be undone.</p>
        <button onclick="confirmAction()">Yes</button>
        <button onclick="hidePopup()">No</button>
      </div> 
    </div>
    <script src="SubmitOrder.js"></script>

</div>
    <div class="container">
      <div class="menu">
        <h2 class="menu-group-heading">
            Starter
        </h2>
        <div class="menu-group">
            <%
            out.println(Menu.getMenu("Starter"));
            %>

        </div>

        </div>
      </div>



    
    <div class="container">
        <div class="menu">
          <h2 class="menu-group-heading">
              Burger
          </h2>
          <div class="menu-group">
            <%
            out.println(Menu.getMenu("Burger"));
            %>

          </div>
  
          </div>
        </div>


      <div class="container">
        <div class="menu">
          <h2 class="menu-group-heading">
              pizza
          </h2>
          <div class="menu-group">
            <%
            out.println(Menu.getMenu("Pizza"));
            %>
          </div>
  
          </div>
        </div>


      <div class="container">
        <div class="menu">
          <h2 class="menu-group-heading">
              Seafood
          </h2>
          <div class="menu-group">
            <%
            out.println(Menu.getMenu("Seafood"));
            %>
          </div>
          </div>
        </div>

	<script src="DropDown.js"></script>
	
	<script>
function hidePopup() {
    popup.style.display = "none";
}
	</script>
</body>
</html>