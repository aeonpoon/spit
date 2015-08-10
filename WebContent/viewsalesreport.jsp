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
	<body class="right-sidebar">

		<!-- Header -->
			<div id="header-wrapper">
				<div id="header">
					
					<!-- Logo -->
						<h1><a>SP IT!</a></h1>
					
					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li><a href="adminpage.jsp">Admin Page</a></li>
								<li><a href="insertproduct.jsp">Insert Product</a></li>
								<li><a href="insertcategory.jsp">Insert Category</a></li>
								<li><a href="viewcategory.jsp">View Category</a></li>
								<li class="current"><a href="salesreport.jsp">Sales Report</a></li>
								<li><a href="searchproduct.jsp">Search Product</a></li>
								<li><a href="viewcomments.jsp">View Comments</a></li>
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
						<%
						String status = (String)session.getAttribute("status");
						
							if(status != "verifyAdmin"){
								response.sendRedirect("login.jsp?msg=Please Login!");
							}else{
								Login loginid = (Login)session.getAttribute("admDetails");
								ArrayList<salesreport> getmonthreport = (ArrayList<salesreport>)session.getAttribute("getmonthreport");
								ArrayList<salesreport> getyearreport = (ArrayList<salesreport>)session.getAttribute("getyearreport");
								String msg = request.getParameter("msg");
						%>
						<header>
						<%
							if(getmonthreport != null){
								out.println("<h2>Monthly Report</h2>");
							}else if(getyearreport != null){
								out.println("<h2>Yearly Report</h2>");
							}else{
								out.println("<h2>Sales Report</h2>");
							}
						%>
						
						</header>
						<%
							if(msg != null){
								out.println(msg);
							}
						
							out.println("<br/><br/>");
						
							if(getmonthreport != null){
								out.println("<table>");
								out.println("<tr>");
								out.println("<td width='100'></td>");
								out.println("<td width='100'><b>Product Name</b></td>");
								out.println("<td width='150'><b>Quantity Sold</b></td>");
								out.println("</tr>");
								for(salesreport rp:getmonthreport){
									out.println("<tr>");
									out.println("<td></td>");
										out.println("<td>"+rp.getProdname()+"</td>");
										out.println("<td>"+rp.getQuantity()+"</td>");
									out.println("</tr>");
									
								}
								out.println("</table>");
							}else if(getyearreport != null){
								out.println("<table>");
								out.println("<td width='100'></td>");
								out.println("<td width='100'><b>Product Name</b></td>");
								out.println("<td width='150'><b>Quantity Sold</b></td>");
								out.println("</tr>");
								for(salesreport rp:getyearreport){
									out.println("<tr>");
										out.println("<td></td>");
										out.println("<td>"+rp.getProdname()+"</td>");
										out.println("<td>"+rp.getQuantity()+"</td>");
									out.println("</tr>");
									
								}
								out.println("</table>");
							}
							
							if(getmonthreport != null || getyearreport != null){
								out.println("<form action='printreportController' method='post'>");
								out.println("<input type='submit' value='Send Report'/>");
								out.println("</form>");
							}
							
							out.println("<form action='salesreport.jsp' method='get'>");
							out.println("<br/>");
							out.println("<input type='submit' value='Back'/>");
							out.println("</form>");
							
							
						%>
						
					</div>
						<%
							}
						%>
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