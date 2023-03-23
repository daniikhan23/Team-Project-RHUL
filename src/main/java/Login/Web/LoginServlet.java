package Login.Web;

import java.io.IOException;
import java.sql.SQLException;

import Login.Bean.LoginBean;
import Login.Database.LoginDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -5947371781560086598L;
	private LoginDatabase LoginDatabase;

    @Override
	public void init() {
    	LoginDatabase = new LoginDatabase();
    }

    @Override
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
                HttpSession session = request.getSession();
            	System.out.println(LoginDatabase.plevel(loginBean));
            	if (LoginDatabase.plevel(loginBean).equals("admin")) {
            		session.setAttribute("plevel", "admin");
            		response.sendRedirect("loginsuccess.jsp");
            	}
            	if (LoginDatabase.plevel(loginBean).equals("waiter")) {
            		session.setAttribute("plevel", "waiter");
            		response.sendRedirect("waiterPage.jsp");
            	}

            	if (LoginDatabase.plevel(loginBean).equals("kitchen")) {
            		session.setAttribute("plevel", "kitchen");
            		response.sendRedirect("Kitchen.jsp");
            	}
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}