<%@ page import="java.sql.*" %>
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
						<h1><a href="index.html">SP IT!</a></h1>
					
					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li class="current"><a href="adminpage.jsp">Admin Page</a></li>
								<li><a href="insertproduct.jsp">Insert Product</a></li>
								<li><a href="insertcategory.jsp">Insert Category</a></li>
								<li><a href="viewcategory.jsp">View Category</a></li>
								<li><a href="searchproduct.jsp">Search Product</a></li>
								<li><a href="viewcomments.jsp">View Comments</a></li>
								<li><a href="login.jsp">Logout</a></li>
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
							<h2>Search Engine</h2>
						</header>
							<%
								String search = request.getParameter("search");
							%>
							<form action="processSearch.jsp" method="get">
								Search products/brand/price/spec: <input type="text" name="search" value="<%=search%>"/>
								<br/>
								<input type="submit" name="btnSubmit" value="Search"/>
							</form>
							<br/><br/>
							<%
								try{
									String brand = request.getParameter("search");
									String prodname = request.getParameter("search");
									String spec = request.getParameter("search");
									String price = request.getParameter("search");
									
									Class.forName("com.mysql.jdbc.Driver");
									String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
									Connection conn = DriverManager.getConnection(connURL);
														
									String sql = "select * from product where brand like ? or prodname like ? or spec like ? or price like ? order by prodname, brand, price";
									PreparedStatement pstmt = conn.prepareStatement(sql);
									
									pstmt.setString(1, "%"+brand+"%");
									pstmt.setString(2, "%"+prodname+"%");
									pstmt.setString(3, "%"+spec+"%");
									pstmt.setString(4, "%"+price+"%");
									
									ResultSet rs = pstmt.executeQuery();
													
									out.println("<table border='1'>");
									out.println("<tr>");
									out.println("<td align='center'> <b>Product Name</b> </td>");
									out.println("<td align='center'> <b>Brand</b> </td>");
									out.println("<td align='center'> <b>Spec</b> </td>");
									out.println("<td align='center'> <b>Price</b> </td>");
									out.println("<td align='center'> <b>Image Path</b> </td>");
									out.println("</tr>");
									
									if(!rs.next()){
										out.println("No record found<br/><br/>");
									}else{
										do{
											prodname = rs.getString("prodname");
											brand = rs.getString("brand");
											spec = rs.getString("spec");
											price = rs.getString("price");
											String imgpath = rs.getString("imgpath");
																	
											out.println("<tr>");
											out.println("<td align='center'>" + prodname + "</td>");
											out.println("<td align='center'>" + brand + "</td>");
											out.println("<td width='150'>" + spec + "</td>");
											out.println("<td align='center'>$ " + price + "</td>");
											out.println("<td align='center'>" + imgpath + "</td>");
																	
											out.println("</tr>");
										}while(rs.next());
									}
									
									out.println("</table><br/>");
									out.println("<form action='adminpage.jsp' method='post'>");
									out.println("<input type='submit' name='btnSubmit' value='Back to main'/>");
									out.println("</form>");
									conn.close();
															
								}catch(Exception e){
									out.println("Error: " + e);
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