package Order.Waiter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;

public class waiterOrder {
	
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
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		int numberofOrders = statement.executeQuery(NumberOfCurrentOrders).getInt(0);
		String frontEndView = "";
		while (orderSet <= numberofOrders) {
			
		}
		
		return frontEndView;
	}

}
