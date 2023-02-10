

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {
	static Connection connection = null;
	
	public static void main(String[] argv) throws SQLException {
		connection = connectToDatabase();
		if (connection != null) {
			System.out.println(
					"SUCCESS: Connected to database successfully.");
		} else {
			System.out.println("ERROR: \tFailed to make connection!");
			System.exit(1);
		}

		initialiseTables();
	}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = connectToDatabase();
			initialiseTables();
		}
		
		return connection;
	}

	public static void initialiseTables() throws SQLException {
		Statement statement = connection.createStatement();

		statement.execute("DROP TABLE IF EXISTS Menu;");
		statement.execute("DROP TABLE IF EXISTS Staff;");

		String MenuTable = """
				CREATE TABLE Menu (
					ItemCode INTEGER NOT NULL,
					Name VARCHAR(255) NOT NULL,
					Cost INTEGER NOT NULL,
					Category VARCHAR(255) NOT NULL,
					PRIMARY KEY (ItemCode)
				);
				""";


		String StaffTable = """
				CREATE TABLE Staff (
					StaffID INTEGER NOT NULL,
					username VARCHAR(255) NOT NULL,
					password_hash BINARY(64) NOT NULL,
					salt BINARY(32) NOT NULL,
					PRIMARY KEY (StaffID)
				);
				""";
		statement.executeUpdate(MenuTable);
		statement.executeUpdate(StaffTable);
	}

	public static void initialiseTableFromFile(String tableName, Statement statement)
			throws SQLException {
		try {
			FileInputStream fis = new FileInputStream(tableName);
			Scanner sc = new Scanner(fis);
			String[] arrOfStr = sc.nextLine().split(",");

			while (sc.hasNextLine()) {

				statement.executeUpdate(Insert(arrOfStr, tableName));
				arrOfStr = sc.nextLine().split(",");

			}
			statement.executeUpdate(Insert(arrOfStr, tableName));

			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static String Insert(String[] arr, String tableName) {
		String temp = "";
		String sql = "";
		for (String a : arr) {
			if (IsInt(a) == true) {
				temp += a + ",";
			} else {
				temp += "'" + a + "',";
			}
		}
		temp = temp.substring(0, temp.length() - 1);
		sql = "INSERT INTO " + tableName + " VALUES(" + temp + ");";
		return sql;
	}

	public static boolean IsInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


	public static Connection connectToDatabase() {
		System.out.println("------ Testing PostgreSQL JDBC Connection ------");
<<<<<<< HEAD
		// String user = "postgres"; for offline postgres
		// String password = "Vietnam1"; for offline postgres
=======
		String user = "postgres"; //for offline postres
		String password = "ooquie";  //for offline postres
>>>>>>> main

		//String user = "group35";
		//String password = "eibahv";
		String database = "localhost";
		Connection connection = null;
		try {
			String protocol = "jdbc:postgresql://";
<<<<<<< HEAD
			// String dbName = "/postgres"; offline postgres
			String dbName = "/CS2810/group35";
=======
			String dbName = "/postgres";    //offline postres
			//String dbName = "CS2810%2fgroup35";
>>>>>>> main
			String fullURL = protocol + database + dbName;
			connection = DriverManager.getConnection(fullURL, user, password);
			return connection;
		} catch (SQLException e) {
			String errorMsg = e.getMessage();
			if (errorMsg.contains("authentication failed")) {
				System.out.println(
						"ERROR: \tDatabase password is incorrect.");
			} else {
				System.out.println("Connection failed! Check output console.");
				e.printStackTrace();
			}
		}
		return null;
	}

}
