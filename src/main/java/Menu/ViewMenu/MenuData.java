package Menu.ViewMenu;


import java.sql.*;

import DB.connection.Database;

public class MenuData {
	
	public void getMenu(String Category) throws SQLException {
		Connection connection = Database.connectToDatabase();
		Statement st = connection.createStatement();
		String sql = "SELECT Name, Cost " +
					 "FROM Menu " +
					 "WHERE Category = \"" + Category + "\";";
		
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			
			String categoryMenu = "<div class=\"menu-item\">"+
		                "<div class=\"menu-item-text\">"+
		                    "<h3 class=\"menu-item-heading\">"+
		                        "<span class=\"menu-item-name\">+rs.getString(1)+</span>"+
		                        "<span class=\"menu-item-price\">£"+rs.getString(2)+"</span>"+
		                    "</h3>"+
		                "</div>"+
		            "</div>";
			System.out.println(categoryMenu);
		}
	}
	
	
}