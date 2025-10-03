package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDBUtil {
	private static Connection con=null;
	private static Statement stmt=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	
	public static List<Vehicle> getVehicleDetails(){
		
		List<Vehicle> vehicle=new ArrayList<>();
		try {

			con=DBConnection.getconnection();
			stmt=con.createStatement();
			String sql="Select * From vehicle where status='Approved'";
			rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String number=rs.getString(3);
				double rate=rs.getDouble(4);
				String brand=rs.getString(5);
				String type=rs.getString(6);
				String status="";
				int d_id=rs.getInt(8);
				
				
				Vehicle vcl=new Vehicle(id,name,number,rate, brand, type,status, d_id);
				vehicle.add(vcl);	
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vehicle;
	}

	public static boolean insertBooking(Booking booking) {
		
		boolean x=false;
		
		try {
			con=DBConnection.getconnection();
			String sql="insert into booking(name,phone,pickup_loc,drop_loc,distance,cost,date,payment_method,bk_status,vid,cid,dId) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, booking.getName());
			ps.setString(2, booking.getTel());
			ps.setString(3, booking.getPickup_loc());
			ps.setString(4, booking.getDrop_loc());
			ps.setInt(5, booking.getDistance());
			ps.setDouble(6, booking.getCost());
			ps.setString(7, booking.getDate());
			ps.setString(8, booking.getPayment());
			ps.setString(9, booking.getBk_status());
			ps.setInt(10, booking.getVid());
			ps.setInt(11, booking.getCid());
			ps.setInt(12, booking.getVid());
			
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
	public static int getDriverVehicle(int did) {
		int vid=-1;
		try {
			con=DBConnection.getconnection();
			String sql="Select id from vehicle where driverId=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,did);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				vid=rs.getInt(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return vid;
	}
	public static ArrayList<Booking> getAllBooking(int did) {
		
		ArrayList<Booking> booking=new ArrayList<>();
		
		int vid=BookingDBUtil.getDriverVehicle(did);
		try {
			con=DBConnection.getconnection();
			String sql="Select * from booking where vId=? and bk_status='pending'";
			ps=con.prepareStatement(sql);
			ps.setInt(1, vid);
			rs=ps.executeQuery();
			
			
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String number=rs.getString(3);
				String pickup=rs.getString(4);
				String drop=rs.getString(5);
				int distance=rs.getInt(6);
				double amount=rs.getDouble(7);
				String date=rs.getString(8);
				String payment=rs.getString(9);
				String bk_status=rs.getString(10);
				int vid1=rs.getInt(11);
				int cid=rs.getInt(12);
				int bk_did=rs.getInt(13);
				
				booking.add(new Booking(id, name, number, pickup, drop, distance, amount, date, payment, vid1, cid,bk_status,bk_did));
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return booking;
		
	}

	public static boolean acceptBooking(int b_id, int d_id) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="Update booking set dId=?,bk_status='Accepted' where booking_id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, d_id);
			ps.setInt(2, b_id);
			
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

	public static ArrayList<Booking> getAcceptedBookings(int did) {
		ArrayList<Booking> booking=new ArrayList<>();
		
		System.out.println(did);
		try {
			con=DBConnection.getconnection();
			String sql="Select * from booking where dId=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, did);
			rs=ps.executeQuery();
			
			
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String number=rs.getString(3);
				String pickup=rs.getString(4);
				String drop=rs.getString(5);
				int distance=rs.getInt(6);
				double amount=rs.getDouble(7);
				String date=rs.getString(8);
				String payment=rs.getString(9);
				String bk_status=rs.getString(10);
				int vid1=rs.getInt(11);
				int cid=rs.getInt(12);
				int bk_did=rs.getInt(13);
				
				booking.add(new Booking(id, name, number, pickup, drop, distance, amount, date, payment, vid1, cid,bk_status,bk_did));
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return booking;
	}

	public static boolean completedBookings(int b_id, int d_id) {
		
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="Update booking set bk_status='Completed' where booking_id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, b_id);
			
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

	public static boolean cancelBooking(int b_id) {
		boolean x=false;
		try {
			con=DBConnection.getconnection();
			String sql="delete from booking where booking_id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, b_id);
			
			int i=ps.executeUpdate();
			if(i>0) {
				x=true;
				System.out.println(x);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public static ArrayList<CusBooking> getCusAllBooking(int cid) {
		ArrayList<CusBooking> booking=new ArrayList<>();
		
		try {
			con=DBConnection.getconnection();
			String sql="Select * from booking where cId=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, cid);
			rs=ps.executeQuery();
			
			
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String number=rs.getString(3);
				String pickup=rs.getString(4);
				String drop=rs.getString(5);
				int distance=rs.getInt(6);
				double amount=rs.getDouble(7);
				String date=rs.getString(8);
				String payment=rs.getString(9);
				String bk_status=rs.getString(10);
				int vid1=rs.getInt(11);
				int cid1=rs.getInt(12);
				int bk_did=rs.getInt(13);
				
				String sqlveh="Select number,driverId from vehicle where id=?";
				PreparedStatement psveh=con.prepareStatement(sqlveh);
				psveh.setInt(1, vid1);
				ResultSet rsveh=psveh.executeQuery();
				while(rsveh.next()){
					String Vnumber=rsveh.getString(1);
					int dvr_id=rsveh.getInt(2);
					
					
					String sqldri="Select name,phone from driver where id=?";
					PreparedStatement psdri=con.prepareStatement(sqldri);
					psdri.setInt(1, dvr_id);
					ResultSet rsdri=psdri.executeQuery();
					while(rsdri.next()){
						String dName=rsdri.getString(1);
						String dphone=rsdri.getString(2);
						booking.add(new CusBooking(id, name, number, pickup, drop, distance, amount, date, payment, vid1, cid,bk_status,bk_did,Vnumber,dphone,dName));
					}
				}
				
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return booking;
	}

	public static ArrayList<Booking> getCusAllBooking() {
		ArrayList<Booking> booking=new ArrayList<>();
		

		try {
			con=DBConnection.getconnection();
			String sql="Select * from booking";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String number=rs.getString(3);
				String pickup=rs.getString(4);
				String drop=rs.getString(5);
				int distance=rs.getInt(6);
				double amount=rs.getDouble(7);
				String date=rs.getString(8);
				String payment=rs.getString(9);
				String bk_status=rs.getString(10);
				int vid1=rs.getInt(11);
				int cid=rs.getInt(12);
				int bk_did=rs.getInt(13);
				
				booking.add(new Booking(id, name, number, pickup, drop, distance, amount, date, payment, vid1, cid,bk_status,bk_did));
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return booking;
	}
}
