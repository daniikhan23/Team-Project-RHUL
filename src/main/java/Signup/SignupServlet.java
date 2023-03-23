package Signup;
import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1 ;
    private SignupDB employeeDB;

    public void init() {
        employeeDB = new SignupDB();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	response.getWriter().append("Served at: ").append(request.getContextPath());
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/webapp/signup.jsp");
    	dispatcher.forward(request, response);
    	
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        SignupBean employee = new SignupBean();
        employee.setUsername(username);
        employee.setEmail(email);
        employee.setPassword(password);
        try {
            employeeDB.registerEmployee(employee);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //System.out.println(e);
        }
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/webapp/login.jsp");
//    	dispatcher.forward(request, response);
        response.sendRedirect("login.jsp");
    }
}