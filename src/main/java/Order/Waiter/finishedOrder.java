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
			
			if (request.getParameter("finished item") != null) {
				String Order = request.getParameter("finishedorder");
				String item = request.getParameter("finished item");
				int OrderNO = Integer.parseInt(Order);
				AlterOrder order = new AlterOrder();
				try {
					order.CompleteItem(OrderNO, item);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				String Order = request.getParameter("finishedorder");
				AlterOrder order = new AlterOrder();
				int OrderNO = Integer.parseInt(Order);
				try {
					order.CompleteOrder(OrderNO);;
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		
		response.sendRedirect("waiterPage.jsp");
	}
	
}