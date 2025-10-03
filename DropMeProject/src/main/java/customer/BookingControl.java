package customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class BookingControl
 */

public class BookingControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String act=request.getParameter("action");
		System.out.println(act);
		switch (act) {
		case "login":
			getBookingDetails(request,response);
			break;
		case "accepted":
			getAcceptedBooking(request,response);
			break;
		case "cBooking":
			getCusBookingDetails(request,response);
			break;
		case "getVeh":
			getCVehicleDetails(request,response);
			break;
		case "getBks":
			getAdminBookingDetails(request,response);
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + act);
		}
    }
	
	
	private void getCVehicleDetails(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Vehicle> vehicle=VehicleDBUtil.getvehicleDetails();
		
		try {
			request.setAttribute("vcl", vehicle);
			RequestDispatcher dis=request.getRequestDispatcher("VehicleCharges.jsp");
			dis.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String act=request.getParameter("action");
		System.out.println(act);
		switch (act) {
		case "book": 
			insertBooking(request,response);
			break;
		case "login":
			getBookingDetails(request,response);
			break;
		case "accept":
			acceptBooking(request,response);
			break;
		case "done":
			completedBooking(request,response);
			break;
		case "cancel":
			cancelBooking(request,response);
			break;
		case "cancelBooking":
			cancelBooking(request,response);
			break;
		
		
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + act);
		}
	}

	
	
	private void cancelBooking(HttpServletRequest request, HttpServletResponse response) {
		int b_id=Integer.parseInt(request.getParameter("b_id"));
		HttpSession session=request.getSession(false);
		String role=(String)session.getAttribute("role");
		Integer id = null;
		int userId = 0;

		try{
			switch (role.toLowerCase()) {
	        case "driver":
	            id = (Integer) session.getAttribute("DID");
	            userId=id;
				boolean cancel=BookingDBUtil.cancelBooking(b_id);
				if(cancel==true) {
					ArrayList<Booking> book=BookingDBUtil.getAcceptedBookings(id);
					request.setAttribute("bookings", book);
					RequestDispatcher dis= request.getRequestDispatcher("AcceptedBookings.jsp");
					dis.forward(request, response);
				}
	            break;
	        case "customer":
	            id = (Integer) session.getAttribute("UID");
	            userId=id;
	            boolean cancel1=BookingDBUtil.cancelBooking(b_id);
				if(cancel1==true) {
					ArrayList<CusBooking> book=BookingDBUtil.getCusAllBooking(id);
					request.setAttribute("bookings", book);
					RequestDispatcher dis= request.getRequestDispatcher("CustomerBooking.jsp");
					dis.forward(request, response);
				}
	            break;
	        case "admin":
	            id = (Integer) session.getAttribute("AID");
	            userId=id;
	            break;
	        default:
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void completedBooking(HttpServletRequest request, HttpServletResponse response) {
		int b_id=Integer.parseInt(request.getParameter("b_id"));
		HttpSession session=request.getSession(false);
		try{
			int d_id=(int)session.getAttribute("DID");
			boolean accept=BookingDBUtil.completedBookings(b_id,d_id);
			
			if(accept==true) {
				
				ArrayList<Booking> book=BookingDBUtil.getAcceptedBookings(d_id);
				try {
				request.setAttribute("bookings", book);
				RequestDispatcher dis= request.getRequestDispatcher("AcceptedBookings.jsp");
				dis.forward(request, response);
				}catch(Exception e) {
					
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void acceptBooking(HttpServletRequest request, HttpServletResponse response) {
		int b_id=Integer.parseInt(request.getParameter("b_id"));
		HttpSession session=request.getSession(false);
		try{
			int d_id=(int)session.getAttribute("DID");
			boolean accept=BookingDBUtil.acceptBooking(b_id,d_id);
			
			if(accept==true) {
				ArrayList<Booking> book=BookingDBUtil.getAllBooking(d_id);
				request.setAttribute("bookings", book);
				RequestDispatcher dis= request.getRequestDispatcher("DriverBookings.jsp");
				dis.forward(request, response);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void insertBooking(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession(false);
		
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String pickup_loc=request.getParameter("pickup");
		String drop_loc=request.getParameter("drop");
		String distanceStr=request.getParameter("distance");
		String costStr=request.getParameter("total");
		String date=request.getParameter("date");
		String payment=request.getParameter("payment");
		String vidStr=request.getParameter("vid");
		int cid=(int)session.getAttribute("UID");
		
		int vid=0;
		int distance=0;
		double cost=0;
		
		try {
			
			distance=Integer.parseInt(distanceStr);
			cost=Double.parseDouble(costStr);
			vid=Integer.parseInt(vidStr);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		int dId=VehicleDBUtil.getDriverId(vid);
		System.out.println(vid+"  "+dId);
		
		Booking booking=new Booking(0,name,tel,pickup_loc,drop_loc,distance,cost,date,payment,vid,cid,"pending",dId);

		
		if(payment.equals("card")) {
			try {
				request.setAttribute("booked", booking);
				RequestDispatcher dis= request.getRequestDispatcher("paymentform.jsp");
				dis.forward(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			

		}else if(payment.equals("settlement")) {

			
			try {
				boolean success=BookingDBUtil.insertBooking(booking);
				ArrayList<Vehicle> v=VehicleDBUtil.getvehicleDetails();
				
					request.setAttribute("veh", v);
					RequestDispatcher dis= request.getRequestDispatcher("booking.jsp");
					dis.forward(request, response);
					
				
			} catch (ServletException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
		
		
	}
	
	public void getBookingDetails(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("entered");
		int did=Integer.parseInt(request.getParameter("did"));
		
		System.out.println(did+"hiii");
		
		ArrayList<Booking> book=BookingDBUtil.getAllBooking(did);
		try {
		request.setAttribute("bookings", book);
		RequestDispatcher dis= request.getRequestDispatcher("DriverBookings.jsp");
		dis.forward(request, response);
		}
		catch(Exception e) {
			
		}
		
	}
	public void getCusBookingDetails(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("entered");
		int cid=Integer.parseInt(request.getParameter("did"));
		
		System.out.println(cid+"hiiiiiiiiii");
		
		ArrayList<CusBooking> book=BookingDBUtil.getCusAllBooking(cid);
		try {
		request.setAttribute("bookings", book);
		RequestDispatcher dis= request.getRequestDispatcher("CustomerBooking.jsp");
		dis.forward(request, response);
		}
		catch(Exception e) {
			
		}
		
	}
	private void getAcceptedBooking(HttpServletRequest request, HttpServletResponse response) {
		
		int did=Integer.parseInt(request.getParameter("did"));
		System.out.println(did+"hiii");
		
		ArrayList<Booking> book=BookingDBUtil.getAcceptedBookings(did);
		try {
		request.setAttribute("bookings", book);
		RequestDispatcher dis= request.getRequestDispatcher("AcceptedBookings.jsp");
		dis.forward(request, response);
		}
		catch(Exception e) {
			
		}
	}
	private void getAdminBookingDetails(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("booking get");
		ArrayList<Booking> book=BookingDBUtil.getCusAllBooking();
		try {
		request.setAttribute("book", book);
		RequestDispatcher dis= request.getRequestDispatcher("AdminBooking.jsp");
		dis.forward(request, response);
		}
		catch(Exception e) {
			
		}
		
	}

}
