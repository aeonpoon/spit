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
						<%
							String upId = request.getParameter("upId");
							int upcatId = Integer.parseInt(request.getParameter("upcatId")), catid = 0;
							String search = request.getParameter("search");
							String brand = "", prodname="", des="", spec1="", spec2="", spec3="", spec4="", 
											price="", imgpath="";
						
							try{	
								Class.forName("com.mysql.jdbc.Driver");
								String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
								Connection conn = DriverManager.getConnection(connURL);
								
								String sql = "select * from product P, category C where P.catid = C.catid and prodid=?";
								PreparedStatement pstmt = conn.prepareStatement(sql);
																
								pstmt.setInt(1, Integer.parseInt(upId));
								
								ResultSet rs = pstmt.executeQuery();
								
								while(rs.next()){
									brand = rs.getString("brand");
									prodname = rs.getString("prodname");
									des = rs.getString("description");
									spec1 = rs.getString("spec1");
									spec2 = rs.getString("spec2");
									spec3 = rs.getString("spec3");
									spec4 = rs.getString("spec4");
									price = rs.getString("price");
									imgpath = rs.getString("imgpath");
								}
								conn.close();
							}catch(Exception e){
								out.println("Error!");
								System.out.println("Error: " + e);
							}
								
						%>
						<header>
							<h2>Update Product</h2>
						</header>
						<form action="processupProduct.jsp" method="post">
							<table>
								<tr>
									<td>
										<b>Category:</b>
									</td>
									<td>
										<%
											try{
												Class.forName("com.mysql.jdbc.Driver");
												String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
												Connection conn = DriverManager.getConnection(connURL);
												
												String sql = "select * from category";
												PreparedStatement pstmt = conn.prepareStatement(sql);
												
												ResultSet rs = pstmt.executeQuery();
												
												out.println("<select name='catid' >");
												out.println("<option value=''>Select Category</option>");
												
												while(rs.next()){
													catid = rs.getInt("catid");
													String catname = rs.getString("catname");
													
													if(upcatId == catid){
														out.println("<option selected='selected' value='"+catid+"'>"+catname);
													}else{
														out.println("<option value='"+catid+"'>"+catname);
													}
													
													out.println("</option>");
												}
												
												out.println("</select>");
												conn.close();
											}catch(Exception e){
												out.println("Error!");
												System.out.println("Error: " + e);
											}
											
										%>
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Brand:</b>
									</td>
									<td>
										<input type="text" name="brand" value="<%=brand%>" />
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Product Name:</b>
									</td>
									<td>
										<input type="text" name="prodname" value="<%=prodname%>" />
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Description:</b>
									</td>
									<td>
										<input type="text" name="des" value="<%=des%>" />
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Specification Info 1:</b>
									</td>
									<td>
										<input type="text" name="spec1" value="<%=spec1%>" />
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Specification Info 2:</b>
									</td>
									<td>
										<input type="text" name="spec2" value="<%=spec2%>" />
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Specification Info 3:</b>
									</td>
									<td>
										<input type="text" name="spec3" value="<%=spec3%>" />
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Specification Info 4:</b>
									</td>
									<td>
										<input type="text" name="spec4" value="<%=spec4%>" />
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Price:</b>
									</td>
									<td>
										<input type="text" name="price" value="<%=price%>" />
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Image Path:</b>
									</td>
									<td>
										<input type="text" name="imgpath" value="<%=imgpath%>" /><br/>
									</td>
								</tr>
								
								<tr>
									<td>
										
									</td>
									
									<td>
										<input type="submit" name="btnUpdate" value="Update" />
										<input type="hidden" name="upId" value="<%=upId%>" />
										<input type="hidden" name="catid" value="<%=catid%>" />
										<input type="hidden" name="search" value="<%=search%>" />
										<input type="reset" name="btnReset" value="Reset" />
									</td>
								</tr>
							</table>
						</form>
						<%	
							out.println("<form action='delupproduct.jsp?search="+search+"' method='post'>");
							out.println("<input type='submit' name='btnSubmit' value='Back'/>");
							out.println("</form>");	
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