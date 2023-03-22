<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page import="Menu.ViewMenu.MenuData"%>
<%@ page import="Order.Waiter.waiterOrder" %>
<%@ page import="Order.Waiter.Messages" %>
<!DOCTYPE html>
<html>
<head>
<title>Waiter Page</title>
<link rel="stylesheet" href="Restaurant Style waiter.css">
<link rel="stylesheet" href="Kitchen Style.css">
</head>

  <%
  System.out.println("adding staff menu");
  MenuData Menu = new MenuData();
  waiterOrder order = new waiterOrder();
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

<script>
  function helping(table) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/cafe/menuChange", true);
    xhr.send("table=" + table);
  }
  
  function cancelorder(order){
	  	const formdata = new FormData();
	  	formdata.append("Order", order);
	  	
	  	fetch("cafe/menuChange", {
	  		method: "POST",
	  		body: formdata,
	  		
	  	})
  }
  
  function acceptorder(order){
	    var xhr = new XMLHttpRequest();
	    xhr.open("GET", "http://localhost:8080/cafe/menuChange", true);
	    xhr.send("id=" + order);
  }
  function finishedorder(order){
	  
	    var xhr = new XMLHttpRequest();
	    xhr.open("GET", "http://localhost:8080/cafe/menuChange", true);
	    xhr.send("id=" + order);
  }
</script>


  <div id="countdown"></div>
  <button id="pauseButton" onclick="toggleRefresh()">Pause Refresh</button>
  
  <script>
    var seconds = 10;
    var refreshInterval = setInterval(countdown, 1000);

    function countdown() {
      seconds--;
      document.getElementById("countdown").innerHTML = "Refreshing page: " + seconds;
      if (seconds == 0) {
        location.reload();
      }
    }

    function toggleRefresh() {
      var button = document.getElementById("pauseButton");
      if (button.innerHTML == "Pause Refresh") {
        clearInterval(refreshInterval);
        button.innerHTML = "Resume Refresh";
      } else {
        refreshInterval = setInterval(countdown, 1000);
        button.innerHTML = "Pause Refresh";
      }
    }
  </script>

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
    <h1>Customer help</h1>
    <div class="table-group" style="width:100%">
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=1>
        <button style="width:25%; background-color: <% out.println(order.checkCustomer(1)); %>;">Table 1</button>
      </form>
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=2>
        <button style="width:25%; background-color: <% out.println(order.checkCustomer(2)); %>">Table 2</button>
      </form>  
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=3>
        <button style="width:25%; background-color: <% out.println(order.checkCustomer(3)); %>">Table 3</button>
      </form>
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=4>
        <button style="width:25%; background-color: <% out.println(order.checkCustomer(4)); %>">Table 4</button>
      </form>

    </div>
    <div class="table-group" style="width:100%">
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=5>
        <button style="width:25%; background-color: <% out.println(order.checkCustomer(5)); %>">Table 5</button>
      </form>
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=6>
        <button style="width:25%; background-color: <% out.println(order.checkCustomer(6)); %>">Table 6</button>
      </form>
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=7>
        <button style="width:25%; background-color: <% out.println(order.checkCustomer(7)); %>">Table 7</button>
      </form>
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=8>
        <button style="width:25%; background-color: <% out.println(order.checkCustomer(8)); %>">Table 8</button>
      </form>

    </div>
    <div class="table-group" style="width:100%">
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=9>
        <button style="width:50%; background-color: <% out.println(order.checkCustomer(9)); %>">Table 9</button>
      </form>
      <form action=menuChange method="post">
        <input type="hidden" class="table" name="table" value=10>
        <button style="width:50%; background-color: <% out.println(order.checkCustomer(10)); %>">Table 10</button>
      </form>

    </div>

  </div>
</div>
</div>

<div class="box-style">
<div class="container">
  <div class="text">
    <h1>Special order notes</h1>
    <textarea id="freeform" name="freeform" rows="4" cols="50">
Enter text here...
</textarea>
<br>
<button>Submit button</button>

  </div>
  
  <div class="text">
    <h1 >Order status</h1>
    
    <%out.println(order.frontEndView()); %>

  </div>
</div>
</div>

<div class="box-style-2">
<div class="container">
  <div class="text">
    <h1>Msg from kitchen</h1>
    <textarea id="freeform" name="freeform" rows="4" cols="30">
    <%out.println(Order.Waiter.Messages.getMessage()); %>
</textarea>
  </div>
  
  <div>
    <div class ="text">
    <form action="Messaging" method="post">
    <h1 >Msg to Kitchen</h1>
    
    <textarea id="Message" name="Message" rows="4" cols="30">

</textarea>

<input type="submit" value="Submit" id="submit"/>
</form>
    </div>
  </div>
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