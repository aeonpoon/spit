package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;
import mail.*;

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
			voucherGen getdiscount = (voucherGen)session.getAttribute("getdiscount");
			
			int subtotal = Integer.parseInt(request.getParameter("subtotal"));
			String cctype = request.getParameter("cctype");
			String ccnum = request.getParameter("ccnum");
			String ccname = request.getParameter("ccname");
			String expdateM = request.getParameter("expdateM");
			String expdateY = request.getParameter("expdateY");
			String cvcnum = request.getParameter("cvcnum");
			int total = 0;;
			int discount = 0;
			String msg="";
			
			String chkccname = "([A-Za-z\\s]{5,})";
			Pattern chkccnamePattern = Pattern.compile(chkccname);
			Matcher chkccnameMatcher = chkccnamePattern.matcher(ccname);
			
			String visaccnum = "(^4[0-9]{12}(?:[0-9]{3}))";
			Pattern visaccnumPattern = Pattern.compile(visaccnum);
			Matcher visaccnumMatcher = visaccnumPattern.matcher(ccnum);
			
			String masterccnum = "(^5[1-5][0-9]{14})";
			Pattern masterccnumPattern = Pattern.compile(masterccnum);
			Matcher masterccnumMatcher = masterccnumPattern.matcher(ccnum);
			
			String expressccnum = "(^3[47][0-9]{13})";
			Pattern expressccnumPattern = Pattern.compile(expressccnum);
			Matcher expressccnumMatcher = expressccnumPattern.matcher(ccnum);
			
			String dinersccnum = "(^3(?:0[0-5]|[68][0-9])[0-9]{11})";
			Pattern dinersccnumPattern = Pattern.compile(dinersccnum);
			Matcher dinersccnumMatcher = dinersccnumPattern.matcher(ccnum);
			
			String jcbccnum = "(^(?:2131|1800|35\\d{3})\\d{11})";
			Pattern jcbccnumPattern = Pattern.compile(jcbccnum);
			Matcher jcbccnumMatcher = jcbccnumPattern.matcher(ccnum);
			
			String chkexpdate = "[0-9]{2}";
			Pattern chkexpdatePattern = Pattern.compile(chkexpdate);
			Matcher chkexpdateMMatcher = chkexpdatePattern.matcher(expdateM);
			Matcher chkexpdateYMatcher = chkexpdatePattern.matcher(expdateY);
			
			String chkcvcnum = "[0-9]{3}";
			Pattern chkcvcnumPattern = Pattern.compile(chkcvcnum);
			Matcher chkcvcnumMatcher = chkcvcnumPattern.matcher(cvcnum);
				
			if(memDetails == null){
				response.sendRedirect("login.jsp?msg=Please Login!");
			}else{
				
				if(ccnum.length() == 0){
					msg = "Please enter your credit card number!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(ccname.length() == 0){
					msg = "Please enter your credit card name!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(!chkccnameMatcher.matches()){
					msg = "Please enter characters for your credit card name!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(cctype == "Select Card"){
					msg = "Please select your credit card type!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(cctype == "Visa" && !visaccnumMatcher.matches()){	
					msg = "Incorrect Visa credit card number!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(cctype == "MasterCard" && !masterccnumMatcher.matches()){	
					msg = "Incorrect MasterCard credit card number!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(cctype == "JCB" && !jcbccnumMatcher.matches()){	
					msg = "Incorrect JCB credit card number!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(cctype == "America Express" && !expressccnumMatcher.matches()){	
					msg = "Incorrect America Express credit card number!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(cctype == "Diners Club" && !dinersccnumMatcher.matches()){
					msg = "Incorrect Diners Club credit card number!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(expdateM.length() == 0){
					msg = "Please enter your credit card expiry month!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(!chkexpdateMMatcher.matches()){
					msg = "Please enter only 2 digits for your credit card expiry month!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(expdateY.length() == 0){
					msg = "Please enter your credit card expiry year!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(!chkexpdateYMatcher.matches()){
					msg = "Please enter only 2 digits your credit card expiry year!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(cvcnum.length() == 0){
					msg = "Please enter your credit card CVV number!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else if(!chkcvcnumMatcher.matches()){
					msg = "Please enter your correct CVV number!";
					response.sendRedirect("placeOrder.jsp?msg="+msg);
				}else{
					cartDB db = new cartDB();
					SendMail mail = new SendMail();
					
					if(code.getStringChars().equals("-")){
						discount = 0;
						subtotal -= discount;
						total = subtotal;
						getdiscount = new voucherGen(discount);
						session.setAttribute("getdiscount", getdiscount);
					}else{
						discount = 15;
						subtotal -= discount;
						total = subtotal;
						getdiscount = new voucherGen(discount);
						session.setAttribute("getdiscount", getdiscount);						
					}
					
					confirmPurchase displayPurchase = new confirmPurchase(ccnum, ccname, cctype, Integer.parseInt(expdateM), Integer.parseInt(expdateY));
					
					boolean orderProd = db.orderProd(memDetails.getMemid(), total, cctype, ccnum, ccname, Integer.parseInt(expdateM), Integer.parseInt(expdateY), Integer.parseInt(cvcnum), code.getStringChars());
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
								
								int msubtotal=0;
								
								String data = "<table><tr><td align='center' width='150'> <b>Transaction ID:</b> </td>"
										+ "<td align='center' width='150'> <b>Credit Card Name:</b> </td>"
										+ "<td align='center' width='150'> <b>Credit Card No.:</b> </td>"
										+ "<td align='center' width='150'> <b>Credit Card Type:</b> </td>"
										+ "<td align='center' width='150'> <b>Total:</b> </td>";
											
								data +=	"<tr><td align='center'>"+getTransID+"</td>"
										+ "<td align='center'>"+ccname+"</td><td align='center'>"+ccnum+"</td>"
												+ "<td align='center'>"+cctype+"</td>"
														+ "<td align='center'>$"+total+"</td></tr></table>";
								
								data += "<br/><table><tr><td align='center' width='400'> <b>Product Name:</b> </td>"
										+ "<td align='center' width='100'> <b>Price:</b> </td><td align='center'> <b>Quantity:</b> </td>"
										+ "<td align='center'> <b>Total:</b> </td></tr>";
								
								for(getCart c:displayCart){
									data += "<tr><td align='center'>" +c.getBrand()+ " " + c.getProdname()+"</td>"
											+ "<td align='center'> $"+c.getPrice()+"</td><td align='center'>"+c.getQuantity()+"</td>"
													+ "<td align='center'>$" + c.getQuantity() * c.getPrice()+"</td></tr>";
									
									msubtotal += c.getQuantity() * c.getPrice();
								}
								
								data += "<br/><b>Voucher Code: "+code.getStringChars()+"</b><br/></table>"
										+ "SUB TOTAL: $"+msubtotal+"<br/><br/>DISCOUNT: $"+getdiscount.getDiscount()+"<br/>"
										+ "</br><b>Total: $"+total+"</b>";
								
								mail.memberpurchase(memDetails.getEmail(), data, memDetails.getName());
								
								for(getCart c:displayCart){
									int finalqty = c.getProdqty() - c.getQuantity();
									boolean removeqty = db.removeqtyfromproduct(c.getProdid(), c.getCatid(), finalqty);
									if(removeqty == false){
										response.sendRedirect("displayCart.jsp?msg=Unable to deduct from stock!");
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
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		
	}

}
