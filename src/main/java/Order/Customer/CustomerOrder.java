package Order.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DB.connection.Database;

public class CustomerOrder {
	
	public void CurrentOrder() throws ClassNotFoundException, SQLException {
		String CurrentOrderTable = """
				CREATE TABLE CurrentOrderTable(
					OrderID INTEGER NOT NULL,
					orderItem VARCHAR(256) NOT NULL,
					TableNo INTEGER NOT NULL,
					CompletePhase INTEGER NOT NULL,
					timeStarted DATETIME NOT NULL,
					PRIMARY KEY (OrderID)
				);
				""";
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE IF EXISTS CurrentOrderTable;");
		statement.execute(CurrentOrderTable);
	}
	
	
	public void addToOrderTable() throws ClassNotFoundException, SQLException {
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
	
	public int addpnum(String table) throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		ResultSet LastPrimarykey = statement.executeQuery("SELECT COUNT(*) FROM " + table +";");
		int addnew = LastPrimarykey.getInt(0) +1;
		return addnew;
	}
	
	public void inputIntoCtable(String item, int tableNO) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		String menuItem = "SELECT ItemCode FROM MenuTable WHERE Name = '"+item+"';";
		int primary_key = addpnum("CurrentOrderTable");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		String sql = "INSERT INTO CurrentOrderTable VALUES ("+primary_key+", "+menuItem+ ", "+ tableNO+ ", 0"+ dtf.format(now) + ");";
		statement.execute(sql);
	}
	
	
}
