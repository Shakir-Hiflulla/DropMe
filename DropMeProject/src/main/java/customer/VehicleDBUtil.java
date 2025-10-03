package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehicleDBUtil {

	private static Connection con=null;
	private static Statement stmt=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	
	public static int getDriverId(int vid) {
		int did=0;
		System.out.println(vid+"vanthuu");
		try {
			con=DBConnection.getconnection();
			String sql="select driverId from vehicle where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, vid);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				did=rs.getInt(1);
			}
			System.out.println(did);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return did;
	}

	public static ArrayList<Vehicle> getvehicleDetails() {
		
		ArrayList<Vehicle> v=new ArrayList<>();
		try {
			con=DBConnection.getconnection();
			String sql="Select* from vehicle";
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String number=rs.getString(3);
				double rate=rs.getDouble(4);
				String brand=rs.getNString(5);
				String type=rs.getString(6);
				String status=rs.getString(7);
				int dId=rs.getInt(8);
				
				v.add(new Vehicle(id,name,number,rate,brand,type,status,dId));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	public static boolean approveVehicle(int vid) {
		
		boolean x=false;
		con=DBConnection.getconnection();
		String sql="Update vehicle set status='Approved' where id=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, vid);
			
			int i=ps.executeUpdate();
			if(i>0) {
				x=true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return x;
	}

	public static boolean setRateVeh(int vid, double rate) {
		boolean x=false;
		con=DBConnection.getconnection();
		String sql="Update vehicle set rate=? where id=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setDouble(1, rate);
			ps.setInt(2, vid);
			
			int i=ps.executeUpdate();
			if(i>0) {
				x=true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return x;
	}

	public static Vehicle getvehicleDetails(int id) {
		Vehicle v=null;
		try {
			con=DBConnection.getconnection();
			String sql="Select* from vehicle where driverId=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				int vid=rs.getInt(1);
				String name=rs.getString(2);
				String number=rs.getString(3);
				double rate=rs.getDouble(4);
				String brand=rs.getNString(5);
				String type=rs.getString(6);
				String status=rs.getString(7);
				int dId=rs.getInt(8);
				v=new Vehicle(vid,name,number,rate,brand,type,status,dId);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	public static boolean updateVehicle(Vehicle v) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="Update vehicle set name=?,number=?,rate=?,barand=?,type=?,status=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, v.getName());
			ps.setString(2, v.getNumber());
			ps.setDouble(3, v.getRate());
			ps.setString(4, v.getBrand());
			ps.setString(5, v.getType());
			ps.setString(6, v.getStatus());
			ps.setInt(7, v.getId());
			
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
