package Order.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;

public class CustomerOrder {
	
	public void CurrentOrder() throws ClassNotFoundException, SQLException {
		String CurrentOrderTable = """
				CREATE TABLE CurrentOrderTable(
					OrderID INTEGER NOT NULL,
					orderItem VARCHAR(256) NOT NULL,
					TableNo INTEGER NOT NULL,
					CompletePhase INTEGER NOT NULL,
					timeStarted DATE NOT NULL,
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
		
		ResultSet rs = statement.executeQuery("SELECT * FROM CurrentOrder;");
		String sql = "INSERT INTO OrderTable VALUES(";

		ResultSet OrderNo = statement.executeQuery("SELECT OrderNO FROM OrderTable ORDER BY OrderNO;");
		ResultSet LastPrimarykey = statement.executeQuery("SELECT OrderID FROM OrderTable ORDER BY OrderID DESC;");
		int NewOrderNo = OrderNo.getInt(0) + 1;
		int NewOrderID = LastPrimarykey.getInt(0)+1;

		while (rs.next()) {
			statement.execute(sql+NewOrderID+",'"+rs.getString(1)+"', "+rs.getString(2)+", 0, "+rs.getString(4)+", "+NewOrderNo);
			NewOrderID ++;
		}
	}
	
	
	
}
