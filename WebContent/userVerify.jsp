<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, Model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to SP IT!</title>
</head>
<body>
<%
	try{
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
		Connection conn = DriverManager.getConnection(connURL);
		
		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String status = "";
		
		if(role.equals("member")){
			String sql = "select name from member where email=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			pstmt.setString(1, loginid);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				String name = rs.getString("name");
				status = "verifyMem";
				session.setAttribute("status", status);
				Login memdetails = new Login(name, password); 
				session.setAttribute("userDetails", memdetails);
				response.sendRedirect("memberpage.jsp");
			}else{
				response.sendRedirect("login.jsp?msg=Userid/Password incorrect");
			}
		}else{
			String sql = "select * from login where loginid=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			Login admdetails = new Login(loginid, password); 
			
			pstmt.setString(1, loginid);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				status = "verifyAdmin";
				session.setAttribute("status", status);
				session.setAttribute("userDetails", admdetails);
				response.sendRedirect("adminpage.jsp");
			}else{
				response.sendRedirect("login.jsp?msg=Userid/Password incorrect");
			}
		}
		
	}catch(Exception e){
		out.println("Error: " + e);
	}
%>
</body>
</html>