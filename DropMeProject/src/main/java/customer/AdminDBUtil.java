package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDBUtil {
	private static Connection con=null;
	private static Statement stmt=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;

	public static boolean validate(String email, String pass) {
		boolean x=false;
		try {
			
			con=DBConnection.getconnection();
			String sql="Select * From admin where email=? and password =?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				x=true;
				System.out.println("valid Admin");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return x;
	}

	public static List<Admin> getUser(String email, String pass) {
		ArrayList <Admin> usr=new ArrayList <>();
		try {
			
			con=DBConnection.getconnection();
			String sql="Select * From admin where email=? and password =?";
			ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pass);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String emailu=rs.getString(4);
				String phone=rs.getString(3);
				String uPass=rs.getString(5);
				String status=rs.getString(6);
				
				Admin user=new Admin(id,name,emailu,phone,uPass,status);
				usr.add(user);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usr;
	}
	
	
	public static boolean editAdmin(Admin a) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="Update admin set name=?,phone=?,email=?,password=?,status=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, a.getName());
			ps.setString(2, a.getPhone());
			ps.setString(3, a.getEmail());
			ps.setString(4, a.getPassword());
			ps.setString(5, a.getStatus());
			ps.setInt(6, a.getId());
			
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
	
	public static Admin getAdmin(int id) {
		Admin admin=null;
		try {
			con=DBConnection.getconnection();
			String sql="Select * From admin where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			while(rs.next()){
				int uid=rs.getInt(1);
				String name=rs.getString(2);
				String phone=rs.getString(3);
				String email=rs.getString(4);
				String pass=rs.getString(5);
				String status=rs.getString(5);
				
				admin=new Admin(uid,name,email,phone,pass,status);
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

}
