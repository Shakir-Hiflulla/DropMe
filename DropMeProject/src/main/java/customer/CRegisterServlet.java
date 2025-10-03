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
 * Servlet implementation class CRegisterServlet
 */
public class CRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");

		switch (action) {
		case "getCust":
			getAllCustomer(request,response);
			break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
	}
	
	
	private void getAllCustomer(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<User> user=CustomerDBUtil.getCustomerList();
		try {
		request.setAttribute("userlist", user);
		RequestDispatcher dis= request.getRequestDispatcher("CustomerList.jsp");
		dis.forward(request, response);
		}
		catch(Exception e) {
			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");

		switch (action) {
		case "register":
			registerCustomer(request,response);
			break;
		case "dltCust":
			removeCustomer(request,response);
			break;
		
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
		}
	
	}
	
	


	private void removeCustomer(HttpServletRequest request, HttpServletResponse response) {
		int id= Integer.parseInt(request.getParameter("u_id"));
		
		boolean dlt=false;
		
		dlt=CustomerDBUtil.deleteCustomer(id);
		if(dlt) {
			
			 try {
				response.sendRedirect("CRegisterServlet?action=getCust");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}


	private void registerCustomer(HttpServletRequest request, HttpServletResponse response) {
		String name=request.getParameter("name");
		String phone=request.getParameter("tel");
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		
		boolean rs=CustomerDBUtil.insertCustomer(name, phone, email, pass);
		
		
			try {
				if(rs==true) {
					RequestDispatcher dis= request.getRequestDispatcher("home.jsp");
					dis.forward(request, response);
				}else {
					RequestDispatcher dis= request.getRequestDispatcher("CRegister.jsp");
					dis.forward(request, response);}
			} catch (ServletException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	
		
	}
}
	
