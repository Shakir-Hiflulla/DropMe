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
import java.util.List;


public class cloginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		switch (action) {
		case "profile": 
			getProfile(request,response);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");

		switch (action) {
		case "login": 
			doPostLogin(request,response);
			
			break;
		case "logout":
			doPostlogout(request,response);
			break;
		case "getCust":
			doPostlogout(request,response);
			break;
		
		case "edit-customer":
			editCustomer(request,response);
			break;
		case "edit-admin":
			editAdmin(request,response);
			break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}

	}

	


	private void editAdmin(HttpServletRequest request, HttpServletResponse response) {
		boolean edit=false;
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String mail=request.getParameter("email");
		String pass=request.getParameter("pass");
		String sts=request.getParameter("status");
		
		Admin a=new Admin(id,name,mail,tel,pass,sts);
		
		edit=AdminDBUtil.editAdmin(a);
		
		if(edit) {
			try {
				RequestDispatcher dis= request.getRequestDispatcher("home.jsp");
				dis.forward(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
		boolean edit=false;
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String mail=request.getParameter("email");
		String pass=request.getParameter("pass");
		
		User u=new User(id,name,mail,tel,pass);
		
		edit=CustomerDBUtil.editUser(u);
		
		if(edit) {
			try {
				RequestDispatcher dis= request.getRequestDispatcher("home.jsp");
				dis.forward(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	

	private void doPostlogout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false); 
        try {
        	if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("clogin.jsp");
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
	}

	private void doPostLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String email=request.getParameter("email");
			String pass=request.getParameter("pass");
			String role=request.getParameter("role");
			
			if(role.equals("customer")) {
				try {
					boolean login=CustomerDBUtil.validate(email, pass);
					
					if(login==true) {
						List <User> user= CustomerDBUtil.getUser(email, pass);
						int id=user.get(0).getId();
						HttpSession session=request.getSession(true);
						session.setAttribute("UID", id);
						session.setAttribute("role", role);
						request.setAttribute("cust", user);
						RequestDispatcher dis= request.getRequestDispatcher("home.jsp");
						dis.forward(request, response);
					}else{
					request.setAttribute("triggerJoin", "true");
					RequestDispatcher dis= request.getRequestDispatcher("clogin.jsp");
					dis.forward(request, response);
						

					}
				}catch (Exception e){
					
					e.printStackTrace();	
				}
				
			}
			else if(role.equals("driver")) {
				try {
					
					
					boolean login=DriverDBUtil.validate(email, pass);
					System.out.println(login);
					if(login==true) {
						List <Driver> user= DriverDBUtil.getUser(email, pass);
						int id=user.get(0).getId();
						System.out.println(id);
						HttpSession session=request.getSession(true);
						session.setAttribute("role", role);
						session.setAttribute("DID", id);

						RequestDispatcher dis= request.getRequestDispatcher("home.jsp");
						dis.forward(request, response);

						
					}else{
						RequestDispatcher dis= request.getRequestDispatcher("clogin.jsp");
						dis.forward(request, response);
					}
				}catch (Exception e){
					
					e.printStackTrace();	
				}
				
				
			}else if(role.equals("admin")) {
				
					try {
					
					boolean login=AdminDBUtil.validate(email, pass);
					System.out.println(login);
					if(login==true) {
						List <Admin> user= AdminDBUtil.getUser(email, pass);
						int id=user.get(0).getId();
						System.out.println(id);
						HttpSession session=request.getSession(true);
						session.setAttribute("role", role);
						session.setAttribute("AID", id);

						RequestDispatcher dis= request.getRequestDispatcher("home.jsp");
						dis.forward(request, response);

						
					}else{
						RequestDispatcher dis= request.getRequestDispatcher("clogin.jsp");
						dis.forward(request, response);
					}
				}catch (Exception e){
					
					e.printStackTrace();	
				}
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void getProfile(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("id"));
		String role=request.getParameter("role");
		
		if(role.equals("customer")) {
			User user=CustomerDBUtil.getCustomer(id);
			try {
				request.setAttribute("user", user);
				RequestDispatcher dis=request.getRequestDispatcher("Profile.jsp");
				dis.forward(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(role.equals("driver")) {
			Driver driver=DriverDBUtil.getDriver(id);
			Vehicle veh=VehicleDBUtil.getvehicleDetails(id);
			try {
				request.setAttribute("veh", veh);
				request.setAttribute("user", driver);
				RequestDispatcher dis=request.getRequestDispatcher("Profile.jsp");
				dis.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(role.equals("admin")) {
			Admin admin=AdminDBUtil.getAdmin(id);
			try {
				request.setAttribute("user", admin);
				RequestDispatcher dis=request.getRequestDispatcher("Profile.jsp");
				dis.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
