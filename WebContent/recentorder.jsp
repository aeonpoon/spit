<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Model.*, java.util.*, java.sql.*" %>
<!DOCTYPE HTML>
<!--
	Dopetrope by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Welcome to SP IT!</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.dropotron.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		</script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
<body class="right-sidebar">
<!-- Header -->
			<div id="header-wrapper">
				<div id="header">
					
					<!-- Logo -->
						<h1><a href="index.html">SP IT!</a></h1>
					
					<!-- Nav -->
					<%
						registerMember memDetails = (registerMember)session.getAttribute("memDetails");
					
						if(memDetails == null){
					%>
						<nav id="nav">
							<ul>
								<li><a href="index.jsp">Home</a></li>
								<li>
									<a href="">Products</a>
									<ul>
									<%
										try{	
											Class.forName("com.mysql.jdbc.Driver");
											String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
											Connection conn = DriverManager.getConnection(connURL);
											
											String sql = "select * from category";
											PreparedStatement pstmt = conn.prepareStatement(sql);
											
											ResultSet rs = pstmt.executeQuery();
											
											while(rs.next()){
												String catname = rs.getString("catname");
												
												out.println("<li><a href='product.jsp?catname="+catname+"'>"+catname+"</a></li>");
											}
											
											conn.close();
										}catch(Exception e){
											out.println("Error!");
											System.out.println("Error: " + e);
										}
									
									%>
									</ul>
								</li>
								<li><a href="support.jsp">Support</a></li>
								<li><a href="about.jsp">About Us</a></li>
								<li class="current"><a href="displayCart.jsp">Cart</a></li>
								<li><a href="createMember.jsp">Register</a></li>
								<li><a href="login.jsp">Login</a></li>
							</ul>
						</nav>
					<%
						}else{
					%>
							<nav id="nav">
							<ul>
								<li><a href="index.jsp">Home</a></li>
								<li>
									<a href="product.jsp">Products</a>
									<ul>
									<%
									try{	
										Class.forName("com.mysql.jdbc.Driver");
										String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
										Connection conn = DriverManager.getConnection(connURL);
										
										String sql = "select * from category";
										PreparedStatement pstmt = conn.prepareStatement(sql);
										
										ResultSet rs = pstmt.executeQuery();
										
										while(rs.next()){
											String catname = rs.getString("catname");
											
											out.println("<li><a href='product.jsp?catname="+catname+"'>"+catname+"</a></li>");
										}
										
										conn.close();
									}catch(Exception e){
										out.println("Error!");
										System.out.println("Error: " + e);
									}
	
									%>
									</ul>
								</li>
								<li><a href="support.jsp">Support</a></li>
								<li><a href="about.jsp">About Us</a></li>
							</ul>
							</nav>
							<br/>
							<nav id="nav">
								<ul>
									<li><a href="memberpage.jsp">Member Page</a></li>
									<li><a href="displayCartController">Shopping Cart</a></li>
									<li class="current"><a href="recentorderController">Recent Order</a></li>
									<li><a href="updateProfile.jsp">Update profile</a></li>
									<li><a href="coupons.jsp">Coupons</a></li>
									<li><a href="viewcommentsController">View Comments</a></li>
									<li><a href="logout.jsp">Logout</a></li>
								</ul>
							</nav>
					<%
						}
					%>					
				</div>
			</div>
			
			
			<!-- Main -->
			<div id="main-wrapper">
				<div class="container">
				<!-- Content -->
					<div align="center">
					
					<header>
						<h2>Recent Order</h2>
					</header>
				<%
					try{
						
						String msg = request.getParameter("msg");
												
						if(msg != null){
							out.println(msg+"<br/>");
						}
						
						if(memDetails == null){
							response.sendRedirect("login.jsp?msg=Please login!");
						}else{
							int subtotal = 0,total = 0, discount = 0;
							String vcode="";
							
							
							ArrayList<confirmPurchase> getTransactionID = (ArrayList<confirmPurchase>)session.getAttribute("getTransactionID");
							ArrayList<confirmPurchase> displayPurchaseDetails = (ArrayList<confirmPurchase>)session.getAttribute("displayPurchaseDetails");
							ArrayList<confirmPurchase> getTransactionProd = (ArrayList<confirmPurchase>)session.getAttribute("getTransactionProd");
														
							if(getTransactionID.size() != 0){
								
								for(confirmPurchase tid:getTransactionID){
									subtotal = 0;
									total = 0;
									discount = 0;
									
									for(confirmPurchase d:displayPurchaseDetails){
										if(tid.getTransactionID() == d.getTransactionID()){
											out.println("<table>");
											out.println("<tr>");
											out.println("<td align='center' width='150'> <b>Transaction ID:</b> </td>");
											out.println("<td align='center' width='150'> <b>Credit Card Name:</b> </td>");
											out.println("<td align='center' width='150'> <b>Credit Card No.:</b> </td>");
											out.println("<td align='center' width='150'> <b>Credit Card Type:</b> </td>");
											out.println("<td align='center' width='150'> <b>Total:</b> </td>");
											out.println("<td align='center' width='150'> <b>Date Purchased:</b> </td>");
											out.println("</tr>");
											
											out.println("<tr>");
											out.println("<td align='center'>"+d.getTransactionID()+"</td>");
											out.println("<td align='center'>"+d.getCcname()+"</td>");
											out.println("<td align='center'>"+d.getCcnum()+"</td>");
											
											out.println("<td align='center'>"+d.getCctype()+"</td>");
											out.println("<td align='center'>$"+d.getTotalprice()+"</td>");
											out.println("<td align='center'>"+d.getPurchasedate()+"</td>");
											vcode = d.getVcode();
											out.println("</tr>");
											out.println("</table>");
											total = d.getTotalprice();
											
										}
									}
									
									out.println("<table>");
									out.println("<tr>");
									out.println("<td align='center' width='150'> <b>Name:</b> </td>");
									out.println("<td align='center' width='150'> <b>Address:</b> </td>");
									out.println("<td align='center' width='150'> <b>Email:</b> </td>");
									out.println("<td align='center' width='150'> <b>Contact No.:</b> </td>");
									out.println("</tr>");
									
									out.println("<tr>");
									out.println("<td align='center'>"+memDetails.getName()+"</td>");
									out.println("<td align='center'>"+memDetails.getAddress()+"</td>");
									out.println("<td align='center'>"+memDetails.getEmail()+"</td>");
									out.println("<td align='center'>"+memDetails.getContactno()+"</td>");
									out.println("</tr>");
									out.println("</table>");
									
									out.println("<table>");
									out.println("<tr>");
									out.println("<td align='center' width='400'> <b>Product Name:</b> </td>");
									out.println("<td align='center' width='100'> <b>Unit Price:</b> </td>");
									out.println("<td align='center'> <b>Quantity:</b> </td>");
									out.println("<td align='center'> <b>Total:</b> </td>");
									out.println("</tr>");
									
									for(confirmPurchase dp:getTransactionProd){
										if(tid.getTransactionID() == dp.getTransactionID()){
											out.println("<tr>");
											out.println("<td align='center'>" +dp.getBrand()+ " " + dp.getProdname()+"</td>");
											out.println("<td align='center'> $"+dp.getUnitprice()+"</td>");
											
											out.println("<td align='center'>"+dp.getQuantity()+"</td>");
											
											out.println("<td align='center'>$"+dp.getSubtotal()+"</td>");
											
											out.println("</tr>");
											subtotal += dp.getSubtotal();
										}
									}
									
									out.println("</table>");
									discount = subtotal - total;
									
									out.println("<b>Voucher Code: "+vcode+"</b>");
									out.println("<br/><b>DISCOUNT: $"+discount+"</b>");
									out.println("<br/><br/><br/><br/>");
								}
								
								out.println("<br/><br/>");
								
								out.println("<form action='memberpage.jsp' method='post'>");
								out.println("<input type='submit' name='btnBack' value='Back to main'/>");
								out.println("</form>");
								
							}else{
								out.println("Your Recent Order is currently empty!");
							}
						}
						
						
					}catch(Exception e){
						out.println("Error!");
						System.out.println("Error: " + e);
					}
				
				
				
				%>
					</div>
				</div>
			</div>

		<!-- Footer -->
			<div id="footer-wrapper">
				<section id="footer" class="container">
					<div class="row">
						<div class="8u">
							<section>
								<header>
									<h2>News Update</h2>
								</header>
								<ul class="dates">
									<li>
										<span class="date">April <strong>29</strong></span>
										<h3><a href="#">Launch of SP IT!</a></h3>
										<p>Offical launch of SP IT! E-Store. Click here for more news.</p>
									</li>
									<li>
										<span class="date">May <strong>2</strong></span>
										<h3><a href="#">Newly listed product</a></h3>
										<p>We are happy to anounnce that we have added product into the e-store. 
										Click here to explore more</p>
									</li>
									<li>
										<span class="date">May <strong>9</strong></span>
										<h3><a href="#">Now you can add in reviews about the product</a></h3>
										<p>Review them so other could know the usage</p>
									</li>
								</ul>
							</section>
						</div>
						<div class="4u">
							<section>
								<header>
									<h2>Social Media</h2>
								</header>
								<ul class="social">
									<li><a class="icon fa-facebook" href="#"><span class="label">Facebook</span></a></li>
									<li><a class="icon fa-twitter" href="#"><span class="label">Twitter</span></a></li>
								</ul>
								<ul class="contact">
									<li>
										<h3>Address</h3>
										<p>
											Singapore Polytechnic<br/>
											500 Dover Road<br/>
											Singapore 139651
										</p>
									</li>
									<li>
										<h3>Mail</h3>
										<p><a href="mailto:contactus@sp.edu.sg">contactus@sp.edu.sg</a></p>
									</li>
									<li>
										<h3>Phone</h3>
										<p>67751133</p>
									</li>
								</ul>
							</section>
						</div>
					</div>
					<div class="row">
						<div class="12u">
						
							<!-- Copyright -->
								<div id="copyright">
									<ul class="links">
										<li>&copy; SP IT! 2015. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
									</ul>
								</div>

						</div>
					</div>
				</section>
			</div>

	</body>
</html>















	

		
		
		
						
						
</html>