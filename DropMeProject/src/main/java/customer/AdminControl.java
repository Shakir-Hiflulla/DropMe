package customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AdminControl
 */
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");

		switch (action) {
		
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
				Admin ad=AdminDBUtil.getAdmin(id);
				request.setAttribute("user", ad);
				RequestDispatcher dis= request.getRequestDispatcher("Profile.jsp");
				dis.forward(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
