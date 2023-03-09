package Order.Kitchen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;

public class KitchenOrder {
	
	public ResultSet getOrder(int tableNo) throws ClassNotFoundException, SQLException {
		 String sql = "SELECT * FROM OrderTable WHERE TableNo = " + tableNo + " AND CompletePhase = 0;";
		 Connection connection = Database.connectToDatabase();
		 Statement statement = connection.createStatement();
		 ResultSet rs = statement.executeQuery(sql);
		 return rs;
	 }
	
	public ResultSet getOrderTimes() throws ClassNotFoundException, SQLException {
		 String sql = "SELECT OrderID, timeStarted FROM OrderTable WHERE CompletePhase = 0;";
		 Connection connection = Database.connectToDatabase();
		 Statement statement = connection.createStatement();
		 ResultSet rs = statement.executeQuery(sql);
		 return rs;
	 }
	
	public ResultSet removeOrder(int orderNo) throws ClassNotFoundException, SQLException {
		 String sql = "DELETE FROM OrderTable WHERE OrderNo = " + orderNo + " AND CompletePhase = 1;";
		 Connection connection = Database.connectToDatabase();
		 Statement statement = connection.createStatement();
		 ResultSet rs = statement.executeQuery(sql);
		 return rs;
	 }
}