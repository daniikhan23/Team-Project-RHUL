package Order.Waiter;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Order.AlterOrder;

public class finishedOrder extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		
			// still need to implement
			if (request.getParameter("OrderItem") != null) {
				String table = request.getParameter("OrderCancel");
				String item = request.getParameter("OrderItem");
				int Table = Integer.parseInt(table);
				AlterOrder order = new AlterOrder();
				try {
					order.cancelitem(Table, item);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				String table = request.getParameter("OrderCancel");
				AlterOrder order = new AlterOrder();
				int Table = Integer.parseInt(table);
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