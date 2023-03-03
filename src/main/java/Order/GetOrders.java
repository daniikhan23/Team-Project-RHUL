package Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;

public class GetOrders {

	public static String ListOrder(int orderNO) throws ClassNotFoundException, SQLException {
		String output = "";
		
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		String SQL = "SELECT orderItem FROM OrderTable WHERE OrderNO = "+orderNO+";";
		ResultSet rs = statement.executeQuery(SQL);
		
		while (rs.next()) {
			output += "<li>"+rs.getString(1)+"</li>";
		}
		
		return output;
	}
	
	public static String basicoutput() throws ClassNotFoundException, SQLException {
		String result = "";
		
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		ResultSet output = statement.executeQuery("SELECT OrderNO FROM orderTable;");

		while (output.next()) {
			result += "<button class=\"collapsible\">#"+output.getInt(1)+"</button>"+
		"  <div class=\"content\">\r\n"
		+ "    <ul>"+
				ListOrder(output.getInt(1))+
        "    </ul>\r\n"
        + "  </div>";
					
		}
		System.out.println(result);
		return result;
	}
	

}
