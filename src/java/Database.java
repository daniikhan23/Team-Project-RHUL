package java;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {
	
	// NOTE: You will need to change some variables from START to END.
	public static void main(String[] argv) throws SQLException {


		// END
		
		Connection connection = connectToDatabase();
		if (connection != null) {
			System.out.println("SUCCESS: You made it!"
					+ "\n\t You can now take control of your database!\n");
		} else {
			System.out.println("ERROR: \tFailed to make connection!");
			System.exit(1);
		}
		
	}
	
	
	public static void initialiseTable(String tableName, Statement statement) throws SQLException {
	    try{
	        FileInputStream fis = new FileInputStream(tableName);
	        Scanner sc = new Scanner(fis);
	        String[] arrOfStr = sc.nextLine().split(",");

	        while (sc.hasNextLine()){
	        	
	            statement.executeUpdate(Insert(arrOfStr, tableName));
	            arrOfStr = sc.nextLine().split(",");
	            
	        }
	        statement.executeUpdate(Insert(arrOfStr, tableName));
	        
	        sc.close();
	    }
	    catch(IOException e) {
	      e.printStackTrace();
	    }
	}
	
	
	public static String Insert(String[] arr, String tableName) {
		String temp = "";
		String sql = "";
		for (String a: arr) {
			if (IsInt(a) ==true) {
				temp += a+",";
			}
			else {
				temp += "'"+a+"',";
			}
		}
		temp = temp.substring(0, temp.length()-1);
		sql = "INSERT INTO "+tableName+ " VALUES("+temp+");";
		return sql;
	}
	
	public static boolean IsInt(String str){
		try {
			Integer.parseInt(str);
		    return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	
	public static Connection connectToDatabase() {
		System.out.println("------ Testing PostgreSQL JDBC Connection ------");
		Connection connection = null;
		try {
			String user = "zkac263";
			String password = "ooquie";
			String fullURL = "jdbc:postgresql://localhost/CS2855/zkac263";
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


}