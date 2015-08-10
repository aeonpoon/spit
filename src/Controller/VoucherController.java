package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

/**
 * Servlet implementation class VoucherController
 */
@WebServlet("/VoucherController")
public class VoucherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoucherController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		registerMember memDetails = (registerMember)session.getAttribute("memDetails");
		
		voucherGenDB db = new voucherGenDB();
		boolean getloginid = db.getVoucherDetails(memDetails.getMemid());
		
		if(getloginid == false){
			char[] num = new char[10];
	        ArrayList<Character> charList = new ArrayList<Character>();
	        String stringChars = "";
	        
	        for(char c='a',b='A'; c <= 'z'; c++,b++) {
	            charList.add(c);
	            charList.add(b);
	        }
	        
	        for(char c='0'; c <= '9'; c++) {
	            num[c - '0'] = c;
	        }

			for (int i = 0; i < 6; i++){
	            int sw = (int )(Math. random() * 2);
	            if(sw == 0){
	                int random = (int )(Math. random() * 9 + 1);
	                stringChars += num[random];
	            }else{
	                int random = (int )(Math. random() * 51 + 1);
	                stringChars += charList.get(random);
	            }
			}
			
			boolean genCode = db.insertVoucher(memDetails.getMemid(), stringChars);
			
			if(genCode != false){
				voucherGen code = new voucherGen(stringChars);
				session.setAttribute("code", code);
				response.sendRedirect("coupons.jsp?msg=Code generated. Enjoy!");
			}else{
				response.sendRedirect("coupons.jsp?msg=Error generating code!");
			}
		}else{
			response.sendRedirect("coupons.jsp?msg=You only can generate the code once a day!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
