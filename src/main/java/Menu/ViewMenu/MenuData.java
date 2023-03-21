package Menu.ViewMenu;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jakartaee.commons.lang3.StringEscapeUtils;

import DB.connection.Database;
import Order.Customer.CustomerOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MenuData {
	public String item,stock;
	
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
   System.out.println("reached");
   if (request.getParameter("item-update") != null && request.getParameter("stock-field") != null) {
     item = request.getParameter("item-update");
     stock = request.getParameter("stock-field");
   }
   
   try {
       updateStock(item,stock);
     } catch (ClassNotFoundException | SQLException | IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
     }
   response.sendRedirect("waiterMenu.jsp");
 }
 
	public String getMenu(String Category) throws SQLException, IOException, ClassNotFoundException {
		// Only called with constant values for Category so no risk of injection
	    String sql = "SELECT mt.ItemCode, mt.Name, mt.Cost, i.Name " +
                "FROM MenuTable mt " +
                "JOIN MenuItemIngredients mti ON mt.ItemCode = mti.ItemCode " +
                "JOIN Ingredients i ON mti.IngredientID = i.IngredientID " +
                "WHERE mt.Category = '" + Category + "';";
		
		Connection connection = Database.connectToDatabase();
		Statement st = connection.createStatement();

		//<% Order.inputIntoCtable(\""+rs.getString(0)+"\", 1);
		ResultSet rs = st.executeQuery(sql);
		Map<String, List<String>> items = new HashMap<>();
		
		String colour = "red";
		while (rs.next()) {//need to change for specific table
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
		   	
		   	
			categoryMenu += "<div class=\"menu-item\">"+ "\n"+
		                "<div class=\"menu-item-text\">"+ "\n"+
		                    "<h3 class=\"menu-item-heading\">"+ "\n"+
		                    "<form action=\"CustomerOrderItem\" method=\"post\">"+"\n"+
		                    "<span>"+ CustomerOrder.numberOfitem(name, 1)+"  </span>"+
		                    "<input style = \"background-color:"+colour+" \" type=\"submit\" name=\"-\" value=\"-\" id=\"remove-submit\"/>"+"\n"+
		                    "<input style = \"background-color:"+colour+" \" type=\"submit\" name=\"+\" value=\"+\" id=\"add-submit\"/>"+"\n"+
		                    "<input type= \"hidden\" name=\"MenuItem\" value=\"" + StringEscapeUtils.escapeHtml4(name) + "\">"+"\n"+
		                        "<span class=\"menu-item-name\">"+StringEscapeUtils.escapeHtml4(name)+"</span>"+ "\n"+
		                        "<span class=\"menu-item-price\">£"+items.get(name).get(0)+"</span>"+ "\n"+
		                    "</form>"+"\n"+
		                    "</h3>"+ "\n"+
		                    "<span class=\"menu-item-ingredient\" style='margin-left: 9em; margin-top:-1em;'>" + StringEscapeUtils.escapeHtml4(ingredients) + "</span>\n" +"</span>"+ 
		                "</div>"+ "\n"+
		                    
		            "</div>" +"\n";
			if (colour.equals("red")) {
				colour = "#FFBF00";
			}
			
			else if (colour.equals("#FFBF00")) {
				colour = "#006847";
			}
			else {
				colour = "red";
			}
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
		  while (rs.next()) {
			String item = StringEscapeUtils.escapeHtml4(rs.getString(1));
		    items += "<option value=\"" + item + "\">"+item+"</option>";
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