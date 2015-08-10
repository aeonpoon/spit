package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.cartDB;
import Model.confirmPurchase;
import Model.getCart;
import Model.registerMember;
import Model.voucherGen;

/**
 * Servlet implementation class placeOrderController
 */
@WebServlet("/placeOrderController")
public class placeOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public placeOrderController() {
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
			voucherGen code = (voucherGen)session.getAttribute("code");
			ArrayList<getCart> displayCart = (ArrayList<getCart>)session.getAttribute("displayCart");
			
			int subtotal = Integer.parseInt(request.getParameter("subtotal"));
			String cctype = request.getParameter("cctype");
			String ccnum = request.getParameter("ccnum");
			String ccname = request.getParameter("ccname");
			int expdateM = Integer.parseInt(request.getParameter("expdateM"));
			int expdateY = Integer.parseInt(request.getParameter("expdateY"));
			int cvcnum = Integer.parseInt(request.getParameter("cvcnum"));
			int total = 0;;
			int discount = 0;
			System.out.println("ccnum: " + ccnum);
				
			if(memDetails == null){
				response.sendRedirect("login.jsp?msg=Please Login!");
			}else{
				cartDB db = new cartDB();
				String vcode = code.getStringChars();
				if(vcode != null){
					discount = 15;
					subtotal -= discount;
					total = subtotal;
					voucherGen getdiscount = new voucherGen(discount);
					session.setAttribute("getdiscount", getdiscount);
				}else{
					vcode = "-";
					discount = 0;
					voucherGen getdiscount = new voucherGen(discount);
					session.setAttribute("getdiscount", getdiscount);
				}
				
				for(getCart c:displayCart){
					int finalqty = c.getProdqty() - c.getQuantity();
					boolean removeqty = db.removeqtyfromproduct(c.getProdid(), c.getCatid(), finalqty);
					if(removeqty == false){
						response.sendRedirect("displayCart.jsp?msg=Unable to deduct from stock!");
					}
				}
				
				confirmPurchase displayPurchase = new confirmPurchase(ccnum, ccname, cctype, expdateM, expdateY);
				
				boolean orderProd = db.orderProd(memDetails.getMemid(), total, cctype, ccnum, ccname, expdateM, expdateY, cvcnum, vcode);
				if(orderProd != false){
					
					int getTransID = db.getTransID(memDetails.getMemid());
					if(getTransID != 0){
						if(displayCart.size() != 0){
							for(getCart c:displayCart){
								
								boolean memidForConfirmPurchase = db.memidForConfirmPurchase(memDetails.getMemid());
								
								if(memidForConfirmPurchase != false){
									int getConfirmPurchaseID = db.getConfirmPurchaseID(memDetails.getMemid());	
									
									if(getConfirmPurchaseID != 0){
										subtotal = c.getPrice() * c.getQuantity();
										boolean confirmProd = db.confirmpurchaseProd(getTransID, memDetails.getMemid(), getConfirmPurchaseID, c.getProdid(), c.getBrand(), c.getProdname(), c.getPrice(), c.getQuantity(), subtotal);
										
										if(confirmProd == false){
											response.sendRedirect("displayCart.jsp?msg=Unable to insert product!");
										}
									}else{
										response.sendRedirect("displayCart.jsp?msg=Unable to get Confirm Purchase ID!");
									}
								}
							}
							
							for(getCart c:displayCart){
								boolean removeprodfromCart = db.removeprodfromCart(c.getProdid(), c.getCartid());
								if(removeprodfromCart == false){
									response.sendRedirect("displayCart.jsp?msg=Unable remove product from cart!");
								}
							}
							
							boolean removeCart = db.removeCart(memDetails.getMemid());
							
							if(removeCart != false){
								session.setAttribute("displayPurchase", displayPurchase);
								response.sendRedirect("confirmPurchase.jsp?msg=Order Success!");
							}else{
								response.sendRedirect("displayCart.jsp?msg=Unable to remove cart!");
							}
							
						}else{
							response.sendRedirect("displayCart.jsp?msg=Order is empty!");
						}
					}else{
						response.sendRedirect("displayCart.jsp?msg=Unable to get Transaction ID!");
					}
				}else{
					response.sendRedirect("displayCart.jsp?msg=Details not filled!");
				}
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		
	}

}
