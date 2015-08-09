package Controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.mixSmash;
	
/**
 * Servlet implementation class updateProfileController
 */
@WebServlet("/updateProfileController")
public class updateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProfileController() {
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
			registerMember memDetails = (registerMember)session.getAttribute("memDetails");
			
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String dobday = request.getParameter("dobday");
			String dobmonth = request.getParameter("dobmonth");
			String dobyear = request.getParameter("dobyear");
			String address = request.getParameter("address");
			String contactno = request.getParameter("contactno");
			String sub = request.getParameter("subscribe");
			String msg = "";
			
			String chkpassword = "^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$";
			Pattern passwordPattern = Pattern.compile(chkpassword);
			Matcher passwordMatcher = passwordPattern.matcher(password);
			
			String chkcontactno = "[6,8,9]{1}[0-9]{7,7}";
			Pattern contactnoPattern = Pattern.compile(chkcontactno);
			Matcher contactnoMatcher = contactnoPattern.matcher(contactno);
			
			registerMemberDB db = new registerMemberDB();
			mixSmash ms = new mixSmash();
			
			if(password.length() == 0){
				msg = "Please enter your password!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else if(!passwordMatcher.matches()){
				msg = "Password is not strong! Have at least 8 characters with upper/lower case & numbers!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else if(name.length() == 0){
				msg = "Please enter your name!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else if(gender == null){
				msg = "Please select your gender!";
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(dobday.equals("Day") || dobmonth.equals("Month") || dobyear.equals("Year")){
				msg = "Please enter your birthday!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else if(dobmonth == "Febuary" && (dobday == "30" || dobday == "31")){
				msg = "Invalid birthday in Febuary!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else if(address.length() == 0){
				msg = "Please enter your address!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else if(contactno.length() == 0){
				msg = "Please enter your contact!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else if(!contactnoMatcher.matches()){
				msg = "Please enter a valid contact no.!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else if(sub == null){
				msg = "Please select your subscription!";
				response.sendRedirect("updateProfile.jsp?msg="+msg);
			}else{
				byte[] enpass = ms.encrypt(password);
				boolean enpassSuccess = db.encryptUpdatePass(memDetails.getEmail(), enpass);
				
				if(enpassSuccess != false){
					String depass = ms.decrypt(enpass);
					registerMember updateMember = db.updateMember(memDetails.getMemid(), memDetails.getEmail(), new String(enpass), depass, name, gender, dobday, dobmonth, dobyear, address, contactno, sub);
					
					if(updateMember != null){
						session.setAttribute("memDetails", updateMember);
						response.sendRedirect("updateProfile.jsp?msg=Update Success!");
					}else{
						response.sendRedirect("updateProfile.jsp?msg=Update Fail!");
					}
				}else{
					response.sendRedirect("updateProfile.jsp?msg=Update Fail!");
				}
			}
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

}
