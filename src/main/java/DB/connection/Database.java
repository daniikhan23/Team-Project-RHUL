package DB.connection;



import java.io.*;
import java.sql.*;
import java.util.*;
/*
 * Used to connect and interact with the DB
 */
public class Database {
	/*
	 * Initialises the DB with all valid tables and inforrmation
	 */
	// NOTE: You will need to change some variables from START to END.
	public static void main(String[] argv) throws SQLException, ClassNotFoundException {

		Connection connection = connectToDatabase();
		if (connection != null) {
			System.out.println("Reinitialising Database.");
		} else {
			System.out.println("ERROR: \tFailed to make connection!");
			System.exit(1);
		}
		// Now we're ready to use the DB. You may add your code below this line.
		
		Statement statement = connection.createStatement();
		
		System.out.println("Creating relevant tables.");

		statement.execute("DROP TABLE IF EXISTS MenuTable;");
		statement.execute("DROP TABLE IF EXISTS StaffTable;");
		statement.execute("DROP TABLE IF EXISTS OrderTable;");
		statement.execute("DROP TABLE IF EXISTS CurrentOrderTable;");
		
		String MenuTable = """
				CREATE TABLE MenuTable(
					ItemCode INTEGER NOT NULL,
					Name VARCHAR(255) NOT NULL,
					Cost INTEGER NOT NULL,
					Category VARCHAR(255) NOT NULL,
					Stock INTEGER NOT NULL,
					PRIMARY KEY (ItemCode)
				);
				""";


		String StaffTable = """
				CREATE TABLE StaffTable(
					StaffID INTEGER NOT NULL,
					username VARCHAR(255) NOT NULL,
					password VARCHAR(255) NOT NULL,
					level VARCHAR(255) NOT NULL,
					PRIMARY KEY (StaffID)
				);
				""";
		
		String OrderTable = """
				CREATE TABLE OrderTable(
					OrderID INTEGER NOT NULL,
					orderItem VARCHAR(256) NOT NULL,
					TableNo INTEGER NOT NULL,
					CompletePhase INTEGER NOT NULL,
					timeStarted TIMESTAMP NOT NULL,
					OrderNO INTEGER NOT NULL,
					PRIMARY KEY (OrderID)
				);
				""";
		
		String CurrentOrderTable = """
				CREATE TABLE CurrentOrderTable(
					OrderID INTEGER NOT NULL,
					orderItem VARCHAR(256) NOT NULL,
					TableNo INTEGER NOT NULL,
					CompletePhase INTEGER NOT NULL,
					timeStarted TIMESTAMP NOT NULL,
					PRIMARY KEY (OrderID)
				);
				""";
		
		
		statement.executeUpdate(MenuTable);
		statement.executeUpdate(StaffTable);
		statement.executeUpdate(OrderTable);
		statement.execute(CurrentOrderTable);
		
		initialiseTable("MenuTable", statement);
		initialiseTable("StaffTable", statement);
		
		
	}
	/*
	 * initalises the tables with the .txt files 
	 */
	
	public static void initialiseTable(String tableName, Statement statement) throws SQLException {
	    try{
	        FileInputStream fis = new FileInputStream("src/main/java/DB/connection/"+tableName+".txt");
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
	
	/*
	 * 
	 * Used to insert items into the table
	 */
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
	/*
	 * establishes a connection to the DB
	 * @return  Connection
	 */
	
	public static Connection connectToDatabase() throws ClassNotFoundException {
		System.out.println("------ Testing PostgreSQL JDBC Connection ------");
		String user = "postgres"; //for offline postres
		String password = "ooquie";  //for offline postres

		//String user = "group35";
		//String password = "eibahv";
		String database = "localhost";
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String protocol = "jdbc:postgresql://";
			String dbName = "/postgres";    //offline postres
			//String dbName = "/CS2810/group35";
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
