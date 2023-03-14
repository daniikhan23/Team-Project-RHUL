package DB.connection;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabaseTest {

	
	@Test
	void testMain() {
	    Assertions.assertDoesNotThrow(() -> {  //Test for database connection
	        Database.main(null);; 
	      });
	}

	@Test
    void testInitialiseTable() throws SQLException, ClassNotFoundException {
        Connection connection = Database.connectToDatabase();
        Statement statement = connection.createStatement();
        Database.initialiseTable("TestTable", statement);
    }

	@Test
	void testInsert() throws ClassNotFoundException {
		String[] testString = {"TestValue1", "Test2"};
		Database.Insert(testString, "TestDB");
		Database.connectToDatabase();
	}

	@Test
	void testIsInt() {
		assertEquals(Database.IsInt("1"), true);
		assertEquals(Database.IsInt("abc"), false);
		assertEquals(Database.IsInt("15.2"), false);
		assertEquals(Database.IsInt(""), false);
		assertEquals(Database.IsInt(null), false);
	}
	
	@Test
	void testConnectToDatabase() throws SQLException, ClassNotFoundException {
        Connection connection = Database.connectToDatabase();
        Assertions.assertNotNull(connection, "Connection should not be null");
    }
}