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
 * Servlet implementation class recentorderController
 */
@WebServlet("/recentorderController")
public class recentorderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recentorderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			
			HttpSession session = request.getSession();
			registerMember memDetails = (registerMember)session.getAttribute("memDetails");
			
			if(memDetails == null){
				response.sendRedirect("login.jsp?msg=Please Login!");
			}else{
				confirmPurchaseDB db = new confirmPurchaseDB();
				ArrayList<confirmPurchase> getTransactionID = db.getTransactionID(memDetails.getMemid());
				ArrayList<confirmPurchase> displayPurchaseDetails = new ArrayList<confirmPurchase>();
				ArrayList<confirmPurchase> getTransactionProd = new ArrayList<confirmPurchase>();
				
				displayPurchaseDetails = db.displayPurchaseDetails(memDetails.getMemid());
				getTransactionProd = db.displayRecentPurchase(memDetails.getMemid());
					
				session.setAttribute("getTransactionID", getTransactionID);
				session.setAttribute("displayPurchaseDetails", displayPurchaseDetails);
				session.setAttribute("getTransactionProd", getTransactionProd);
				session.removeAttribute("getdiscount");
				session.removeAttribute("displayPurchase");
				response.sendRedirect("recentorder.jsp");
				
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
