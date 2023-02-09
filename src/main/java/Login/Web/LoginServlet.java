package Login.Web;

import java.io.IOException;


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
                response.sendRedirect("Kitchen.jsp");
            } else {
                //HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}