package DB.connection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CustomerTest {
	private Customer customer;
	
	@BeforeEach
	public void setup() throws ClassNotFoundException {
	customer = new Customer();
	}

	@Test
	public void testCustomerExist() {
	  try {
	    Class.forName("DB.connection.Customer");
	  }catch(ClassNotFoundException e) { //checks if class exists.
	     Assert.fail("Customer yet to be implemented");
	  }
	}
	    
	@Test
	void testException() {
		assertThrows(SQLException.class, () -> customer.getMenu("test"));
	}
	
	@Test
    public void testGetMenu() throws SQLException, ClassNotFoundException {
        String expected = "Salad 5.0\nSoup 6.0\n";
        String actual = customer.getMenu("Appetizer");
        assertEquals(expected, actual);
        
        String expected2 = "";
        String actual2 = "non-existing-category";
        assertEquals(expected2, actual2);
        
        String category = null;
        assertThrows(NullPointerException.class, () -> customer.getMenu(category));
        
        String expected3 = "";
        String actual3 = "";
        assertEquals(expected3, actual3);
    }
}