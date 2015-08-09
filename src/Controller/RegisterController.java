package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.mixSmash;

import java.util.regex.*;

import Model.*;

/**
 * Servlet implementation class CreateMember
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
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
			
			String password = request.getParameter("password");
			String chkpassword = "^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$, g";
			Pattern passwordPattern = Pattern.compile(chkpassword);
			Matcher passwordMatcher = passwordPattern.matcher(password);
			
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String dobday = request.getParameter("dobday");
			String dobmonth = request.getParameter("dobmonth");
			String dobyear = request.getParameter("dobyear");
			String address = request.getParameter("address");
			
			String contactno = request.getParameter("contactno");
			String chkcontactno = "[6,8,9]{1}[0-9]{7}";
			Pattern contactnoPattern = Pattern.compile(chkcontactno);
			Matcher contactnoMatcher = contactnoPattern.matcher(contactno);
			
			String email = request.getParameter("email");
			String checkemail = "^[A-Za-z\\._\\-0-9]*[@][A-Za-z]*[\\.][a-z]{2,3}$";
			Pattern emailPattern = Pattern.compile(checkemail);
			Matcher emailMatcher = emailPattern.matcher(email);
			
			String sub = request.getParameter("subscribe");
			String msg = "";
			
			registerMember reg = new registerMember(0, email, password, name, gender, dobday, dobmonth, dobyear, address, contactno, sub);
			
			if(email.length() == 0){
				msg = "Please enter your email address!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(!emailMatcher.matches()){
				msg = "Not valid. Please enter a valid email address!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(password.length() == 0){
				msg = "Please enter your password!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(!passwordMatcher.matches()){
				msg = "Password is not strong! Have at least 8 characters with upper/lower case & numbers!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(name.length() == 0){
				msg = "Please enter your name!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(gender == null){
				msg = "Please select your gender!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(dobday.equals("Day") || dobmonth.equals("Month") || dobyear.equals("Year")){
				msg = "Please enter your birthday!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(dobmonth == "Febuary" && (dobday == "30" || dobday == "31")){
				msg = "Invalid birthday in Febuary!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(dobmonth == "Febuary" && Integer.parseInt(dobday) == 29 && Integer.parseInt(dobyear) % 4 != 0){
				msg = "Not a leap year!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(address.length() == 0){
				msg = "Please enter your address!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(contactno.length() == 0){
				msg = "Please enter your contact!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(!contactnoMatcher.matches()){
				msg = "Please enter a valid contact no.!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else if(sub == null){
				msg = "Please select your subscription!";
				session.setAttribute("details", reg);
				response.sendRedirect("createMember.jsp?msg="+msg);
			}else{

				registerMemberDB db = new registerMemberDB();
				mixSmash ms = new mixSmash();
				
				boolean chkemail = db.checkemail(email);
				
				if(chkemail != true){
					boolean success = db.createMember(reg);
					
					if(success != false){
						byte[] enpass = ms.encrypt(password);
						boolean enpassSuccess = db.encryptPass(email, enpass);
						boolean enpassStringSuccess = db.enpassString(email, new String(enpass));
						
						if(enpassSuccess != false && enpassStringSuccess != false){
							session.removeAttribute("details");
							response.sendRedirect("login.jsp?success="+success+"&msg=Please proceed to login!");
						}
					}else{
						session.setAttribute("details", reg);
						response.sendRedirect("createMember.jsp?success="+success+"&msg=Sign up failed!");
					}
				}else{
					session.setAttribute("details", reg);
					response.sendRedirect("createMember.jsp?success="+chkemail+"&msg=Email exist! Sign up failed");
				}
			}
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

}
