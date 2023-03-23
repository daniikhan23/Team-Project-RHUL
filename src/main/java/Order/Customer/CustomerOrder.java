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
	public int tableNo;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {

		String name = request.getParameter("MenuItem");

		if (request.getParameter("-") != null) {
			System.out.println("removed item"+name);
			try {
				removefromtable(name, 1);
				response.sendRedirect("menu.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		else if (request.getParameter("+") != null){
        	try {
				inputIntoCtable(name, 1);
				System.out.println("added item"+name);
				response.sendRedirect("menu.jsp");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		else if (request.getParameter("helpDropdown") != null) {
			  String table = request.getParameter("helpDropdown");
			  
			  if (table != null) {
			    tableNo = Integer.parseInt(table);
			  }

			  System.out.println("table " + table);
			  
			  try {
	        sethelp(tableNo);
	      } catch (ClassNotFoundException | SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
			  
			  response.sendRedirect("menu.jsp");
			}

		else if (request.getParameter("myDropdown") != null) {
			
			String table = request.getParameter("myDropdown");
			tableNo = Integer.parseInt(table);
			try {
				addToOrderTable(tableNo);
				response.sendRedirect("CompletionBar.html");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {

				System.out.println("Added currentordertable to ordertable");

				response.sendRedirect("payment.jsp");
		}


	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//String table = request.getParameter("myDropdown");
		//this.tableNO = Integer.parseInt(table);
		//System.out.println(this.tableNO);
		try {
			sethelp(1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void CurrentOrder() throws ClassNotFoundException, SQLException {
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
		System.out.println("Reseted currentordertable");
	}


	public static void addToOrderTable(int tableNO) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();

		String sql = "INSERT INTO OrderTable VALUES";
		ResultSet OrderNo = statement.executeQuery("SELECT OrderNO FROM OrderTable ORDER BY OrderNO DESC;");
		int NewOrderNo;
		if (!OrderNo.next()) {
			NewOrderNo = 1;		}

		else {
			NewOrderNo = OrderNo.getInt(1) + 1;
		}
		int NewOrderID = addpnum("OrderTable");
		System.out.println(NewOrderID);
		System.out.println("Adding items to order Table: ");
		ResultSet rs = statement.executeQuery("SELECT * FROM CurrentOrderTable WHERE completephase = 0;");
		String FinishedS = sql;
		while (rs.next()) {
			System.out.println(rs.getString(2));
			FinishedS += "("+NewOrderID+",'"+rs.getString(2)+"', "+tableNO+", 0, '"+rs.getTimestamp("timeStarted")+"', "+NewOrderNo+"),";
			NewOrderID ++;
		}

		FinishedS = FinishedS.replaceFirst(".$","");
		FinishedS += ";";
		System.out.println(FinishedS);
		statement.execute(FinishedS);
		CurrentOrder();
	}


	public static int addpnum(String table) throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		ResultSet LastPrimarykey = statement.executeQuery("SELECT COUNT(*) FROM " + table +";");
		if (!LastPrimarykey.next()) {
			return 1;
		}
		else {
			int addnew = LastPrimarykey.getInt(1) +1;
			return addnew;
		}

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

	public void sethelp(int tableNO) throws ClassNotFoundException, SQLException {
		System.out.println("calls update help");
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		String help = "UPDATE TableNO SET help = 1 WHERE TableNo = "+tableNO+ ";";
		statement.executeUpdate(help);
	}

	}

