

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {
	
	// NOTE: You will need to change some variables from START to END.
	public static void main(String[] argv) throws SQLException {

		Connection connection = connectToDatabase();
		if (connection != null) {
			System.out.println("SUCCESS: You made it!"
					+ "\n\t You can now take control of your database!\n");
		} else {
			System.out.println("ERROR: \tFailed to make connection!");
			System.exit(1);
		}
		// Now we're ready to use the DB. You may add your code below this line.
		
		Statement statement = connection.createStatement();

		st.execute("DROP TABLE IF EXIST Menu;");

		String MenuTable = """
				CREATE TABLE Menu(
					ItemCode INTEGER NOT NULL,
					Name VARCHAR(255) NOT NULL,
					Cost INTEGER NOT NULL,
					Category VARCHAR(255) NOT NULL,
					PRIMARY KEY (ItemCode);
				)
				""";


		String StaffTable = """
				CREATE TABLE Staff(
					StaffID INTEGER NOT NULL,
					username VARCHAR(255) NOT NULL,
					password VARCHAR(255) NOT NULL,
					PRIMARY KEY (StaffID);
				)
				""";
		statement.executeUpdate(MenuTable);
		statement.executeUpdate(StaffTable);

		
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
		//String user = "postgres"; for offline postres
		//String password = "Vietnam1";  for offline postres

		String user = "group35";
		String password = "eibahv";
		String database = "localhost";
		Connection connection = null;
		try {
			String protocol = "jdbc:postgresql://";
			//String dbName = "/postgres";    offline postres
			String dbName = "CS2810%2fgroup35";
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

}