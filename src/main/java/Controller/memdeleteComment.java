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
 * Servlet implementation class memdeleteComment
 */
@WebServlet("/memdeleteComment")
public class memdeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memdeleteComment() {
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
			String delId = request.getParameter("delId");
			
			commentDB db = new commentDB();
			boolean success = db.delComment(memDetails.getEmail(), Integer.parseInt(delId));
			
			if(success != false){
				response.sendRedirect("memviewcomments.jsp?msg=Delete Successful!");
			}else{
				response.sendRedirect("memviewcomments.jsp?msg=Delete Failed!");
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

}
