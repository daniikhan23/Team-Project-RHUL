package Login.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Login.Bean.LoginBean;


public class LoginDatabase {
	
	public static Connection connectToDatabase() {
		System.out.println("------ Testing PostgreSQL JDBC Connection ------");
		String user = "postgres"; //for offline postres
		String password = "Vietnam1";  //for offline postres

		//String user = "group35";
		//String password = "eibahv";
		String database = "localhost";
		Connection connection = null;
		try {
			String protocol = "jdbc:postgresql://";
			String dbName = "/postgres";    //offline postres
			//String dbName = "CS2810%2fgroup35";
			String fullURL = protocol + database + dbName;
			connection = DriverManager.getConnection(fullURL, user, password);
			return connection;
		} catch (SQLException e) {
			String errorMsg = e.getMessage();
			if (errorMsg.contains("authentication failed")) {
				System.out.println("ERROR: \tDatabase password is incorrect. Have you changed the password string above?");
				System.out.println("\n\tMake sure you are NOT using your university password.\n"
						+ "\tYou need to use the password that was emailed to you!");
			} else {
				System.out.println("Connection failed! Check output console.");
				e.printStackTrace();
			}
		}
		return null;
	}

	
    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;
        try {
        		Statement statement = connectToDatabase().createStatement();
        		String Username = loginBean.getUsername();
        		String Password = loginBean.getPassword();
        		String loginsql = "SELECT * "+
        		"FROM StaffTable "+
        		"WHERE username == "+ Username+
        		" AND password == "+ Password +";";
        		ResultSet rs = statement.executeQuery(loginsql);
        		status = rs.next();
        } 
    catch(SQLException e) {
    	printSQLException(e);
        }
        return status;
    }
    
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
