package Login.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Login.Bean.LoginBean;


public class LoginDatabase {
	

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;
        try {
        		Class.forName("org.postgresql.Driver");
    			String user = "postgres";// for offline postres
    			String password = "ooquie"; // for offline postres

    			//String user = "group35";
    			//String password = "eibahv";
    			String database = "localhost";
    			Connection connection = null;
    			String protocol = "jdbc:postgresql://";
    			String dbName = "/postgres";    //offline postres
    			//String dbName = "CS2810%2fgroup35";
    			String fullURL = protocol + database + dbName;
    			connection = DriverManager.getConnection(fullURL, user, password);
    			System.out.println("connecte");
        		Statement statement = connection.createStatement();
        		String Username = loginBean.getUsername();
        		String Password = loginBean.getPassword();
        		String loginsql = "SELECT * "+
        		"FROM StaffTable "+
        		"WHERE username = '"+ Username+
        		"' AND password = '"+ Password +"';";
        		System.out.println(loginsql);
        		ResultSet rs = statement.executeQuery(loginsql);
        		status = rs.next();
        		System.out.println(status);
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
