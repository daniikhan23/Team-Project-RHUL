package Order.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DB.connection.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomerOrder extends HttpServlet{
	

	private static final long serialVersionUID = 6655222004307163766L;
	private int tableNO;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		
		String name = request.getParameter("MenuItem");
		System.out.println(name);
		
		if (request.getParameter("-") != null) {
			System.out.println("test");
			try {
				removefromtable(name, 1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } else {
        	try {
				inputIntoCtable(name, 1);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		response.sendRedirect("menu.jsp");
	}
	
	public void CurrentOrder() throws ClassNotFoundException, SQLException {
		String CurrentOrderTable = """
				CREATE TABLE CurrentOrderTable(
					OrderID INTEGER NOT NULL,
					orderItem VARCHAR(256) NOT NULL,
					TableNo INTEGER NOT NULL,
					CompletePhase INTEGER NOT NULL,
					timeStarted TIMESTAMP NOT NULL,
					PRIMARY KEY (OrderID)
				);
				""";
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE IF EXISTS CurrentOrderTable;");
		statement.execute(CurrentOrderTable);
	}
	
	
	public static void addToOrderTable() throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("SELECT * FROM CurrentOrderTable;");
		String sql = "INSERT INTO OrderTable VALUES(";

		ResultSet OrderNo = statement.executeQuery("SELECT OrderNO FROM OrderTable ORDER BY OrderNO;");
		int NewOrderNo = OrderNo.getInt(0) + 1;
		int NewOrderID = addpnum("OrderTable");

		while (rs.next()) {
			statement.execute(sql+NewOrderID+",'"+rs.getString(1)+"', "+rs.getString(2)+", 0, "+rs.getString(4)+", "+NewOrderNo);
			NewOrderID ++;
		}
	}
	
	public static int addpnum(String table) throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		ResultSet LastPrimarykey = statement.executeQuery("SELECT COUNT(*) FROM " + table +";");
		LastPrimarykey.next();
		if (LastPrimarykey.getInt(1) == 0) {
			return 1;
		}
		int addnew = LastPrimarykey.getInt(1) +1;
		return addnew;
	}
	
	public static void inputIntoCtable(String item, int tableNO) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		int primary_key = addpnum("CurrentOrderTable");
		System.out.println(primary_key);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		String sql = "INSERT INTO CurrentOrderTable VALUES ("+primary_key+", '"+item+ "', "+ tableNO+ ", 0, '"+ dtf.format(now) + "');";
		statement.execute(sql);
	}
	
	public static int numberOfitem(String item, int tableNO) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "SELECT COUNT(*) FROM CurrentOrderTable WHERE orderItem = '"+item+"' AND TableNo = '"+tableNO+"' AND CompletePhase = 0;";
		ResultSet numberOfItems = statement.executeQuery(SQL);
		numberOfItems.next();
		int number = numberOfItems.getInt(1);
		
		return number;
	}
	
	public static void removefromtable(String item, int tableNO) throws ClassNotFoundException, SQLException {
		if (numberOfitem(item, tableNO) != 0) {
			Connection connection = Database.connectToDatabase();
			Statement statement = connection.createStatement();
			String getSQLNAME = "SELECT OrderID FROM CurrentOrderTable WHERE orderItem = '"+item+"' AND TableNo="+tableNO+" AND CompletePhase = 0 ORDER BY orderid ASC;";
			ResultSet rs = statement.executeQuery(getSQLNAME);
			rs.next();
			System.out.println(item);
			System.out.println("Testing" +rs.getInt(1));
			String SQL = "UPDATE CurrentOrderTable SET CompletePhase = 1 WHERE OrderID = " +rs.getInt(1)+";";
			System.out.println(SQL);
			statement.execute(SQL);
			statement.close();
		}
	}
		
	public String getCurrentOrder(int TableNO) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		String SQL = "SELECT orderItem FROM CurrentOrderTable WHERE TableNo = '"+TableNO+"' AND CompletePhase = 0;";
		ResultSet rs = statement.executeQuery(SQL);
		String returnSTring = "";
		while (rs.next()) {
			returnSTring += "<li>"+rs.getString(1)+"  -  £"+ getitemcost(rs.getString(1))+"</li>";
		}
		return returnSTring;
		
		}
	
	public int getitemcost(String item) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		String getCost = "SELECT Cost FROM MenuTable WHERE Name = '"+item+"';";
		ResultSet combined = statement.executeQuery(getCost);
		combined.next();
		return combined.getInt(1);	
	}
	public int totalcost(int TableNO) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		String SQL = "SELECT orderItem FROM CurrentOrderTable WHERE TableNo = '"+TableNO+"' AND CompletePhase = 0;";
		ResultSet rs = statement.executeQuery(SQL);
		int totalcost = 0;
		while (rs.next()) {
			totalcost += getitemcost(rs.getString(1));
		}
		
		return totalcost;
		
	}
		
	}
	
