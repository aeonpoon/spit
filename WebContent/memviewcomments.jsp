<%@ page import="java.sql.*, Model.*, java.util.*" %>
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
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
	<body class="homepage">

		<!-- Header -->
			<div id="header-wrapper">
				<div id="header">
					
					<!-- Logo -->
						<h1><a>SP IT!</a></h1>
					
					<!-- Nav -->
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
									<li><a href="recentorderController">Recent Order</a></li>
									<li><a href="updateProfile.jsp">Update profile</a></li>
									<li><a href="coupons.jsp">Coupons</a></li>
									<li class="current"><a href="viewcommentsController">View Comments</a></li>
									<li><a href="logoutController">Logout</a></li>
								</ul>
							</nav>										
				</div>
			</div>
		
		<!-- Main -->
			<div id="main-wrapper">
				<div class="container">
				<!-- Content -->
					<div align="center">
						<header>
							<h2>Comments</h2>
						</header>
							<%
							String msg = request.getParameter("msg");
							registerMember memDetails = (registerMember)session.getAttribute("memDetails");
							
							if(msg != null){
								out.println(msg+"<br/>");
							}
							
							if(memDetails == null){
								response.sendRedirect("login.jsp?msg=Please login!");
							}else{
								try{
									ArrayList<comment> getcomment = (ArrayList<comment>)session.getAttribute("getcomment");
									
									if(getcomment.size() != 0){
										out.println("<table border='1'>");
										out.println("<tr>");
										out.println("<td align='center'> <b>Category</b> </td>");
										out.println("<td align='center'> <b>Product Name</b> </td>");
										out.println("<td align='center'> <b>Comment</b> </td>");
										out.println("<td align='center'> <b>Ratings</b> </td>");
										out.println("<td align='center'> <b>Actions</b> </td>");
										out.println("</tr>");
										
										for(comment c:getcomment){
											out.println("<tr>");
											out.println("<td align='center'>" + c.getCatname() + "</td>");
											out.println("<td align='center'>" + c.getBrand() + " " +c.getProdname() + "</td>");
											out.println("<td align='center'>" + c.getComment() + "</td>");
											out.println("<td align='center'>" + c.getRatings() + "</td>");
											
											out.println("<td width='50'>");
											out.println("<form action='memdeleteComment' method='post'>");
											out.println("<input type='hidden' name='delId' value='"+c.getId()+"'/>");
											out.println("<input type='submit' value='Delete'/>");
											out.println("</form>");
											out.println("</td>");
											out.println("</tr>");
										}
										
										out.println("</table><br/>");
									}else{
										out.println("No record found<br/><br/>");
									}
								}catch(Exception e){
									out.println("Error!");
									System.out.println("Error: " + e);
								}
							
							%>
							<br/><br/>
							<%
								out.println("<form action='memberpage.jsp' method='post'>");
								out.println("<input type='submit' name='btnSubmit' value='Back to main'/>");
								out.println("</form>");
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