package Order;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import DB.connection.Database;

class GetOrdersTest {
	
	@Test
    public void testListOrder() throws SQLException, ClassNotFoundException {
        int orderNO = 1; // provide an existing order number for testing
        String expectedOutput = "<li>Item 1</li><li>Item 2</li><li>Item 3</li>"; // provide the expected output for the given order number
        
        // create a mock ResultSet object to return the test data
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(true, true, true, false);
        when(mockResultSet.getString(1)).thenReturn("Item 1", "Item 2", "Item 3");
        
        // create a mock Statement object to return the mock ResultSet
        Statement mockStatement = mock(Statement.class);
        when(mockStatement.executeQuery(any(String.class))).thenReturn(mockResultSet);
        
        // create a mock Connection object to return the mock Statement
        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        
        // call the ListOrder method with the mock connection
        Database.connectToDatabase();
        String result = GetOrders.ListOrder(orderNO);
        
        // verify that the expected SQL query was executed
        verify(mockStatement).executeQuery("SELECT orderItem FROM OrderTable WHERE OrderNO = " + orderNO + ";");
        
        assertNotNull(result);
        
        // verify that the output matches the expected output
        assertEquals(expectedOutput, result);
	}
        
	
    @Test
    public void testBasicoutput() throws SQLException, ClassNotFoundException {
        String expectedOutput = "<button class=\"collapsible\">#1</button>  <div class=\"content\">\r\n    <ul><li>Item 1</li><li>Item 2</li><li>Item 3</li></ul>\r\n  </div>"; // provide the expected output for the available orders
        
        String result = GetOrders.basicoutput();
        assertEquals(expectedOutput, result);
    }

}
