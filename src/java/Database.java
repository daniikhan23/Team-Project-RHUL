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
		// Now we're ready to use the DB. You may add your code below this line.
		
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
	
	public static void outputQuery(int number, String sql, Statement statement) throws SQLException {
		switch (number % 10) {
		case 1:
			System.out.println("################## 1st Query ###############");
			break;
		case 2:
			System.out.println("################## 2nd Query ###############");
			break;
		case 3:
			System.out.println("################## 3rd Query ###############");
			break;
		default:
			System.out.println("################## "+ number +"th Query ###############");
			break;
		}
		
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			  String output1 = rs.getString(1);
              String output2 = rs.getString(2);
              
              
              System.out.println(output1 + " " + output2);
		}
		
	}
	// You can write your new methods here.
	
	// ADVANCED: This method is for advanced users only. You should not need to change this!
	
	public static Connection connectToDatabase() {
		System.out.println("------ Testing PostgreSQL JDBC Connection ------");
		Connection connection = null;
		try {
			String user = "postgres";
			String password = "Vietnam1";
			String fullURL = "jdbc:postgresql://localhost/postgresql";
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