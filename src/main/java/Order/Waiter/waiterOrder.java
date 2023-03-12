package Order.Waiter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class waiterOrder extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		System.out.println("editing Menu");

		if (request.getParameter("Add item: ") != null) {
			System.out.println("test");
			try {
				String name = request.getParameter("add-item");
				String cost = request.getParameter("add-cost");
				double cost1 = Double.parseDouble(cost);
				String Category = request.getParameter("add-category");
				System.out.println("correcting");
				System.out.println(name);
				System.out.println(cost);
				System.out.println(Category);
				add(name, (int) cost1, Category);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } else {
        	try {
        		String items = request.getParameter("Items");
				remove(items);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		
		response.sendRedirect("waiterPage.jsp");
	}
	public ResultSet getName(int OrderNo) throws SQLException, ClassNotFoundException {
		String SQL = "SELECT OrderItem FROM OrderTable WHERE TableNo ='"+ OrderNo +"' AND Complete = 0;";
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		return rs;
	}
	
	public ResultSet getORderNo() throws ClassNotFoundException, SQLException {
		String SQL = "SELECT OrderNo FROM OrderTable WHERE Complete = 0;";
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		return rs;
	}
	
	public String frontEndView() throws SQLException, ClassNotFoundException {
		int orderSet = 1;
		String NumberOfCurrentOrders = "SELECT COUNT( DISTINCT OrderNo) as orders FROM OrderTable;";
		String AllOrders = "SELECT DISTINCT OrderNo as orders FROM OrderTable WHERE CompletePhase = 0;";
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		int numberofOrders = statement.executeQuery(NumberOfCurrentOrders).getInt(0);
		ResultSet OrderNo = statement.executeQuery(AllOrders);
		String frontEndView = "";
		while (orderSet <= numberofOrders) {
			String itemsSQL = "SELECT orderItem FROM OrderTable WHERE OrderNo = "+ OrderNo.getInt(0)+";";
			ResultSet ItemOrder = statement.executeQuery(itemsSQL);
			String container = "<button class=\"collapsible\">Order #"+OrderNo.getInt(0)+"</button>"+
					"<div class=\"content\">\r\n"
					+ "<ul>";
			while (ItemOrder.next()) {
				container += "<li>"+ ItemOrder.getString(0) +"<li>";
			}
			container += "</ul></div>";
					
			frontEndView += container;
			OrderNo.next();		
			orderSet++;
			
		}
		
		return frontEndView;
	}
	
	public void remove(String item) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String sql = "DELETE FROM MenuTable WHERE Name = '"+item+"';";
		
		statement.execute(sql);
	}
	
	public void add(String item, int cost, String Category) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String sql = "INSERT INTO MenuTable VALUES("+getprimarykey()+", '"+item+"', "+cost+", '"+ Category+"');";
		System.out.println(sql);
		statement.execute(sql);
	}
	
	public int getprimarykey() throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String sql = "SELECT ItemCode FROM menutable ORDER BY ItemCode ASC;";
		int num = 1;
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			if (rs.getInt(1) == num) {
				num ++;
			}
		}
		return num;
	}
	
	
}
