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
 * Servlet implementation class salesreportController
 */
@WebServlet("/salesreportController")
public class salesreportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public salesreportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String reptype = request.getParameter("reptype");
		
		salesreportDB db = new salesreportDB();
		
		String status = (String)session.getAttribute("status");
		
		if(status != "verifyAdmin"){
			response.sendRedirect("login.jsp?msg=Please Login!");
		}else{
			
			if(reptype.equals("mtmonth")){
				String month = request.getParameter("mtsmonth");
				String year = request.getParameter("mtyear");
				
				if(month.equals("Month") || year.equals("Year")){
					response.sendRedirect("salesreport.jsp?msg=Please select month/year for monthly report!");
				}else{
					ArrayList<salesreport> getmonthreport = db.getMonthlyreport(Integer.parseInt(month), Integer.parseInt(year));
					
					if(getmonthreport.size() != 0){
						session.setAttribute("getmonthreport", getmonthreport);
						response.sendRedirect("viewsalesreport.jsp?msg=Select successful!");
					}else{
						response.sendRedirect("viewsalesreport.jsp?msg=No sales on this month!");
					}
				}
			}else{
				String year = request.getParameter("ytyear");
				
				if(year.equals("Year")){
					response.sendRedirect("salesreport.jsp?msg=Please select year for yearly report!");
				}else{
					ArrayList<salesreport> getyearreport = db.getYearlyreport(Integer.parseInt(year));
					
					if(getyearreport.size() != 0){
						
						session.setAttribute("getyearreport", getyearreport);
						response.sendRedirect("viewsalesreport.jsp?msg=Select successful!");
					}else{
						response.sendRedirect("viewsalesreport.jsp?msg=No sales on this year!");
					}
					
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
