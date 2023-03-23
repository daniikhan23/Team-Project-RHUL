<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="Menu.ViewMenu.MenuData"%>

<%
String plevel = (String)session.getAttribute("plevel");
if (plevel != "admin" && plevel != "waiter") {
	out.print("<h1>403 Forbidden</h1>");
	throw new SkipPageException();
}
%>

<!DOCTYPE html>
<html>
<head> 
<title>Waiter Menu</title>
<link rel="stylesheet" href="Restaurant Style menu.css">
  <link rel="stylesheet" href="Restaurant Style menu updated.css">
  <link rel="stylesheet" href="SubmitOrder.css" />
  <link rel="stylesheet" href="DropDown.css" />
  
</head>
<body>

	<%
	System.out.println("adding staff menu");
	MenuData Menu = new MenuData();
	%>
	<div class="header">
		<a href="#default" class="logo">Diez Libras De Suciedad</a>
		<div class="header-right"> 
			<a class="active" href="./waiterMenu.jsp">Order</a> 
      <a class="logout" href="./login.jsp">Log Out</a>
		</div>
	</div>

	<div class="update">
	<form action="" method="post">
	  <button class="button" type="button" id="update-button">Update Stock</button> 
	  <select name="Item" id="stock-item">
    <%
    out.println(Menu.fillItemList());
    %>
    </select>
    <input type="text" name="stock" id="stock-field" placeholder="Stock">
    </form>
	</div>

	<div class="container">
		<div class="menu">
			<h2 class="menu-group-heading">Starter</h2>
			<div class="menu-group">
				<%
				out.println(Menu.getMenu("Starter"));
				%>

			</div>

		</div>
	</div>

	<div class="container">
		<div class="menu">
			<h2 class="menu-group-heading">Burger</h2>
			<div class="menu-group">
				<%
				out.println(Menu.getMenu("Burger"));
				%>

			</div>

<<<<<<< HEAD
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
  
 <footer>
  <p>Team 35</p>
 </footer>
=======
		</div>
	</div>

	<div class="container">
		<div class="menu">
			<h2 class="menu-group-heading">pizza</h2>
			<div class="menu-group">
				<%
				out.println(Menu.getMenu("Pizza"));
				%>
			</div>

		</div>
	</div>

	<div class="container">
		<div class="menu">
			<h2 class="menu-group-heading">Seafood</h2>
			<div class="menu-group">
				<%
				out.println(Menu.getMenu("Seafood"));
				%>
			</div>
		</div>
	</div>
>>>>>>> e618acf4485edf8ee8a31aacd46264c680b15703
</body>
</html>