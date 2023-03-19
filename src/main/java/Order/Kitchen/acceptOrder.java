package Order.Kitchen;

import java.io.IOException;
import java.sql.SQLException;

import Order.AlterOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class acceptOrder  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {

			if (request.getParameter("Acceptorder") != null) {
				String table = request.getParameter("Acceptorder");
				int Table = Integer.parseInt(table);
				AlterOrder order = new AlterOrder();
				try {
					order.acceptorder(Table);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else {
				String table = request.getParameter("Acceptorder");
				String item = request.getParameter("Acceptitem");
				AlterOrder order = new AlterOrder();
				int Table = Integer.parseInt(table);
				try {
					order.cancelitem(Table, item);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		response.sendRedirect("Kitchen.jsp");
	}

}