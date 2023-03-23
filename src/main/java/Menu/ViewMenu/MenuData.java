package Menu.ViewMenu;


import java.io.IOException;
import java.sql.*;

import DB.connection.Database;
import Order.Customer.CustomerOrder;

/*
 * how the menu data is formatted and outputed as a whole.
 */
public class MenuData {
	/*
	 * gets the menu and expresses it to the frontend
	 * @return the menu buttons and items
	 */
	public String getMenu(String Category) throws SQLException, IOException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement st = connection.createStatement();
		// Only called with constant values for Category so no risk of injection
		String sql = "SELECT Name, Cost " +
					 "FROM MenuTable " +
					 "WHERE category = '" + Category + "';";
		//<% Order.inputIntoCtable(\""+rs.getString(0)+"\", 1);
		ResultSet rs = st.executeQuery(sql);
		String categoryMenu = "";
		while (rs.next()) {
			categoryMenu += "<div class=\"menu-item\">"+ "\n"+
		                "<div class=\"menu-item-text\">"+ "\n"+
		                    "<h3 class=\"menu-item-heading\">"+ "\n"+
		                    "<form action=\"CustomerOrderItem\" method=\"post\">"+"\n"+
		                    "<span>"+ CustomerOrder.numberOfitem(rs.getString(1), 1)+"  </span>"+
		                    "<input type=\"submit\" name=\"-\" value=\"-\" id=\"remove-submit\"/>"+"\n"+
		                    "<input type=\"submit\" name=\"+\" value=\"+\" id=\"add-submit\"/>"+"\n"+
		                    "<input type= \"hidden\" name=\"MenuItem\" value=\"" + rs.getString(1)+ "\">"+"\n"+
		                        "<span class=\"menu-item-name\">"+rs.getString(1)+"</span>"+ "\n"+
		                        "<span class=\"menu-item-price\">£"+rs.getString(2)+"</span>"+ "\n"+
		                    "</form>"+"\n"+
		                    "</h3>"+ "\n"+
		                "</div>"+ "\n"+
		            "</div>" +"\n";
		}
		
		return categoryMenu;
	}
	/*gets all valid items from the menu page */
	public String fillItemList() throws SQLException, IOException, ClassNotFoundException {
		  Connection connection = Database.connectToDatabase();
		  Statement st = connection.createStatement();
		  String sql = "SELECT Name " +
		      "FROM MenuTable;";
		  ResultSet rs = st.executeQuery(sql);
		  String items = "";
		  int i = 1;
		  while (rs.next()) {
		    items += "<option value=\"" + i + "\">"+rs.getString(1)+"</option>";
		    i++;
		  }
		  return items;
		  }
		  
		  public void updateStock(String item, String Stock) throws SQLException, IOException, ClassNotFoundException {
		    Connection connection = Database.connectToDatabase();
		    
		    String sql = "UPDATE MenuTable SET Stock = ? WHERE Name = ?;";
		    PreparedStatement ps = connection.prepareStatement(sql);
		    ps.setInt(1, Integer.parseInt(Stock));
		    ps.setString(2, item);
		    ps.executeUpdate();
		  }

}
