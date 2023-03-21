package Signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;

public class SignupDB {

    public int registerEmployee(SignupBean employee) throws Exception {
    	String INSERT_USERS_SQL = "INSERT INTO StaffTable VALUES (?, ?, ?, ?)";

        int result = 0;
        
        try (Connection connection = Database.connectToDatabase();
        	// Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
        	
        	int newID = getprimarykey();
        	preparedStatement.setInt(1, newID);
        	preparedStatement.setString(2, employee.getUsername());
//            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getPassword());
            preparedStatement.setString(4, "admin");

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            System.out.println("Result: " + result);

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        	//throw new Exception(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.out.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    public int getprimarykey() throws SQLException, ClassNotFoundException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		
		String sql = "SELECT staffid FROM StaffTable ORDER BY staffid ASC;";
		int num = 1;
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			System.out.println(num);
			if (rs.getInt(1) == num) {
				num ++;
			}
		}
		return num;
	}

}