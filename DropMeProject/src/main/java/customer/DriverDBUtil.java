package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DriverDBUtil {
	
	private static Connection con=null;
	private static Statement stmt=null;
	private static ResultSet rs=null;
	private static PreparedStatement ps=null;
	
	
	public static boolean inserDriver(Driver d) {
		boolean x=false;
		Vehicle v=d.getVeh();
		
		try {
			con=DBConnection.getconnection();
			con.setAutoCommit(false);
			
			String sql1="insert into driver(name,phone,licenceNo,email,password) values(?,?,?,?,?)";
			PreparedStatement driver=con.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
			driver.setString(1, d.getName());
			driver.setString(2, d.getPhone());
			driver.setString(3, d.getLicenseNum());
			driver.setString(4, d.getEmail());
			driver.setString(5, d.getPassword());
			int i=driver.executeUpdate();
			
			if (i == 0) {
                throw new SQLException("Failed to insert Driver, no rows affected.");
            }
			
			rs=driver.getGeneratedKeys();
			
			if (rs.next()) {
                int dId = rs.getInt(1);
             
    			String sql="insert into vehicle(name,number,barand,type,status,driverId) values(?,?,?,?,?,?)";
    			PreparedStatement vehicle=con.prepareStatement(sql);
    			vehicle.setString(1, v.getName());
    			vehicle.setString(2, v.getNumber());
    			vehicle.setString(3, v.getBrand());
    			vehicle.setString(4, v.getType());
    			vehicle.setString(5, v.getStatus());
    			vehicle.setInt(6, dId);
    			
    			int j = vehicle.executeUpdate();

               
                if (j == 0) {
                    throw new SQLException("Failed to insert driver, no rows affected.");
                }

                
                con.commit();
                System.out.println("Both vehicle and driver were inserted successfully.");
                x=true;
                
			} else {
                throw new SQLException("Failed to retrieve generated vehicle ID.");
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return x;
	}


	public static boolean validate(String email, String pass) {
		boolean x=false;
		try {
			
			con=DBConnection.getconnection();
			String sql="Select * From driver where email=? and password =?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				x=true;
				System.out.println("valid driver");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return x;
	}


	public static List<Driver> getUser(String email, String pass) {
		ArrayList <Driver> drvr=new ArrayList <>();
		try {
			
			con=DBConnection.getconnection();
			String sql="Select * From driver where email=? and password =?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			
			
			if(rs.next()) {
				
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String emailu=rs.getString(5);
				String phone=rs.getString(3);
				String licenseNum=rs.getString(4);
				String uPass=rs.getString(6);
				
				Driver dvr=new Driver(id,name,emailu,phone,uPass,licenseNum);
				drvr.add(dvr);
			}
			System.out.println("nvnmvbmvbnv");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return drvr;
	}


	public static ArrayList<Driver> getDriverDetails() {
		ArrayList<Driver> d=new ArrayList<>();
		
		try {
			con=DBConnection.getconnection();
			String sql="select * from driver";
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String phone=rs.getString(3);
				String lnum=rs.getString(4);
				String email=rs.getString(5);
				String pass=rs.getString(6);
				
				d.add(new Driver(id,name,email,phone,pass,lnum));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return d;
	}


	public static boolean deleteDriver(int did) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="delete from driver where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, did);
			
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


	public static Driver getDriver(int id) {
		Driver driver=null;
		try {
			con=DBConnection.getconnection();
			String sql="Select * From Driver where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			while(rs.next()){
				int uid=rs.getInt(1);
				String name=rs.getString(2);
				String phone=rs.getString(3);
				String licence=rs.getString(4);
				String email=rs.getString(5);
				String pass=rs.getString(6);
				
				driver=new Driver(uid,name,phone,licence,email,pass);
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return driver;
	}


	public static boolean editDriver(Driver d) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="Update driver set name=?,phone=?,licenceNo=?,email=?,password=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, d.getName());
			ps.setString(2, d.getPhone());
			ps.setString(3, d.getLicenseNum());
			ps.setString(4, d.getEmail());
			ps.setString(5, d.getPassword());
			ps.setInt(6, d.getId());
			
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
