
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import DB.connection.Database;

class DatabaseTest {

	
	@Test
	void testMain() {
	    Assertions.assertDoesNotThrow(() -> {  //Test for database connection
	        Database.main(null);; 
	      });
	}


	@Test
	void testInsert() {
		String[] testString = {"TestValue1", "Test2"};
		Database.Insert(testString, "TestDB");
		Database.connectToDatabase();
	}

	@Test
	void testIsInt() {
		Assertions.assertEquals(Database.IsInt("5"), true);
	}

}
