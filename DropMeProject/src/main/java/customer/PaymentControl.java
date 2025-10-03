package customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Servlet implementation class PaymentControl
 */
public class PaymentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act=request.getParameter("action");
		switch (act) {
		case "pay": 
			insertPayment(request,response);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + act);
		}
	}

	private void insertPayment(HttpServletRequest request, HttpServletResponse response) {
		HttpSession ses=request.getSession(false);
		
		String Bname=request.getParameter("name");
		String tel=request.getParameter("tel");
		String pickup_loc=request.getParameter("pickup_loc");
		String drop_loc=request.getParameter("drop_loc");
		String distanceStr=request.getParameter("distance");
		String costStr=request.getParameter("cost");
		String Bdate=request.getParameter("date");
		String Bpayment=request.getParameter("payment");
		String vidStr=request.getParameter("vid");
		String didStr=request.getParameter("did");
		
		
		int cid=(int)ses.getAttribute("UID");
		String name=request.getParameter("name");
		String cnum=request.getParameter("Cnumber");
		String desc=request.getParameter("description");
		
		int vid=0;
		int did=0;
		int distance=0;
		double cost=0;
		
		System.out.println(distanceStr);
		
		try {
			
			distance=Integer.parseInt(distanceStr);
			cost=Double.parseDouble(costStr);
			vid=Integer.parseInt(vidStr);
			did=Integer.parseInt(didStr);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		LocalDate Ldate=LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = Ldate.format(formatter);

		Payment payment=new Payment(0,cost,cnum,date,desc,cid);
		boolean success=PaymentDBUtil.insrtPayment(payment);
		
		if(success==true) {
			Booking booking=new Booking(0,Bname,tel,pickup_loc,drop_loc,distance,cost,Bdate,Bpayment,vid,cid,"pending",did);
			success=BookingDBUtil.insertBooking(booking);
			if(success==true) {
				try {
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
		
		
	}

}
