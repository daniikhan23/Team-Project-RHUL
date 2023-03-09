package Login.Web;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import Login.Database.LoginDatabase;
import Login.Bean.LoginBean;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -5947371781560086598L;
	private LoginDatabase LoginDatabase;

    public void init() {
    	LoginDatabase = new LoginDatabase();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);
        System.out.println(username);
        System.out.println(password);
        
        try {
            if ( LoginDatabase.validate(loginBean)) {
                //HttpSession session = request.getSession();
                // session.setAttribute("username",username);
            	System.out.println(LoginDatabase.plevel(loginBean));
            	if (LoginDatabase.plevel(loginBean).equals("admin")) {
            		response.sendRedirect("loginsuccess.jsp");
            	}
            	if (LoginDatabase.plevel(loginBean).equals("waiter")) {
            		response.sendRedirect("waiterPage.jsp");
            	}
            	
            	if (LoginDatabase.plevel(loginBean).equals("kitchen")) {
            		response.sendRedirect("Kitchen.jsp");
            	}
            } else {
                request.setAttribute("errorMessage", "The specified username and password was incorrect. Please try again.");
            	request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}