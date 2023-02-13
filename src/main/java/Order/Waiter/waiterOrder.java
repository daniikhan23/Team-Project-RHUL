package Order.Waiter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;

public class waiterOrder {

	public String getOrder() {
		
		
		
		
		String SQL = """
				<div id="order2">
  <button class="collapsible">Order #58835</button>
  <div class="content">
    <ul>
      <li>Mushroom Swiss Burger</li>
      <li>Lobster Pie Pizza</li>
      <li>Fried Mozzarella</li>
      <li>Crabby Sweets</li>
    </ul>
    <button class="order-out" onclick="document.getElementById('order2').style.display = 'none';">Order Out</button>
  </div>
</div>
				""";
		
		
		return SQL;
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

}
