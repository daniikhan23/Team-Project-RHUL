<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ page import="Order.Customer.CustomerOrder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="login.css">
</head>
<body>



 <main id="main-holder">
  <h1 id="login-header">Login</h1>
  
  <h3 style="color: red">${errorMessage}</h3>

  <form action="Login" method="post">
   <table style="with: 100%">
    <tr>
     <td><input type="text" name="username" id="username-field" placeholder="Username" class="login-field"/></td>
    </tr>
    <tr>
     <td><input type="password" name="password" id="password-field" placeholder="Password" class="login-field"/></td>
    </tr>

   </table>
   <input type="submit" value="Submit" id="login-submit"/>
   
    	
    <div class="button" style="display:inline-block;">
      <a href="menu.jsp">Customer</a>
    </div>
  </form>

 </main>
</body>
</html>