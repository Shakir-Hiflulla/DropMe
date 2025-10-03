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
 * Servlet implementation class DriverControl
 */
public class DriverControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverControl() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");

		switch (action) {
		case "getDri": 
			getDriverDetails(request,response);
			break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
	}
    
	private void getDriverDetails(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Driver> dri=DriverDBUtil.getDriverDetails();
		
		try {
			request.setAttribute("dri", dri);
			RequestDispatcher dis=request.getRequestDispatcher("DriverDetails.jsp");
			dis.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");

		switch (action) {
		case "DReg": 
			insertDriver(request,response);
			
			break;
		case "dltDri": 
			deleteDriver(request,response);
			
			break;
		case "edit-driver":
			editDriver(request,response);
			break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
	}
	private void editDriver(HttpServletRequest request, HttpServletResponse response) {
		
		boolean edit=false;
		
		int id=Integer.parseInt(request.getParameter("id"));
		String dname=request.getParameter("name");
		String dtel=request.getParameter("tel");
		String dmail=request.getParameter("email");
		String dlicense=request.getParameter("licence");
		String dpass=request.getParameter("pass");
		
		Driver d=new Driver(id,dname,dmail,dtel,dpass,dlicense);
		
		edit=DriverDBUtil.editDriver(d);
		
		if(edit) {
			try {
				Driver dri=DriverDBUtil.getDriver(id);
				request.setAttribute("user", dri);
				RequestDispatcher dis= request.getRequestDispatcher("Profile.jsp");
				dis.forward(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	private void deleteDriver(HttpServletRequest request, HttpServletResponse response) {
		int did=Integer.parseInt(request.getParameter("d_id"));
		boolean dlt=false;
		dlt=DriverDBUtil.deleteDriver(did);
		if(dlt) {
			ArrayList<Driver> dri=DriverDBUtil.getDriverDetails();
			
			try {
				request.setAttribute("dri", dri);
				RequestDispatcher dis=request.getRequestDispatcher("DriverDetails.jsp");
				dis.forward(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	private void insertDriver(HttpServletRequest request, HttpServletResponse response) {
		
		String brand=request.getParameter("brand");
		String name=request.getParameter("name");
		String number=request.getParameter("number");
		String type=request.getParameter("type");
		String dname=request.getParameter("dname");
		String dtel=request.getParameter("dtel");
		String dmail=request.getParameter("dmail");
		String dlicense=request.getParameter("dlicense");
		String dpass=request.getParameter("dpass");
		String status="Unchecked";
		
		Vehicle v=new Vehicle(0,name,number,0.0,brand,type,status,0);
		
		Driver d=new Driver(0,dname,dmail,dtel,dpass,dlicense,v);
		
		boolean success=DriverDBUtil.inserDriver(d);
		
		if (success==true) {
			System.out.println("habsfjashfbkbfasb");
			try {
				response.sendRedirect("clogin.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
