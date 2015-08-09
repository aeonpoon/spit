package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.cartDB;
import Model.registerMember;
import Model.*;

import java.util.*;
/**
 * Servlet implementation class displayCartController
 */
@WebServlet("/displayCartController")
public class displayCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayCartController() {
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
			String msg = request.getParameter("msg");
			
			if(memDetails == null){
				response.sendRedirect("login.jsp?msg=Please Login!");
			}else{
				cartDB db = new cartDB();
				ArrayList<getCart> displayCart = db.displayCart(memDetails.getMemid());
				
				if(msg != null){
					session.setAttribute("displayCart", displayCart);
					response.sendRedirect("displayCart.jsp?msg="+msg);
				}else{
					session.setAttribute("displayCart", displayCart);
					response.sendRedirect("displayCart.jsp");
				}
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
