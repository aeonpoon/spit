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
						<h1><a>SP IT!</a></h1>
					
					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li><a href="adminpage.jsp">Admin Page</a></li>
								<li><a href="insertproduct.jsp">Insert Product</a></li>
								<li><a href="insertcategory.jsp">Insert Category</a></li>
								<li><a href="viewcategory.jsp">View Category</a></li>
								<li class="current"><a href="searchproduct.jsp">Search Product</a></li>
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
						<header>
							<h2>Search Engine</h2>
						</header>
							<%
								String search = request.getParameter("search");
							%>
							<form action="delupproduct.jsp" method="get">
								<b>Search Products/Category/Brand/Price/Spec:</b> <input type="text" name="search" value="<%=search%>"/>
								<br/>
								<input type="submit" name="btnSearch" value="Search"/>
							</form>
							<br/><br/>
							<%
								try{
									String catname = request.getParameter("search");
									String brand = request.getParameter("search");
									String prodname = request.getParameter("search");
									String spec1 = request.getParameter("search");
									String spec2 = request.getParameter("search");
									String spec3 = request.getParameter("search");
									String spec4 = request.getParameter("search");
									String price = request.getParameter("search");
									
									Class.forName("com.mysql.jdbc.Driver");
									String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
									Connection conn = DriverManager.getConnection(connURL);
														
									String sql = "select * from product P, category C where P.catid = C.catid and (catname like ? or brand like ? or prodname like ? or spec1 like ? or "+
									"spec2 like ? or spec3 like ? or spec4 like ? or price like ?) order by catname, prodname, brand, price";
									PreparedStatement pstmt = conn.prepareStatement(sql);
									
									pstmt.setString(1, "%"+catname+"%");
									pstmt.setString(2, "%"+brand+"%");
									pstmt.setString(3, "%"+prodname+"%");
									pstmt.setString(4, "%"+spec1+"%");
									pstmt.setString(5, "%"+spec2+"%");
									pstmt.setString(6, "%"+spec3+"%");
									pstmt.setString(7, "%"+spec4+"%");
									pstmt.setString(8, "%"+price+"%");
									
									ResultSet rs = pstmt.executeQuery();
													
									out.println("<table border='1'>");
									out.println("<tr>");
									out.println("<td align='center'> <b>Category</b> </td>");
									out.println("<td align='center'> <b>Product Name</b> </td>");
									out.println("<td align='center'> <b>Brand</b> </td>");
									out.println("<td align='center'> <b>Spec</b> </td>");
									out.println("<td align='center'> <b>Price</b> </td>");
									out.println("<td align='center'> <b>Image Path</b> </td>");
									out.println("<td align='center' colspan='2'> <b>Actions</b> </td>");
									out.println("</tr>");
									
									if(!rs.next()){
										out.println("No record found<br/><br/>");
									}else{
										do{
											int prodid = rs.getInt("prodid");
											int upcatId = rs.getInt("catid");
											catname = rs.getString("catname");
											prodname = rs.getString("prodname");
											brand = rs.getString("brand");
											spec1 = rs.getString("spec1");
											spec2 = rs.getString("spec2");
											spec3 = rs.getString("spec3");
											spec4 = rs.getString("spec4");
											price = rs.getString("price");
											String imgpath = rs.getString("imgpath");
																	
											out.println("<tr>");
											out.println("<td align='center' width='100'>" + catname + "</td>");
											out.println("<td align='center' width='100'>" + prodname + "</td>");
											out.println("<td align='center' width='100'>" + brand + "</td>");
											out.println("<td align='center' width='250'>" + spec1 + ", " +spec2 + ", " +spec3 + ", " +spec4 +"</td>");
											out.println("<td align='center' width='80'>$ " + price + "</td>");
											out.println("<td align='center'>" + imgpath + "</td>");
											
											out.println("<td width='50'>");
											out.println("<form action='deleteProduct.jsp' method='get'>");
											out.println("<input type='hidden' name='delId' value='"+prodid+"'/>");
											out.println("<input type='hidden' name='search' value='"+search+"'/>");
											out.println("<input type='submit' value='Delete'/>");
											out.println("</form>");
											out.println("</td>");
											
											out.println("<td width='50'>");
											out.println("<form action='updateProduct.jsp' method='get'>");
											out.println("<input type='hidden' name='upId' value='"+prodid+"'/>");
											out.println("<input type='hidden' name='upcatId' value='"+upcatId+"'/>");
											out.println("<input type='hidden' name='search' value='"+search+"'/>");
											out.println("<input type='submit' value='Update'/>");
											out.println("</form>");
											out.println("</td>");
											
											out.println("</tr>");
										}while(rs.next());
									}
									
									out.println("</table><br/>");
									out.println("<form action='adminpage.jsp' method='post'>");
									out.println("<input type='submit' name='btnBack' value='Back to main'/>");
									out.println("</form>");
									conn.close();
															
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