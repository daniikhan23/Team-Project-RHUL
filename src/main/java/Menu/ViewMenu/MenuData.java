package Menu.ViewMenu;


import java.io.IOException;
import java.sql.*;

import DB.connection.Database;

public class MenuData {
	
	public String getMenu(String Category) throws SQLException, IOException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement st = connection.createStatement();
		String sql = "SELECT Name, Cost " +
					 "FROM MenuTable " +
					 "WHERE category = '" + Category + "';";
		
		ResultSet rs = st.executeQuery(sql);
		String categoryMenu = "";
		while (rs.next()) {
			
			categoryMenu += "<div class=\"menu-item\">"+ "\n"+
		                "<div class=\"menu-item-text\">"+ "\n"+
		                    "<h3 class=\"menu-item-heading\">"+ "\n"+
		                        "<span class=\"menu-item-name\">"+rs.getString(1)+"</span>"+ "\n"+
		                        "<span class=\"menu-item-price\">£"+rs.getString(2)+"</span>"+ "\n"+
		                    "</h3>"+ "\n"+
		                "</div>"+ "\n"+
		            "</div>" +"\n";
			
		}
		
		return categoryMenu;
	}
	
	
}