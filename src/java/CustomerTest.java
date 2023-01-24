

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class CustomerTest {

	
	
	  @Test
	  public void testCustomerExist(){
	    try {
	      Class.forName("Customer");
	    }catch(ClassNotFoundException e) { //checks if class exist.
	       Assert.fail("Customer yet to be implemented");
	    }
	  }
	    
	@Test
	void GetMenu() {
		
	}
}
