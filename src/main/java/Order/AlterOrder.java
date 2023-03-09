package Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;

public class AlterOrder { //add all order to accepted order
	public void acceptorder(int order) throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "UPDATE ordertable SET CompletePhase = 1 WHERE OrderNO = "+order+";";
		statement.execute(SQL);
	}
	
	public void acceptitem(int order, String item) throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "UPDATE ordertable SET CompletePhase = 1 WHERE OrderNO = "+order+" AND orderItem = '"+item+"';";
		statement.execute(SQL);
	}
	
	public void cancelorder(int order) throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "UPDATE ordertable SET CompletePhase = -1 WHERE OrderNO = "+order+";";
		statement.execute(SQL);
	}
	
	public void cancelitem(int order, String item) throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "UPDATE ordertable SET CompletePhase = -1 WHERE OrderNO = "+order+" AND orderItem = '"+item+"';";
		statement.execute(SQL);
	}
	
	
}
