package Order.Waiter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		
		String tes = request.getParameter("param");
		System.out.println(tes);
		
		if (request.getParameter("Add item: ") != null) {
			System.out.println("Adding item.");
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
        }
		else if (request.getParameter("remove: ") != null) {
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
		
		else {
			String table = request.getParameter("TableNum");
			int Table = Integer.parseInt(table);
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

		String AllOrders = "SELECT DISTINCT OrderNo,tableno FROM OrderTable WHERE CompletePhase >= 0 ORDER BY OrderNO;";
		
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		

		ResultSet OrderNo = statement.executeQuery(AllOrders);
		String frontEndView = "";
		List<Integer> Orderlist = new ArrayList<Integer>();
		
		while (OrderNo.next()) {
			Orderlist.add(OrderNo.getInt(1));
			Orderlist.add(OrderNo.getInt(2));
		}
		
		System.out.println("full order: " +Orderlist);

		
		for (int i = 0; i< Orderlist.size(); i+=2) {
			String itemsSQL = "SELECT orderItem FROM OrderTable WHERE OrderNo = "+ Orderlist.get(i)+";";
			ResultSet ItemOrder = statement.executeQuery(itemsSQL);
			
			String container = "<button style = \"color:"+getcompleteness(Orderlist.get(i))+"; \"class=\"collapsible\">Order #"+Orderlist.get(i)+ "      Table:"+Orderlist.get(i+1)+"</button>"+
					"<div class=\"content\">\r\n"
					+ "<ul>";
			while (ItemOrder.next()) {
				container += "<li>"+ ItemOrder.getString(1) +"<button>Cancel Item</button><button>Finished Item</button></li>";
				container += "<form action=\"cancelorder\" method=\"post\">";
				container += "<input type=\"submit\" name=\"Cancel Item\" value=\"Cancel Item\" id=\"cancelorder\"/>";
				container += "<input type= \"hidden\" name=\"OrderCancel\" value=\"" + Orderlist.get(i)+ "\">";
				container += "<input type= \"hidden\" name=\"OrderItem\" value=\"" + ItemOrder.getString(1)+ "\">";
				container += "</form>";
				container += "</li>";
			}
			container += "</ul>";
			container += "<form action=\"cancelorder\" method=\"post\">";

			container += "<input type=\"submit\" name=\"Cancel Order\" value=\"Cancel Order\" id=\"cancelorder\"/>"+"\n";
			container += "<input type= \"hidden\" name=\"OrderCancel\" value=\"" + Orderlist.get(i)+ "\">";
			container += "</form>";
			
			container += "<form action=\"acceptorder\" method=\"post\">";

			container += "<input type=\"submit\" name=\"Accept Order\" value=\"Accept Order\" id=\"acceptorder\"/>"+"\n";
			container += "<input type= \"hidden\" name=\"Acceptorder\" value=\"" + Orderlist.get(i)+ "\">";
			
			container += "</form>";
			
			
			container += "<button onclick = \"finishedorder("+Orderlist.get(i)+")\">Finished Order</button></div>";
			frontEndView += container;
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
	
	
	//Still needs implementation
	public void checkCustomer(String[] tables) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		for (int i = 0; i < tables.length; i++) {
			String SQL = "SELECT * FROM TableNO WHERE help = 1 AND TableNO = "+tables[i]+";";
			ResultSet rs = statement.executeQuery(SQL);
			rs.next();
			
		}
	}
	
	
	public String checkCustomer(int tables) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		String SQL = "SELECT * FROM TableNO WHERE help = 1 AND TableNO = "+tables+";";
		ResultSet rs = statement.executeQuery(SQL);
		if (rs.next() == true) {
			return "red";
		}
		else {
			return "#04AA6D";
		}
	}
	
	public void gethelp(int tableno) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "UPDATE TableNO SET help = 1 WHERE TableNo = "+tableno+ ";";
		statement.execute(SQL);
	}
	
	public String getcompleteness(int order) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "SELECT CompletePhase FROM ordertable WHERE orderno = "+order+" ORDER BY CompletePhase DESC;";
		ResultSet rs = statement.executeQuery(SQL);
		rs.next();
		
		if (rs.getInt(1) == 3) {
			String sql = "SELECT CompletePhase FROM ordertable WHERE orderno = "+order+" ORDER BY CompletePhase ASC;";
			ResultSet testcase = statement.executeQuery(sql);
			testcase.next();
			if (testcase.getInt(1) == 3) {
				return "green";
			}
			else {
				return "yellow";
			}
		}
		else if (rs.getInt(1) == 2) {
			return "yellow";
		}
		else if (rs.getInt(1) == 1) {
			return "red";
		}
		else {
			return "black";
		}
	}
}
