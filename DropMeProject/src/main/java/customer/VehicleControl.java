package customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class VehicleControl
 */
public class VehicleControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		switch (action) {
		case "getVeh":
			GetVehicleList(request,response);
			break;
		case "editVehicle":
			updateVehicle(request,response);
			break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
	}

	private void updateVehicle(HttpServletRequest request, HttpServletResponse response) {
		boolean edit=false;
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String vnum=request.getParameter("vnum");
		String vbrand=request.getParameter("vbrand");
		String vtype=request.getParameter("vtype");
		double rate=Double.parseDouble(request.getParameter("rate"));
		String status=request.getParameter("status");
		
		Vehicle v=new Vehicle(id,name,vnum,rate,vbrand,vtype,status,0);
		
		edit=VehicleDBUtil.updateVehicle(v);
		
		if(edit) {
			try {
				Vehicle veh=VehicleDBUtil.getvehicleDetails(id);
				request.setAttribute("veh",veh );
				request.setAttribute("triggerJoin", "true");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Profile.jsp");
				dispatcher.forward(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private void GetVehicleList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Vehicle> v=VehicleDBUtil.getvehicleDetails();
		try {
			request.setAttribute("veh", v);
			RequestDispatcher dis= request.getRequestDispatcher("booking.jsp");
			dis.forward(request, response);
			}
			catch(Exception e) {
				
			}
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		switch (action) {
		case "aprove":
			approveVehicle(request,response);
			break;
		case "setRate":
			setRateVeh(request,response);
			break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}
	
	private void approveVehicle(HttpServletRequest request, HttpServletResponse response) {
		int vid=Integer.parseInt(request.getParameter("v_id"));
		boolean aprv=false;
		aprv=VehicleDBUtil.approveVehicle(vid);
		if(aprv) {
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
		
		
	}
	private void setRateVeh(HttpServletRequest request, HttpServletResponse response) {
		int vid=Integer.parseInt(request.getParameter("v_id"));
		String rt=request.getParameter("rate");
		double rate=Double.parseDouble(rt);
		
		boolean set=false;
		set=VehicleDBUtil.setRateVeh(vid,rate);
		if(set) {
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
		
		
	}

}
