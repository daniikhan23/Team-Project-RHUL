<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Order.Kitchen.KitchenOrder" %>

<%
String plevel = (String)session.getAttribute("plevel");
if (plevel != "admin" && plevel != "kitchen") {
	out.print("<h1>403 Forbidden</h1>");
	throw new SkipPageException();
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Incoming Orders</title>
  <link rel="stylesheet" href="Kitchen Style.css">
  <link rel="stylesheet" href="Restaurant Style waiter.css">
  <meta http-equiv="refresh" content="30">
</head>
<body>
<%KitchenOrder KOrder = new KitchenOrder(); %>
<div class="header">
  <a href="#default" class="logo">Diez Libras De Suciedad</a>
  <div class="header-right">
    <a href="login.jsp">LOG OUT</a>
  </div>
</div>


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
</div>
</div>

<div class="box-style-2">
<div class="container">
  <div class="text">
    <h1>Msg from waiter</h1>
    <textarea id="freeform" name="freeform" rows="4" cols="30">
	<%out.println(Order.Kitchen.Messages.getMessage()); %>
</textarea>
  </div>
  
  <div>
    <div class ="text">
    <form action="KMessaging" method="post">
    <h1 >Msg to Waiter</h1>
    
    <textarea id="Message" name="Message" rows="4" cols="30">

</textarea>

<input type="submit" value="Submit" id="submit"/>
</form>
    </div>
  </div>
</div>
</div>

<%out.println(KOrder.getOrder()); %>

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