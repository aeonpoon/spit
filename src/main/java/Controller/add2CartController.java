package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

/**
 * Servlet implementation class add2CartController
 */
@WebServlet("/add2CartController")
public class add2CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add2CartController() {
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
			
			int prodid = Integer.parseInt(request.getParameter("prodid"));
			int catid = Integer.parseInt(request.getParameter("catid"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			
			cartDB db = new cartDB();
			int getcartid = db.getcartid(memDetails.getMemid());
			
			if(getcartid == 0){
				boolean insertcart = db.insertcart(memDetails.getMemid());
				
				if(insertcart != false){
					boolean insertcarthasproduct = db.insertcarthasproduct(getcartid, prodid, catid, quantity);
					if(insertcarthasproduct != false){
						response.sendRedirect("displayCartController");
					}
				}else{
					response.sendRedirect("displayCart.jsp?msg=Insert Fail!");
				}
				
			}else{
				boolean insertcarthasproduct = db.insertcarthasproduct(getcartid, prodid, catid, quantity);
				if(insertcarthasproduct != false){
					response.sendRedirect("displayCartController");
				}else{
					response.sendRedirect("displayCart.jsp?msg=Insert Fail!");
				}
			}
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		
	}

}
