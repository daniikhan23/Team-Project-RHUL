package Order.Waiter;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Order.AlterOrder;

public class cancelOrder extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		
			if (request.getParameter("OrderCancel") != null) {
				String table = request.getParameter("OrderCancel");
				int Table = Integer.parseInt(table);
				AlterOrder order = new AlterOrder();
				try {
					order.cancelorder(Table);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		
		response.sendRedirect("waiterPage.jsp");
	}
	
}