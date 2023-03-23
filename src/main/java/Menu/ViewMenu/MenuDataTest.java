package Menu.ViewMenu;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import DB.connection.Database;

public class MenuDataTest {

	private MenuData menuData;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	@Before
	public void setUp() throws Exception {
		menuData = new MenuData();

		// Mocking the database connection
		connection = mock(Connection.class);
		statement = mock(Statement.class);
		resultSet = mock(ResultSet.class);
		when(connection.createStatement()).thenReturn(statement);
		when(statement.executeQuery("SELECT Name, Cost FROM MenuTable WHERE category = 'test';")).thenReturn(resultSet);
		when(statement.executeQuery("SELECT Name FROM MenuTable;")).thenReturn(resultSet);
	}

	@Test
	public void testGetMenu() throws SQLException, IOException, ClassNotFoundException {
		// Mocking the ResultSet
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		when(resultSet.getString("Name")).thenReturn("testItem1");
		when(resultSet.getDouble("Cost")).thenReturn(10.0);
		
		String categoryMenu = menuData.getMenu("test");
		
		assertEquals("", categoryMenu); // Replace with expected output
	}

	@Test
	public void testFillItemList() throws SQLException, IOException, ClassNotFoundException {
		String result = menuData.fillItemList();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	@Test
	public void testUpdateStock() throws SQLException, IOException, ClassNotFoundException {
		String item = "Wings";
		String stock = "5";
		menuData.updateStock(item, stock);

		Connection connection = Database.connectToDatabase();
		Statement st = connection.createStatement();
		String sql = "SELECT * FROM MenuTable WHERE Name = '" + item + "';";
		ResultSet rs = st.executeQuery(sql);

		int updatedStock = 0;
		if (rs.next()) {
			updatedStock = rs.getInt(1);
		}
		assertEquals(updatedStock, Integer.parseInt(stock));
	}
}
