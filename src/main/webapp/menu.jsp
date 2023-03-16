<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="Menu.ViewMenu.MenuData" %>
<%@ page import="Order.Customer.CustomerOrder" %>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome to the Restaurant Webpage</title>
  <link rel="stylesheet" href="DropDown.css" />
  <link rel="stylesheet" href="Restaurant Style menu.css">
  <link rel="stylesheet" href="SubmitOrder.css" />
  
  
      <style>
      .requestbutton {
        background-color: green;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
      }
      .requestbutton:hover {
        background-color: darkgreen;
      }
      .requestbutton.active {
        background-color: red;
      }
    </style>
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
	
<div class="dropdown">
    <label for="filter">Filter by:</label>
    <select id="filter">
      <option value="all">All</option>
      <option value="Starter">Starter</option>
      <option value="Burger">Burger</option>
      <option value="Pizza">Pizza</option>
      <option value="Seafood">Seafood</option>
    </select>
    
  </div>
    <%!
      boolean active = false;
    %>
    <script>
      function changeColor() {
        var btn = document.querySelector(".requestbutton");
        btn.classList.add("active");

        
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "CustomerOrderItem", true);
        xhr.send();
      }
      
      
      const form = document.querySelector('form');
      form.addEventListener('submit', (e)) => {
    	  e.preventDefault();
      }
    </script>

    <button class="requestbutton" onclick="changeColor()">Click me</button>
    <% if (active) { 
     %>
      <script>
        document.querySelector(".requestbutton").classList.add("active");
      </script>
    <% } %>
    
<div class = "submittingbutton">
    <button type="" onclick="showModal()">Order Now</button>
    <div id="modal" class="modal">
      <div class="modal-content">
        <span type="input" class="close" onclick="hideModal()">&times;</span>
        <h1>Your Cart</h1>
        <p>Your order details:</p>
        <ul id="order-details">
         
          <% out.println(Order.getCurrentOrder(1)); //need to change for specific table%>
        </ul>
        <p class="total">Total: �<% out.println(Order.totalcost(1)); //need to change for specific table%></p>

        <h2>Would you like to place the order?</h2>
        <form action= "CustomerOrderItem" method="post">
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
        <p>This action cannot be undone.</p>
          
   		  <input class = button type="submit" name="Yes" value="Yes" id="OrderTable"/>
  		  </form>
        <button onclick="hidePopup()">No</button>
      </div> 
    </div>
    <script src="SubmitOrder.js"></script>



</div>

	<div class="item Starter">
    <div class="container">
      <div class="menu">
        <h2 class="menu-group-heading">
            Starter
        </h2>
         <div class="row">
		  <div class="column">
		    <img src="starters.png" alt="Starters" style="height:80%; width:100%">
		  </div>
		  <div class="column">
		    <img src="starters2.jpg" alt="Starters" style="height:80%; width:100%">
		  </div>
		  <div class="column">
		    <img src="starters3.jpg" alt="Starters" style="height:80%; width:100%">
		  </div>
		</div> 
        <div class="menu-group">
        
            <%
            out.println(Menu.getMenu("Starter"));
            %>

        </div>
        </div>
      </div>
	</div>


   <div class="item Burger">
    <div class="container">
        <div class="menu">
          <h2 class="menu-group-heading">
              Burger
          </h2>
          <div class="row">
		  <div class="column">
		    <img src="burgers.jpg" alt="Burgers" style="height:80%; width:100%">
		  </div>
		  <div class="column">
		    <img src="burgers2.jpg" alt="Burgers" style="height:80%; width:100%">
		  </div>
		  <div class="column">
		    <img src="burgers3.jpg" alt="Burgers" style="height:80%; width:100%">
		  </div>
		</div> 
          <div class="menu-group">
            <%
            out.println(Menu.getMenu("Burger"));
            %>

          </div>
  
          </div>
        </div>
	</div>

	<div class="item Pizza">
      <div class="container">
        <div class="menu">
          <h2 class="menu-group-heading">
              Pizza
          </h2>
          <div class="row">
		  <div class="column">
		    <img src="pizzas.jpg" alt="Pizzas" style="height:80%; width:100%">
		  </div>
		  <div class="column">
		    <img src="pizzas2.jpg" alt="Pizzas" style="height:80%; width:100%">
		  </div>
		  <div class="column">
		    <img src="pizzas3.jpeg" alt="Pizzas" style="height:80%; width:100%">
		  </div>
		</div>
          <div class="menu-group">
            <%
            out.println(Menu.getMenu("Pizza"));
            %>
          </div>
  
          </div>
        </div>
     </div>

	<div class="item Seafood">
      <div class="container">
        <div class="menu">
          <h2 class="menu-group-heading">
              Seafood
          </h2>
          <div class="row">
		  <div class="column">
		    <img src="seafood.jpg" alt="Seafood" style="height:80%; width:100%">
		  </div>
		  <div class="column">
		    <img src="seafood2.jpg" alt="Seafood" style="height:80%; width:100%">
		  </div>
		  <div class="column">
		    <img src="seafood3.jpg" alt="Seafood" style="height:80%; width:100%">
		  </div>
		</div>
          <div class="menu-group">
            <%
            out.println(Menu.getMenu("Seafood"));
            %>
          </div>
          </div>
        </div>
	</div>
	
	<script src="DropDown.js"></script>
	
	<script>
function hidePopup() {
    popup.style.display = "none";
}
	</script>
	
  <script>
  const filterSelect = document.getElementById("filter");
  const items = document.querySelectorAll(".item");

  filterSelect.addEventListener("change", (event) => {
    const selectedValue = event.target.value;

    items.forEach((item) => {
      if (selectedValue === "all") {
        item.hidden = false;
      } else if (item.classList.contains(selectedValue)) {
        item.hidden = false;
      } else {
        item.hidden = true;
      }
    });
  });
  </script>
  
  
</body>
</html>