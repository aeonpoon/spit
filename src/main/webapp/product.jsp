<%@ page import="java.sql.*, product.*, Model.*"  %>
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
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
		<script src="js/jquery.dropotron.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
		<!-- Nivoslider -->
		<link rel="stylesheet" type="text/css" href="style-nivo.css" />
		<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
		<script type="text/javascript" src="jquery.nivo.slider.pack.js"></script>
		<script type="text/javascript">
		$(window).load(function() {
			$('#slider').nivoSlider({animSpeed: 300,pauseTime:4500,effect:'random'});
		});
		</script>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
	<body class="right-sidebar">

		<!-- Header -->
			<div id="header-wrapper">
				<div id="header">
					
					<!-- Logo -->
						<h1><a href="index.jsp">SP IT!</a></h1>
						
					<!-- Nav -->
						<!-- Nav -->
						<%
						registerMember memDetails = (registerMember)session.getAttribute("memDetails");
					
						if(memDetails == null){
					%>
						<nav id="nav">
							<ul>
								<li><a href="index.jsp">Home</a></li>
								<li class="current">
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
								<li class="current">
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
								<li><a href="viewcommentsController">View Comments</a></li>
								<li><a href="logoutController">Logout</a></li>
							</ul>
						</nav>
					<%
						}
					%>
					<!-- Banner -->
						<div id="banner">
							<div id="slider" class="nivoSlider">
								<img src="images/logo_banner.jpg" alt="logo" />
								<a href="audio.html"><img src="images/adv_banner_audio.jpg" alt="audio" /></a>
								<a href="cellphones.html"><img src="images/adv_banner_cellphones.jpg" alt="cellphones" /></a>
								<a href="keymouse.html"><img src="images/adv_banner_keyMouse.jpg" alt="keymouse" /></a>
								<a href="internalcom.html"><img src="images/adv_banner_internalCom.jpg" alt="internalcom" /></a>
								<a href="laptop.html"><img src="images/adv_banner_laptop.jpg" alt="laptop" /></a>
								<a href="memory.html"><img src="images/adv_banner_memory.jpg" alt="memory" /></a>
								<a href="monitor.html"><img src="images/adv_banner_monitor.jpg" alt="monitor" /></a>
								<a href="tv.html"><img src="images/adv_banner_TV.jpg" alt="tv" /></a>
							</div>
						</div>			
				</div>
			</div>
		
		<!-- Main -->
			<div id="main-wrapper">
				<div class="container">
				<%
					String catname = request.getParameter("catname");
				
					String msg = request.getParameter("msg");
					if(msg != null){
						out.println("<div align='center'><h2><u>"+msg+"</u></h2></div>");
					}
				
					if(catname == null){
						catname = "Products";
						
				%>
				<h1 class="productHeader"><%=catname %></h1>
					<div class="row">
							<!-- Content -->
				<%	
					try{
					Class.forName("com.mysql.jdbc.Driver");
					String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
					Connection conn = DriverManager.getConnection(connURL);
					
					String sql = "select * from category C";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()){
						String catnameall = rs.getString("catname");
						String imgpath = rs.getString("imgpath");
						
						out.println("<div class='4u important(collapse)'>");
						out.println("<section class='box'>");
						out.println("<a href='product.jsp?catname="+catnameall+"' class='image featured'><img src="+imgpath+" alt='' /></a>");
						out.println("<header align='center'><h3>"+catnameall+"</h3></header>");
						out.println("</section>");		
						out.println("</div>");	
					}
					
						conn.close();
					}catch(Exception e){
						out.println("Error!");
						System.out.println("Error: " + e);
					}
				}
				%>
				
					<div class="row">
							<!-- Content -->
								<%
									try{
										Class.forName("com.mysql.jdbc.Driver");
										String connURL = "jdbc:mysql://localhost/spit?user=root&password=abc123";
										Connection conn = DriverManager.getConnection(connURL);
										
										String sql = "select * from product P, category C where P.catid = C.catid and catname=?";
										PreparedStatement pstmt = conn.prepareStatement(sql);
										
										pstmt.setString(1, catname);
										
										ResultSet rs = pstmt.executeQuery();
										
										while(rs.next()){
											int prodid = rs.getInt("prodid");
											String brand = rs.getString("brand");
											String prodname = rs.getString("prodname");
											String des = rs.getString("description");
											String catnameind = rs.getString("catname");
											int catid = rs.getInt("catid");	
	
											String[] spec = {"spec1", "spec2", "spec3", "spec4"};
											for(int i=0; i<4; i++){
												spec[i] = rs.getString(spec[i]);
											}
											
											int price = rs.getInt("price");
											String imgpath = rs.getString("imgpath");
											
											out.println("<div class='4u important(collapse)'>");
											out.println("<section class='box'>");
											out.println("<a class='image featured'><img src="+imgpath+" alt='' /></a>");
											out.println("<header><h3>"+brand+ " " +prodname+"</h3></header>");
											out.println("<ul class='divided'>");
											out.println("<li>"+des+"</li>");
											
											for(int i=0; i<4; i++){
												out.println("<li>"+spec[i]+"</li>");
											}
											
											out.println("<li>$ "+price+"</li>");
											out.println("</ul>");
											viewProduct vp = new viewProduct(brand, prodname, price, imgpath);
											session.setAttribute("inprod", vp);
											out.println("<footer><a href='indProductPage.jsp?prodid="+ prodid +"&catname="+ catnameind +"&catid="+ catid +"' class='button alt'>View</a></footer>");
											out.println("</section>");		
											out.println("</div>");	
										}
										
										conn.close();
									}catch(Exception e){
										out.println("Error!");
										System.out.println("Error: " + e);
									}
									
								%>		
					</div>
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
										<p>Dolore consequat sed phasellus lorem sed etiam nullam dolor etiam sed amet sit consequat.</p>
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