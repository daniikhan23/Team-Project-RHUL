<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
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
  
  <form action="<%=request.getContextPath()%>/Login" method="post">
   <table style="with: 100%">
    <tr>
     <td><input type="text" name="username" id="username-field" placeholder="Username" class="login-field"/></td>
    </tr>
    <tr>
     <td><input type="password" name="password" id="password-field" placeholder="Password" class="login-field"/></td>
    </tr>

   </table>
   <input type="submit" value="Submit" id="login-submit"/>
   <input type="submit" value="Customer" id="customer-submit">
  </form>
 </main>
</body>
</html>