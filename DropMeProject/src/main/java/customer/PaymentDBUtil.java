package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PaymentDBUtil {
	private static Connection con=null;
	private static Statement stmt=null;
	private static ResultSet rs=null;

	public static boolean insrtPayment(Payment pay) {
		
		boolean x=false;
		
		try {
			con=DBConnection.getconnection();
			String sql="insert into payment(card_number,amount,p_date,description,cid) values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setString(1, pay.getcNumber());
			ps.setDouble(2, pay.getAmount());
			ps.setString(3, pay.getPayment_date());
			ps.setString(4, pay.getPayment_discription());
			ps.setInt(5, pay.getCid());
			
			int i=ps.executeUpdate();
			if(i>0) {
				x=true;
				System.out.println(ps);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return x;
	}

}
