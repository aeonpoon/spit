package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.mixSmash;
import Model.*;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			HttpSession session = request.getSession();
			
			String loginid = request.getParameter("loginid");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			String status = "";
			
			LoginDB db = new LoginDB();
			mixSmash ms = new mixSmash();
			voucherGenDB voucherdb = new voucherGenDB();
			
			if(role.equals("member")){
				byte[] enpass = db.passVerify(loginid);
				String depass = ms.decrypt(enpass);
				
				if(password.equals(depass)){
					registerMember memdetails = db.loginMember(loginid, depass);
					voucherGen code = voucherdb.getvCode(memdetails.getMemid());
					
					if(code == null){
						code.setStringChars("-");
					}
				
					if(memdetails != null){
						status = "verifyMem";
						session.setAttribute("status", status);
						session.setAttribute("memDetails", memdetails);
						session.setAttribute("code", code);
						response.sendRedirect("memberpage.jsp");
					}
				}else{
					response.sendRedirect("login.jsp?msg=Userid/Password incorrect");
				}
			}else{
				boolean admin = db.loginAdmin(loginid, password); 
				
				if(admin != false){
					status = "verifyAdmin";
					session.setAttribute("status", status);
					Login admdetails = new Login(loginid, password); 
					session.setAttribute("admDetails", admdetails);
					response.sendRedirect("adminpage.jsp");
				}else{
					response.sendRedirect("login.jsp?msg=Userid/Password incorrect");
				}
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		
	}

}
