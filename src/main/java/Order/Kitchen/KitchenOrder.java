package Order.Kitchen;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;

public class KitchenOrder {
	
	public ResultSet getOrderTimes() {
		 String sql = "SELECT OrderID, timeStarted FROM OrderTable WHERE CompletePhase = 0;";
		 Connection connection = Database.connectToDatabase();
		 Statement statement = connection.createStatement();
		 ResultSet rs = statement.executeQuery(sql);
		 return rs;
	 }
}