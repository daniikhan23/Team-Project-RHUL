package Login.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        		String username = loginBean.getUsername();
        		String password = loginBean.getPassword();
        		String loginsql = "SELECT * "+
        		"FROM StaffTable "+
        		"WHERE username = ? "+
        		"AND password = ?;";
        		PreparedStatement ps = connection.prepareStatement(loginsql);
        		ps.setString(1, username);
        		ps.setString(2, password);
        		System.out.println(ps);
        		ResultSet rs = ps.executeQuery();
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
		String username = loginBean.getUsername();
		String loginsql = "SELECT level "+
        "FROM StaffTable "+
        "WHERE username = ?;";
        PreparedStatement ps = connection.prepareStatement(loginsql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
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
