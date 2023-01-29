import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

public class Auth {
	public boolean validateLogin(String username, String password, Connection dbConnection) {
		/* check if username in db, if so make sure sha512(password + salt) = password_hash in db */
		return false;
	}
}
