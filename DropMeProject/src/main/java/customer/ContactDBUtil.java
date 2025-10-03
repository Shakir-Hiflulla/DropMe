package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContactDBUtil {

	private static Connection con=null;
	private static Statement stmt=null;
	private static ResultSet rs=null;
	private static PreparedStatement ps=null;
	
	public static boolean insetFaq(FAQ f) {
		boolean x=false;
		try {
			
			con=DBConnection.getconnection();
			String sql="Insert into faq values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, f.getId() );
			ps.setString(2, f.getQuestion());
			ps.setString(3, f.getReply());
			ps.setString(4, f.getEmail());
			ps.setString(5, f.getpStatus());
			
			int i=ps.executeUpdate();
			
			if(i>0) {
				x=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return x;
	}

	public static ArrayList<FAQ> getFaq() {
		ArrayList<FAQ> faq=new ArrayList<>();
		

		try {
			con=DBConnection.getconnection();
			String sql="Select * from faq";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			
			while(rs.next()){
				int id=rs.getInt(1);
				String question=rs.getString(2);
				String answer=rs.getString(3);
				String email=rs.getString(4);
				String status=rs.getString(5);
				
				faq.add(new FAQ(id,question,answer,email,status));
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return faq;
	}

	public static boolean publishFaq(int id, String sts) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="Update faq set status=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, sts);
			ps.setInt(2, id);
			
			int i=ps.executeUpdate();
			if(i>0) {
				x=true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public static boolean replyFaq(int id, String ans) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="Update faq set answer=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, ans);
			ps.setInt(2, id);
			
			int i=ps.executeUpdate();
			if(i>0) {
				x=true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public static ArrayList<FAQ> getuserFaq() {
ArrayList<FAQ> faq=new ArrayList<>();
		

		try {
			con=DBConnection.getconnection();
			String sql="Select * from faq where status=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, "Published");
			rs=ps.executeQuery();
			
			
			while(rs.next()){
				int id=rs.getInt(1);
				String question=rs.getString(2);
				String answer=rs.getString(3);
				String email=rs.getString(4);
				String status=rs.getString(5);
				
				faq.add(new FAQ(id,question,answer,email,status));
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return faq;
	}

}
