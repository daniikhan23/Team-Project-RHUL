package Login.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;
import Login.Bean.LoginBean;


public class LoginDatabase {
	/*
     * validates all the login is correct and assigns the user
     * to the specified page
     * 
     * @return boolean true or false if valid
     */

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;
        try {
    			Connection connection = Database.connectToDatabase();
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
    /*
     * the priority level of the login.
     * 
     * @return the string of the priority level.
     */
    public String plevel(LoginBean loginBean) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		String Username = loginBean.getUsername();
		String Password = loginBean.getPassword();
		String loginsql = "SELECT level "+
		"FROM StaffTable "+
		"WHERE username = '"+ Username+
		"' AND password = '"+ Password +"';";
		ResultSet rs = statement.executeQuery(loginsql);
		rs.next();
		String level = rs.getString(1);
		return level;
    }
    
    /*
     * exception error if the username or password incorrect
     */
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
