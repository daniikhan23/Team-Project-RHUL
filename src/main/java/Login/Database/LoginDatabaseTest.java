package Login.Database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Login.Bean.LoginBean;

class LoginDatabaseTest {

	private LoginBean loginBean;
	private LoginBean loginBean2;
	private LoginBean loginBean3;
	private LoginDatabase loginDatabase;

	@BeforeEach
	public void setUp() {
		loginBean = new LoginBean();
		loginBean2 = new LoginBean();
		loginBean3 = new LoginBean();
		loginBean.setUsername("Staff1");
		loginBean.setPassword("Password1");
		loginBean2.setUsername("InvalidUser");
		loginBean2.setPassword("InvalidPassword");
		loginBean3.setUsername("Staff2");
		loginBean3.setPassword("Password2");
		loginDatabase = new LoginDatabase();
	}
	
	@Test
	public void testValidate() throws ClassNotFoundException {
		assertEquals(loginDatabase.validate(loginBean), true);
		assertEquals(loginDatabase.validate(loginBean2), false);
	}
	
	@Test
	public void testPlevel() throws ClassNotFoundException, SQLException {
		String actual = loginDatabase.plevel(loginBean);
		assertEquals("waiter", actual);
		assertEquals("kitchen", loginDatabase.plevel(loginBean3));
	}
	
	@Test
	public void testExceptions() {
		assertThrows(SQLException.class, () -> loginDatabase.plevel(loginBean2));
		assertThrows(ClassNotFoundException.class, () -> Class.forName("test.NonExistentClass"));
	}
}
