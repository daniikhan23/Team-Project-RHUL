package Menu.ViewMenu;


import java.io.IOException;
import java.sql.*;
import java.util.*;
import DB.connection.*;

public class MenuData {
	
	public String getMenu(String Category) throws SQLException, IOException, ClassNotFoundException {
	    Connection connection = Database.connectToDatabase();
	    Statement st = connection.createStatement();
	    System.out.println("Connection object: " + st);
	    String sql = "SELECT mt.ItemCode, mt.Name, mt.Cost, i.Name " +
	                 "FROM MenuTable mt " +
	                 "JOIN MenuItemIngredients mti ON mt.ItemCode = mti.ItemCode " +
	                 "JOIN Ingredients i ON mti.IngredientID = i.IngredientID " +
	                 "WHERE mt.Category = '" + Category + "';";

	    ResultSet rs = st.executeQuery(sql);
	    Map<String, List<String>> items = new HashMap<>();
	    while (rs.next()) {
	        String name = rs.getString(2);
	        String price=rs.getString(3);
	        String ingredient = rs.getString(4);
	        if (!items.containsKey(name)) {
	            items.put(name, new ArrayList<>());
	            items.get(name).add(price);
	        }
	        items.get(name).add(ingredient);
	    }

	    String categoryMenu = "";
	    for (String name : items.keySet()) {
	    	List<String> ingredientList = items.get(name);
	    	String ingredients = String.join(", ", ingredientList.subList(1, ingredientList.size()));
 categoryMenu += "<div class=\"menu-item\">\n" +
	                        "<div class=\"menu-item-text\">\n" +
	                        "<h3 class=\"menu-item-heading\">\n" +
	                        "<span class=\"menu-item-name\">" + name + "</span>\n" +
	                        "<span class=\"menu-item-price\">£" +items.get(name).get(0)+ "</span>\n" +
	                        "<span class=\"menu-item-ingredient\">" + ingredients + "</span>\n" +
	                        "</h3>\n" +
	                        "</div>\n" +
	                        "</div>\n";
	    }

	    return categoryMenu;
	}
	
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
		    Statement st = connection.createStatement();
		    
		    String sql = "UPDATE MenuTable SET Stock = "+ Integer.parseInt(Stock) + " WHERE Name = " + item + ";";
		    st.executeQuery(sql);
		  }

}