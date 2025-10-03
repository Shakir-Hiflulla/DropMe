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
 * Servlet implementation class ContactControl
 */
public class ContactControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		switch (action) {
		case "getfaq": 
			getFaq(request,response);
			break;
		case "getuserfaq": 
			getUserFaq(request,response);
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	private void getUserFaq(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<FAQ> faq=ContactDBUtil.getuserFaq();
		try {
		request.setAttribute("faq", faq);
		RequestDispatcher dis= request.getRequestDispatcher("Contact.jsp");
		dis.forward(request, response);
		}
		catch(Exception e) {
			
		}
		
	}

	private void getFaq(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<FAQ> faq=ContactDBUtil.getFaq();
		try {
		request.setAttribute("faq", faq);
		RequestDispatcher dis= request.getRequestDispatcher("AdminContact.jsp");
		dis.forward(request, response);
		}
		catch(Exception e) {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		switch (action) {
		case "ask": 
			questionUpdate(request,response);
			break;
		case "publish": 
			publishQuestion(request,response);
			break;
		case "answer": 
			replyQuestion(request,response);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	private void replyQuestion(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("f_id"));
		String ans=request.getParameter("answr");
		boolean pub=false;
		pub=ContactDBUtil.replyFaq(id,ans);
		if (pub) {
			ArrayList<FAQ> faq=ContactDBUtil.getFaq();
			try {
			request.setAttribute("faq", faq);
			RequestDispatcher dis= request.getRequestDispatcher("AdminContact.jsp");
			dis.forward(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private void publishQuestion(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("f_id"));
		String sts=request.getParameter("sts");
		
		boolean pub=false;
		pub=ContactDBUtil.publishFaq(id,sts);
		if (pub) {
			ArrayList<FAQ> faq=ContactDBUtil.getFaq();
			try {
			request.setAttribute("faq", faq);
			RequestDispatcher dis= request.getRequestDispatcher("AdminContact.jsp");
			dis.forward(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private void questionUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		String qstn=request.getParameter("question");
		String email=request.getParameter("email");
		String rply="";
		String sts="Archived";
		
		FAQ faq=new FAQ(0,qstn,rply,email,sts);
		
		boolean inst=false;
		inst=ContactDBUtil.insetFaq(faq);
		if (inst) {
			ArrayList<FAQ> faq1=ContactDBUtil.getuserFaq();
			try {
				request.setAttribute("faq", faq1);
				RequestDispatcher dis= request.getRequestDispatcher("Contact.jsp");
				dis.forward(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
