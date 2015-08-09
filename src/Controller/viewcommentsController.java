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
 * Servlet implementation class viewcommets
 */
@WebServlet("/viewcommentsController")
public class viewcommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewcommentsController() {
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
				commentDB db = new commentDB();
				
				ArrayList<comment> getcomment = db.viewComment(memDetails.getEmail());
				
				session.setAttribute("getcomment", getcomment);
				response.sendRedirect("memviewcomments.jsp");
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
