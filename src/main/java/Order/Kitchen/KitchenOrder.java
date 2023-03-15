package Order.Kitchen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.connection.Database;

public class KitchenOrder {
	
	
	public void SendOrder() {
		//Send order to waiter after finishing
	}
	
	public void SendOrder(String item, int OrderNO) {
		//Send specific item to waiter
	}
	
	public void SendMessage(String Message) {
		//TEXT for DB send to waiter side
	}
	
	public void GetMessage() {
		//get message from waiter
	}
	
	public String getOrder() throws SQLException, ClassNotFoundException {
		String AllOrders = "SELECT DISTINCT OrderNo,tableno FROM OrderTable WHERE CompletePhase > 0 ORDER BY OrderNO;";
		
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		

		ResultSet OrderNo = statement.executeQuery(AllOrders);
		String frontEndView = "";
		List<Integer> Orderlist = new ArrayList<Integer>();
		
		while (OrderNo.next()) {
			Orderlist.add(OrderNo.getInt(1));
			Orderlist.add(OrderNo.getInt(2));
		}
		
		System.out.println("full order: " +Orderlist);

		
		for (int i = 0; i< Orderlist.size(); i+=2) {
			String itemsSQL = "SELECT orderItem FROM OrderTable WHERE OrderNo = "+ Orderlist.get(i)+";";
			ResultSet ItemOrder = statement.executeQuery(itemsSQL);
			
			String container = "<button style = \"color:"+getcompleteness(Orderlist.get(i))+"; \"class=\"collapsible\">Order #"+Orderlist.get(i)+ "      Table:"+Orderlist.get(i+1)+"</button>"+
					"<div class=\"content\">\r\n"
					+ "<ul>";
			while (ItemOrder.next()) {
				container += "<li style = \"color:"+ getcompleteness(Orderlist.get(i), ItemOrder.getString(1))+">"+ ItemOrder.getString(1);
				container += "<form action=\"cancelorder\" method=\"post\">";
				container += "<input type=\"submit\" name=\"Cancel Item\" value=\"Cancel Item\" id=\"cancelorder\"/>";
				container += "<input type= \"hidden\" name=\"OrderCancel\" value=\"" + Orderlist.get(i)+ "\">";
				container += "<input type= \"hidden\" name=\"OrderItem\" value=\"" + ItemOrder.getString(1)+ "\">";
				container += "</form>";
				container += "</li>";
			}
			container += "</ul>";
			container += "<form action=\"cancelorder\" method=\"post\">";

			container += "<input type=\"submit\" name=\"Cancel Order\" value=\"Cancel Order\" id=\"cancelorder\"/>"+"\n";
			container += "<input type= \"hidden\" name=\"OrderCancel\" value=\"" + Orderlist.get(i)+ "\">";
			container += "</form>";
			
		
			
			container += "<button onclick = \"finishedorder("+Orderlist.get(i)+")\">Finished Order</button></div>";
			frontEndView += container;
		}
		return frontEndView;
	}
	public String getcompleteness(int order) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "SELECT CompletePhase FROM ordertable WHERE orderno = "+order+" ORDER BY CompletePhase DESC;";
		ResultSet rs = statement.executeQuery(SQL);
		rs.next();
		
		if (rs.getInt(1) == 3) {
			String sql = "SELECT CompletePhase FROM ordertable WHERE orderno = "+order+" ORDER BY CompletePhase ASC;";
			ResultSet testcase = statement.executeQuery(sql);
			testcase.next();
			if (testcase.getInt(1) == 3) {
				return "green";
			}
			else {
				return "yellow";
			}
		}
		else if (rs.getInt(1) == 2) {
			return "yellow";
		}
		else if (rs.getInt(1) == 1) {
			return "red";
		}
		else {
			return "black";
		}
	}
	
	public String getcompleteness(int order, String item) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String SQL = "SELECT CompletePhase FROM ordertable WHERE orderno = "+order+" AND orderItem = '"+item+"';";
		ResultSet rs = statement.executeQuery(SQL);
		rs.next();
		
		if (rs.getInt(1) == 3) {
			return "green";
		}
		else if (rs.getInt(1) == 2) {
			return "yellow";
		}
		else if (rs.getInt(1) == 1) {
			return "red";
		}
		else {
			return "black";
		}
	}
}
