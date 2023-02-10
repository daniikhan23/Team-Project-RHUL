import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Auth {
	public boolean validateLogin(String username, String password, Connection dbConnection) throws SQLException {
		/* check if username in db, if so make sure sha512(password + salt) = password_hash in db */
		Connection db_conn = Database.getConnection();
		PreparedStatement statement = db_conn.prepareStatement("SELECT password_hash, salt FROM Staff WHERE username = ?");
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		
		if (!result.next()) /* not found in db */
			return false;
		
		byte[] pass_hash = result.getBytes(1);
		byte[] salt = result.getBytes(2);
		
		if (hashPassword(password, salt).equals(pass_hash))
			return true;
		
		return false;
	}
	
	public byte[] hashPassword(String password, byte[] salt) {
		byte[] hash = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			hash = md.digest(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hash;
	}
}
