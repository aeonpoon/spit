package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

import Model.*;
import mail.*;

/**
 * Servlet implementation class printreportController
 */
@WebServlet("/printreportController")
public class printreportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public printreportController() {
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
		HttpSession session = request.getSession();
		
		ArrayList<salesreport> getmonthreport = (ArrayList<salesreport>)session.getAttribute("getmonthreport");
		ArrayList<salesreport> getyearreport = (ArrayList<salesreport>)session.getAttribute("getyearreport");
		
		String status = (String)session.getAttribute("status");
		SendMail mail = new SendMail();
		
		if(status != "verifyAdmin"){
			response.sendRedirect("login.jsp?msg=Please Login!");
		}else{
			if(getmonthreport != null){
				String data="<table><tr><td width='100'></td><td width='100'><b>Product Name</b></td>"
						+ "<td width='150'><b>Quantity Sold</b></td></tr>";
								
				for(salesreport rp:getmonthreport){
					data += "<tr><td></td>"+rp.getProdname()+"</td><td>"+rp.getQuantity()+"</td></tr>";
				}
				
				data += "</table>";
				
				boolean sendReport = mail.sendMonthlyReport(data);
				if(sendReport != false){
					response.sendRedirect("salesreport.jsp?msg=Report send successful!");
				}else{
					response.sendRedirect("salesreport.jsp?msg=Report fail to send!");
				}
			}else if(getyearreport != null){
				String data="<table><tr><td width='100'></td><td width='100'><b>Product Name</b></td>"
						+ "<td width='150'><b>Quantity Sold</b></td></tr>";
								
				for(salesreport rp:getyearreport){
					data += "<tr><td></td>"+rp.getProdname()+"</td><td>"+rp.getQuantity()+"</td></tr>";
				}
				
				data += "</table>";
				
				boolean sendReport = mail.sendYearlyReport(data);
				if(sendReport != false){
					response.sendRedirect("salesreport.jsp?msg=Report send successful!");
				}else{
					response.sendRedirect("salesreport.jsp?msg=Report fail to send!");
				}
			}
		}
	}

}
