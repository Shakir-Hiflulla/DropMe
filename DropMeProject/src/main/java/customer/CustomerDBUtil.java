package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDBUtil {
	
	private static Connection con=null;
	private static Statement stmt=null;
	private static ResultSet rs=null;
	private static PreparedStatement ps=null;
	
	public static boolean validate(String email,String password) {
		
		boolean login=false;
		try {
			
			con=DBConnection.getconnection();
			stmt=con.createStatement();
			String sql="Select * From user where email='"+email+"' and password ='"+password+"'";
			rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				login=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return login;
	}
	
	public static List<User> getUser(String email,String password){
		
		
		ArrayList <User> usr=new ArrayList <>();
		try {
			
			con=DBConnection.getconnection();
			stmt=con.createStatement();
			String sql="Select * From user where email='"+email+"' and password ='"+password+"'";
			rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String emailu=rs.getString(4);
				String phone=rs.getString(3);
				String uPass=rs.getString(5);
				
				User user=new User(id,name,emailu,phone,uPass);
				usr.add(user);
				
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usr;
		
	}
	
	public static boolean insertCustomer(String name,String phone, String email,String pass) {
		
		boolean success=false;
		
		try {
			con=DBConnection.getconnection();
			stmt=con.createStatement();
			String sql="insert into user values(0,'"+name+"','"+phone+"','"+email+"','"+pass+"')";
			int rs=stmt.executeUpdate(sql);
			if(rs>0) {
				success=true;
			}else {
				success=false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}

	public static ArrayList<User> getCustomerList() {
		ArrayList<User> user=new ArrayList<>();
		try {

			con=DBConnection.getconnection();
			String sql="Select * From user";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String email=rs.getString(3);
				String phone=rs.getString(4);
				String pass=rs.getString(5);
				
				user.add(new User(id,name,email,phone,pass));
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public static boolean deleteCustomer(int uId) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="delete from user where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, uId);
			
			int i=ps.executeUpdate();
			if(i>1) {
				x=true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return x;
		
	}

	public static User getCustomer(int id) {
		User user=null;
		try {
			con=DBConnection.getconnection();
			String sql="Select * From user where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			while(rs.next()){
				int uid=rs.getInt(1);
				String name=rs.getString(2);
				String phone=rs.getString(3);
				String email=rs.getString(4);
				String pass=rs.getString(5);
				
				user=new User(uid,name,email,phone,pass);
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	

	public static boolean editUser(User u) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="Update user set name=?,phone=?,email=?,password=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getPhone());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPassword());
			ps.setInt(5, u.getId());
			
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

}
