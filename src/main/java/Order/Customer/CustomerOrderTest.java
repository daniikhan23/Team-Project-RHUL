package Order.Customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import DB.connection.Database;

class CustomerOrderTest {

	CustomerOrder order = new CustomerOrder();
	Connection connection = mock(Connection.class);
	Statement statement = mock(Statement.class);
	ResultSet resultSet = mock(ResultSet.class);

	@Test
	public void testCurrentOrder() throws ClassNotFoundException, SQLException {
		// Create a temporary table for testing
		String CurrentOrderTable = """
				CREATE TEMPORARY TABLE CurrentOrderTable(
				    OrderID INTEGER NOT NULL,
				    orderItem VARCHAR(256) NOT NULL,
				    TableNo INTEGER NOT NULL,
				    CompletePhase INTEGER NOT NULL,
				    timeStarted TIMESTAMP NOT NULL,
				    PRIMARY KEY (OrderID)
				);
				""";
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		statement.executeUpdate(CurrentOrderTable);

		// Call the CurrentOrder method and check that it executes without errors
		try {
			order.CurrentOrder();
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}

		// Check that the table was created successfully
		ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'CurrentOrderTable'");
		assertTrue(resultSet.next());
		assertEquals("CurrentOrderTable", resultSet.getString(1));

		// Clean up the temporary table
		statement.executeUpdate("DROP TABLE CurrentOrderTable");
	}

	@Test
	public void testAddToOrderTable() throws ClassNotFoundException, SQLException {
		// Create a temporary OrderTable and CurrentOrderTable for testing
		String OrderTable = """
				CREATE TEMPORARY TABLE OrderTable(
				    OrderNO INTEGER NOT NULL,
				    PRIMARY KEY (OrderNO)
				);
				""";
		String CurrentOrderTable = """
				CREATE TEMPORARY TABLE CurrentOrderTable(
				    OrderID INTEGER NOT NULL,
				    orderItem VARCHAR(256) NOT NULL,
				    TableNo INTEGER NOT NULL,
				    CompletePhase INTEGER NOT NULL,
				    timeStarted TIMESTAMP NOT NULL,
				    PRIMARY KEY (OrderID)
				);
				INSERT INTO CurrentOrderTable VALUES (1, 'Item 1', 1, 0, NOW());
				INSERT INTO CurrentOrderTable VALUES (2, 'Item 2', 2, 0, NOW());
				""";
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		statement.executeUpdate(OrderTable);
		statement.executeUpdate(CurrentOrderTable);

		// Call the addToOrderTable method and check that it executes without errors
		try {
			order.addToOrderTable(3);
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}

		// Check that the OrderTable was updated correctly
		ResultSet resultSet = statement.executeQuery("SELECT * FROM OrderTable;");
		assertTrue(resultSet.next());
		assertEquals(1, resultSet.getInt("OrderNO"));
		assertTrue(resultSet.next());
		assertEquals(2, resultSet.getInt("OrderNO"));
		assertTrue(resultSet.next());
		assertEquals(3, resultSet.getInt("OrderNO"));
		assertFalse(resultSet.next());
	}

	@Test
	public void testAddpnum() throws SQLException, ClassNotFoundException {

		when(Database.connectToDatabase()).thenReturn(connection);
		when(connection.createStatement()).thenReturn(statement);
		when(statement.executeQuery("SELECT COUNT(*) FROM test_table;")).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		when(resultSet.getInt(1)).thenReturn(0);

		int result = order.addpnum("test_table");

		assertEquals(1, result);
	}

	@Test
    public void testInputIntoCtable() throws ClassNotFoundException, SQLException {
        // Configure the mock objects to return expected values
        when(Database.connectToDatabase()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);

        // Call the method being tested
        String item = "Test Item";
        int tableNo = 1;
        order.inputIntoCtable(item, tableNo);

        // Verify that the expected SQL statement was executed
        int expectedPrimaryKey = 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String expectedSql = "INSERT INTO CurrentOrderTable VALUES (" + expectedPrimaryKey + ", '" + item + "', " + tableNo + ", 0, '" + dtf.format(now) + "');";
        verify(statement).execute(expectedSql);
    }

	@Test
	public void testNumberOfitem() throws SQLException, ClassNotFoundException {
		when(Database.connectToDatabase()).thenReturn(connection);
		when(connection.createStatement()).thenReturn(statement);
		when(statement.executeQuery("SELECT COUNT(*) FROM CurrentOrderTable WHERE orderItem = 'Test Item' AND TableNo = '1' AND CompletePhase = 0;")).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		when(resultSet.getInt(1)).thenReturn(2);

		int result = order.numberOfitem("Test Item", 1);

		assertEquals(2, result);
	}

	@Test
    public void testRemoveFromTable() throws ClassNotFoundException, SQLException {
		
		 when(connection.createStatement()).thenReturn(statement);
	     when(statement.executeQuery(toString())).thenReturn(resultSet);
	     when(resultSet.next()).thenReturn(true).thenReturn(false);
	     when(resultSet.getInt(1)).thenReturn(123);
	        
        String item = "foo";
        int tableNO = 1;

        order.removefromtable(item, tableNO);

        ((Statement) verify(resultSet)).execute("UPDATE CurrentOrderTable SET CompletePhase = 1 WHERE OrderID = 123;");
    }
	
	@Test
	public void testGetCurrentOrder() throws ClassNotFoundException, SQLException {
		// Set up test data
		int tableNo = 1;
		String expectedOutput = "<li>item1  -  £5.00</li><li>item2  -  £3.50</li>";
		
		// Call the method to get actual output
		String actualOutput = order.getCurrentOrder(tableNo);
		
		// Assert that the actual output matches the expected output
		assertEquals(expectedOutput, actualOutput);
	}
	
	// Helper method to mock getitemcost method
	private double getitemcost(String item) {
		// Return hardcoded cost for testing purposes
		return 5.00;
	}
	
	@Test
	public void testGetItemCost() throws ClassNotFoundException, SQLException {
		// Set up test data
		String item = "burger";
		int expectedCost = 10;
		
		// Call the method to get actual cost
		int actualCost = order.getitemcost(item);
		
		// Assert that the actual cost matches the expected cost
		assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void testTotalCost() throws ClassNotFoundException, SQLException {
		// Set up test data
		int tableNo = 1;
		int expectedCost = 20; // assuming there are two items, each costing 10
		
		// Call the method to get actual cost
		int actualCost = order.totalcost(tableNo);
		
		// Assert that the actual cost matches the expected cost
		assertEquals(expectedCost, actualCost);
	}
}
