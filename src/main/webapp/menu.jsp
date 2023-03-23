<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="Menu.ViewMenu.MenuData" %>
<%@ page import="Order.Customer.CustomerOrder" %>
<!DOCTYPE html>
<html>
<head>
  <title>The Restaurant Menu</title>
  <link rel="stylesheet" href="DropDown.css" />
  <link rel="stylesheet" href="Restaurant Style menu.css">
  <link rel="stylesheet" href="SubmitOrder.css" />
  
  
      <style>
      .requestbutton {
        background-color: red;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
      }
      .requestbutton:hover {
        background-color: green;
      }
      .requestbutton.active {
        background-color: yellow;
      }
    </style>
</head>
<body>

<%

String filterValue = request.getParameter("filterValue");
if (filterValue == null) {
    filterValue = "None"; // set a default value here
}
System.out.println("adding menu");
MenuData Menu = new MenuData();
CustomerOrder Order = new CustomerOrder();
%>

<div class="header">
  <a href="#default" class="logo">Diez Libras De Suciedad</a>
  <div class="header-right">
    <a href="./Restaurant Home Page.html">Home</a>
	<a class="active" href="./menu.jsp">Order</a>
    <a href="Restaurant Contact.html">Contact</a>
    <a href="Restaurant About.html">About</a>
  </div>
</div>
	
<div class="dropdown">
    <label for="filter">Filter by Allergy/Preference:</label>
   <select id="filter" onchange="updateFilterValue()">
     <option value="None" <% if (filterValue == null || filterValue.equals("None")) { out.print("selected"); } %>>None</option>
        <option value="Gluten" <% if (filterValue != null && filterValue.equals("Gluten")) { out.print("selected"); } %>>Gluten Free</option>
        <option value="Shellfish" <% if (filterValue != null && filterValue.equals("Shellfish")) { out.print("selected"); } %>>No Shellfish</option>
        <option value="Vegetable" <% if (filterValue != null && filterValue.equals("Vegetable")) { out.print("selected"); } %>>No vegetables</option>
        <option value="Dairy" <% if (filterValue != null && filterValue.equals("Dairy")) { out.print("selected"); } %>>Dairy Free</option>
        <option value="Poultry" <% if (filterValue != null && filterValue.equals("Poultry")) { out.print("selected"); } %>>No Poultry</option>
        <option value="Red Meat" <% if (filterValue != null && filterValue.equals("Red Meat")) { out.print("selected"); } %>>No Red Meat</option>
        
 
    </select>
  </div>
    <%!
      boolean active = false;
    String Starter;
    String Burger;
    String Pizza;
    String Seafood;
    
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

    <button class="requestbutton" style="margin-left: 75px;" onclick="changeColor()">Select Filter</button>
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

<div class="submittingbutton">
  <button onclick="showHelp()">Call Staff</button>
  <div id="help" class="modal">
    <div class="modal-content">
      <span class="close" onclick="hideHelp()">&times;</span>
      <h1>Request for help</h1>
      <form action="CustomerOrderItem" method="post">
        <div class="dropdown">
            <select id="helpDropdown" name = "helpDropdown">
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
        <p>Once you confirm, a staff member will be with you shortly.</p>
        <input class = button type="submit" name="Yes" value="Yes" id="helptable"/>
      </form>
      <button onclick="hideHelp()">No</button>
    </div>
  </div>
</div>
<script>
const help = document.getElementById("help");
function showHelp() {
    help.style.display = "block";
}
function hideHelp() {
    help.style.display = "none";
}

// Get the popup element
var popup = document.querySelector(".modal");

// Show the popup
function showPopup() {
    popup.style.display = "block";
}

// Confirm the action
function confirmAction() {
    // Do something (e.g. delete a record)
    console.log("Action confirmed");

    // Hide the popup
    hidePopup();
}
</script>

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
            Starter=Menu.getMenu("Starter", filterValue); // pass the selected filter value to the getMenu() function
            out.println(Starter);
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
            Burger=Menu.getMenu("Burger",filterValue);
            out.println(Burger);
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
            Pizza=Menu.getMenu("Pizza",filterValue);
            out.println(Pizza);
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
           
            Seafood=Menu.getMenu("Seafood",filterValue);
            out.println(Seafood);
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
  const items = document.querySelectorAll(".item");
    items.forEach((item) => {
    	if (item.innerText.length<=8) {
        item.hidden = true;
      } else {
        item.hidden = false;
      }
    });
  </script>
	<script>
function updateFilterValue() {
  var filterValue = document.getElementById("filter").value;
  var newUrl = window.location.href.split("?")[0] + "?filterValue=" + filterValue;
  window.history.pushState({path:newUrl},'',newUrl);
  document.getElementById("filter").value = filterValue;
  location.reload();
}
</script>

 <footer>
 	<p>Team 35</p>
 </footer>
</body>
</html>
