package Order.Waiter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.connection.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class Messages extends HttpServlet{

	private static final long serialVersionUID = 4940068322743158735L;



	//DO POST WILL SET IT
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {


		if (request.getParameter("Message") != null) {
			try {
				String Message = request.getParameter("Message");
				System.out.println("Sending message to Kitchen");
				System.out.println(Message);
				inputMessage(Message);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		response.sendRedirect("waiterPage.jsp");
	}



	public static String getMessage() throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();

		String SQL = "SELECT message from MessagingTable WHERE MessageNO = 2;";
		ResultSet rs = statement.executeQuery(SQL);
		rs.next();
		return rs.getString(1);
	}

	public void inputMessage(String message) throws ClassNotFoundException, SQLException {
		Connection connection = Database.connectToDatabase();
		Statement statement = connection.createStatement();
		statement.execute("UPDATE MessagingTable SET Message = '"+message+"' WHERE MessageNO = 1;");
	}
}
