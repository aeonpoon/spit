package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;
import java.util.*;

/**
 * Servlet implementation class upCartController
 */
@WebServlet("/upCartController")
public class upCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upCartController() {
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
			ArrayList<getCart> displayCart = (ArrayList<getCart>)session.getAttribute("displayCart");
			
			if(memDetails == null){
				response.sendRedirect("login.jsp?msg=Please Login!");
			}else{
				int prodid = Integer.parseInt(request.getParameter("prodid"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				
				for(getCart d:displayCart){
					if(d.getProdid() == prodid){
						if(quantity > d.getProdqty()){
							response.sendRedirect("displayCart.jsp?msg=Not enough Stock: "+d.getBrand()+" "+d.getProdname()+", current: "+d.getProdqty()+"!");
						}else{
							cartDB db = new cartDB();
							int getcartid = db.getcartid(memDetails.getMemid());
							
							if(getcartid != 0){
								boolean updProd = db.upProd(getcartid, prodid, quantity);	
								
								if(updProd != false){
									response.sendRedirect("displayCartController?msg=Update Success!");
								}else{
									response.sendRedirect("displayCart.jsp?msg=Update fail!");
								}
							}else{
								response.sendRedirect("displayCart.jsp?msg=Update fail!");
							}
						}
					}
				}
				
			}
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		
		
	}

}
