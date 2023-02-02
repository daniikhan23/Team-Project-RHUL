
import java.sql.*;

public class Customer {
	
	public String getMenu(String Category) throws SQLException {
		String SegmentMenu = "";
		
		Connection connection = Database.connectToDatabase();
		Statement st = connection.createStatement();
		String sql = "SELECT Name, Cost " +
					 "FROM Menu " +
					 "WHERE Category = \"" + Category + "\";";
		
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			  String output1 = rs.getString(1);
              String output2 = rs.getString(2);
              
              SegmentMenu += output1 + " " + output2 +"\n";
		}
		
		return SegmentMenu;
	}
	
	/*
	public submitButton() {
		ALL ITEMS ADD TO DATABASE AS ORDER ID
	}
	*/
	
}