package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.*;
import Model.*;
import security.*;
/**
 * Servlet implementation class forgetPassController
 */
@WebServlet("/forgetPassController")
public class forgetPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgetPassController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String email = request.getParameter("email");
			
			LoginDB db = new LoginDB();
			mixSmash ms = new mixSmash();
			SendMail sm = new SendMail();
			
			byte[] pass = db.forgetPass(email);
			
			if(pass != null){
				String depass = ms.decrypt(pass);
				boolean success = sm.sendforgetPass(email, depass);
				if(success != false){
					response.sendRedirect("forgetpass.jsp?msg=Password sent, check your email!");
				}
			}else{
				response.sendRedirect("forgetpass.jsp?msg=Email not found!");
			}
		
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
