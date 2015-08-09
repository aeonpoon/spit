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
 * Servlet implementation class delProductFromCart
 */
@WebServlet("/delCartController")
public class delCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delCartController() {
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
			
			int prodid = Integer.parseInt(request.getParameter("prodid"));
			registerMember memDetails = (registerMember)session.getAttribute("memDetails");
			
			if(memDetails == null){
				response.sendRedirect("login.jsp?msg=Please Login!");
			}else{
				cartDB db = new cartDB();
				int getcartid = db.getcartid(memDetails.getMemid());
				
				if(getcartid != 0){
					boolean prodDel = db.delProd(prodid, getcartid);	
					
					if(prodDel != false){
						response.sendRedirect("displayCartController?msg=Delete Success!");
					}else{
						response.sendRedirect("displayCart.jsp?msg=Delete fail!");
					}
				}else{
					response.sendRedirect("displayCart.jsp?msg=Delete fail!");
				}
			}
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

}
