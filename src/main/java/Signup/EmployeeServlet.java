package Signup;
import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1 ;
    private EmployeeDB employeeDao;

    public void init() {
        employeeDao = new EmployeeDB();
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

        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setEmail(email);
        employee.setPassword(password);
        try {
            employeeDao.registerEmployee(employee);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //System.out.println(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/webapp/login.jsp");
    	dispatcher.forward(request, response);
    }
}