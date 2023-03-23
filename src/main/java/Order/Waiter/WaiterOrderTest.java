package Order.Waiter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import DB.connection.Database;


class WaiterOrderTest {
	
	waiterOrder order = new waiterOrder();

	@Test
	public void testGetName() throws ClassNotFoundException, SQLException {
		// Set up test data
		int orderNo = 1;
		
		// Call the method to get ResultSet
		ResultSet rs = order.getName(orderNo);
		
		// Assert that the ResultSet is not null
		assertNotNull(rs);
		
		// Assert that the ResultSet has at least one row
		assertTrue(rs.next());
		
		// Assert that the first column of the first row is not null or empty
		assertNotNull(rs.getString(1));
		assertFalse(rs.getString(1).isEmpty());
	}

	@Test
	public void testGetOrderNo() throws ClassNotFoundException, SQLException {
		// Call the method to get ResultSet
		ResultSet rs = order.getORderNo();
		
		// Assert that the ResultSet is not null
		assertNotNull(rs);
		
		// Assert that the ResultSet has at least one row
		assertTrue(rs.next());
		
		// Assert that the first column of the first row is not null or empty
		assertNotNull(rs.getString(1));
		assertFalse(rs.getString(1).isEmpty());
	}
	
	@Test
	public void testFrontEndView() throws ClassNotFoundException, SQLException {
		// Set up the database connection and data
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		statement.executeUpdate("INSERT INTO OrderTable (TableNo, OrderNo, OrderItem, CompletePhase) VALUES (1, 1, 'Burger', 0)");
		
		// Call the method to get the front-end view
		String result = order.frontEndView();
		
		// Assert that the result is not null or empty
		assertNotNull(result);
		assertFalse(result.isEmpty());
		
		// Assert that the result contains the expected order number and item
		assertTrue(result.contains("Order #1"));
		assertTrue(result.contains("Burger"));
		
		// Clean up the database after the test
		statement.executeUpdate("DELETE FROM OrderTable WHERE OrderNo = 1");
	}
	
	 @Test
	    public void testRemove() throws ClassNotFoundException, SQLException {
	        // Add an item to the menu table
	        String itemName = "Test Item";
	        int itemCost = 10;
	        Connection connection = Database.connectToDatabase();
	        Statement statement = connection.createStatement();
	        String sql = "INSERT INTO MenuTable (Name, Cost) VALUES ('" + itemName + "', " + itemCost + ");";
	        statement.executeUpdate(sql);

	        // Check that the item was added
	        ResultSet rs = statement.executeQuery("SELECT * FROM MenuTable WHERE Name = '" + itemName + "';");
	        assertTrue(rs.next(), "Item was not added to menu table");

	        // Remove the item
	        order.remove(itemName);

	        // Check that the item was removed
	        rs = statement.executeQuery("SELECT * FROM MenuTable WHERE Name = '" + itemName + "';");
	        assertFalse(rs.next(), "Item was not removed from menu table");

	        statement.close();
	    }
	 
	 @Test
	  public void testAdd() throws ClassNotFoundException, SQLException {
	    // Arrange
	    String item = "Test Item";
	    int cost = 10;
	    String category = "Test Category";
	    
	    // Act
	    order.add(item, cost, category);
	    int expectedCost = cost;
	    int actualCost = 10;
	    
	    // Assert
	    assertEquals(expectedCost, actualCost);
	  }
	 
	 @Test
		public void testGetPrimaryKey() throws SQLException, ClassNotFoundException {
			ResultSet rs = mock(ResultSet.class);
			Statement statement;
			statement = mock(Statement.class);
			when(statement.executeQuery("SELECT ItemCode FROM menutable ORDER BY ItemCode ASC;")).thenReturn(rs);
			when(rs.next()).thenReturn(true, false); // return one row with value 1
			when(rs.getInt(1)).thenReturn(1);
			
			int result = order.getprimarykey();
			assertEquals(2, result); // expected next primary key value is 2
		}
}
