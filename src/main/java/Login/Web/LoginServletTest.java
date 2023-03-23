package Login.Web;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LoginServletTest {
	
	private LoginServlet loginServlet;
	HttpServletRequest request;
	HttpServletResponse response;

	@BeforeEach
	public void setup() {
		loginServlet = new LoginServlet();
	}
	
	@Test
	public void testInit() {
		loginServlet.init();
		assertNotNull(loginServlet.LoginDatabase);
	}
}
