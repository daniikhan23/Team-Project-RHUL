package Login.Bean;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LoginBeanTest {
	
	private LoginBean loginBean;
	
	@Before
	public void setUp() throws Exception {
		loginBean = new LoginBean();
	}
    
    @Test
    public void testGettersAndSetters() {
    	
    	loginBean.setUsername("testuser");
        loginBean.setPassword("testpassword");
      
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        
        assertEquals("testuser", username);
        assertEquals("testpassword", password);
    }
}
