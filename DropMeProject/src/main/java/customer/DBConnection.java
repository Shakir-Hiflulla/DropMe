package customer;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	private static String url="jdbc:mysql://localhost:3306/dropme";
	private static String user="root";
	private static String password="12345";
	private static Connection con;
	
	public static Connection getconnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection(url, user, password);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Connection failed");
		}
		return con;
	}

}
