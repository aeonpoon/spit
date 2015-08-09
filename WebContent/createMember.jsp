<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, Model.*" %>
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
		<script src="checkRegister.js"></script>
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
										out.println("Error: " + e);
									}
	
									%>
									</ul>
								</li>
								<li><a href="support.jsp">Support</a></li>
								<li><a href="about.jsp">About Us</a></li>
								<li class="current"><a href="createMember.jsp">Register</a></li>
								<li><a href="login.jsp">Login</a></li>
							</ul>
						</nav>					
				</div>
			</div>
		
		<!-- Main -->
			<div id="main-wrapper">
				<div class="container">
				<!-- Content -->
					<div id="msg" align="center">
					<%
						String msg = request.getParameter("msg");
						registerMember reg = (registerMember)session.getAttribute("details");
						if(msg != null){
							out.println("<u>"+msg+"</u>");
						}
					%>
					</div>
					<div align="center">
						<header >
							<h2>Register Account</h2>
						</header>
					</div>
					<%
						if(reg == null){
					%>
						<form action="RegisterController" method="post" onsubmit="return checkRegister()" >
							<table>
								<tr>
									<td width="340">
										<b>Email:</b>
									</td>
									<td>
										<input type="email" id="emailinput" name="email" placeholder="Enter email..." />
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td width="340">
										<b>Password:</b>
									</td>
									<td>
										<input type="password" id="passwordinput" name="password" 
																			placeholder="Enter password..." value="" />
									</td>
									<td>
										<input type="button" id="check" name="btnCheck" value="Check" onclick="return passwordCheck()" />
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td width="340">
										<b>Name:</b>
									</td>
									<td>
										<input type="text" id="nameinput" name="name" placeholder="Enter name..." /><br/>
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Gender:</b>
									</td>
									<td>
										<input type="radio" id="male" name="gender" value="male" /> Male
										<input type="radio" id="female" name="gender" value="female" /> Female<br/>
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td>
										<b>Date of Birth:</b>
									</td>
									<td>
										<select id="dobday" name="dobday">
											<option selected='selected'>Day</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
											<option>6</option>
											<option>7</option>
											<option>8</option>
											<option>9</option>
											<option>10</option>
											<option>11</option>
											<option>12</option>
											<option>13</option>
											<option>14</option>
											<option>15</option>
											<option>16</option>
											<option>17</option>
											<option>18</option>
											<option>19</option>
											<option>20</option>
											<option>21</option>
											<option>22</option>
											<option>23</option>
											<option>24</option>
											<option>25</option>
											<option>26</option>
											<option>27</option>
											<option>28</option>
											<option>29</option>
											<option>30</option>
											<option>31</option>
										</select>
									</td>
									
									<td width="330">
										<select id="dobmonth" name="dobmonth">
											<option selected='selected'>Month</option>
											<option>January</option>
											<option>Febuary</option>
											<option>March</option>
											<option>April</option>
											<option>May</option>
											<option>June</option>
											<option>July</option>
											<option>August</option>
											<option>September</option>
											<option>October</option>
											<option>November</option>
											<option>December</option>
										</select>
									</td>
									
									<td>
										 <select id="dobyear" name="dobyear">
											<option selected='selected'>Year</option>
											<option>2011</option>
											<option>2010</option>
											<option>2009</option>
											<option>2008</option>
											<option>2007</option>
											<option>2006</option>
											<option>2005</option>
											<option>2004</option>
											<option>2003</option>
											<option>2002</option>
											<option>2001</option>
											<option>2000</option>
											<option>1999</option>
											<option>1998</option>
											<option>1997</option>
											<option>1996</option>
											<option>1995</option>
											<option>1994</option>
											<option>1993</option>
											<option>1992</option>
											<option>1991</option>
											<option>1990</option>
											<option>1989</option>
											<option>1988</option>
											<option>1987</option>
											<option>1986</option>
											<option>1985</option>
											<option>1984</option>
											<option>1983</option>
											<option>1982</option>
											<option>1981</option>
											<option>1980</option>
											<option>1979</option>
											<option>1978</option>
											<option>1977</option>
											<option>1976</option>
											<option>1975</option>
											<option>1974</option>
											<option>1973</option>
											<option>1972</option>
											<option>1971</option>
											<option>1970</option>
											<option>1969</option>
											<option>1968</option>
											<option>1967</option>
											<option>1966</option>
											<option>1965</option>
											<option>1964</option>
											<option>1963</option>
											<option>1962</option>
											<option>1961</option>
											<option>1960</option>
											<option>1959</option>
										</select>
									</td>
								</tr>
							</table>	
							
							<table>
								<tr>
									<td>
										<b>Mailing Address:</b>
									</td>
									<td width="860">
										<input type="text" id="addressinput" name="address" placeholder="Enter address..." /><br/>
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Contact No.:</b>
									</td>
									<td>
										<input type="text" id="contactinput" name="contactno" placeholder="Enter contact no..." /><br/>
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Subscribe Newsletter:</b>
									</td>
									<td>
										<input type="radio" id="yes" name="subscribe" value="yes" /> Yes
										<input type="radio" id="no" name="subscribe" value="no" /> No<br/><br/>
									</td>
								</tr>
							
								<tr>
									<td>
									
									</td>
									<td>
										<input type="submit" id="create" name="btnCreate" value="Create" />
										<input type="reset" name="btnReset" value="Reset" />
									</td>
								</tr>							
							</table>
						</form>
					<%
						}else{
					%>
						<form action="RegisterController" method="post" onsubmit="return checkRegister()" >
							<table>
								<tr>
									<td width="340">
										<b>Email:</b>
									</td>
									<td>
										<input type="email" id="emailinput" name="email" placeholder="Enter email..." value="<%out.println(reg.getEmail()); %>" />
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td width="340">
										<b>Password:</b>
									</td>
									<td>
										<input type="password" id="passwordinput" name="password" 
																			placeholder="Enter password..." value="<%out.println(reg.getPassword()); %>" />
									</td>
									<td>
										<input type="button" id="check" name="btnCheck" value="Check" onclick="return passwordCheck()" />
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td width="340">
										<b>Name:</b>
									</td>
									<td>
										<input type="text" id="nameinput" name="name" placeholder="Enter name..." value="<%out.println(reg.getName()); %>" /><br/>
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Gender:</b>
									</td>
									<td>
										<%
										String genderRadio = reg.getGender();
										
										if(genderRadio == null){
									%>
										<input type="radio" id="male" name="gender" value="male" /> Male
										<input type="radio" id="female" name="gender" value="female" /> Female<br/>
									<%
										}else{
											String malechecked = "", femalechecked = "";
											
											if(genderRadio.equals("male")){
												malechecked = "checked";
											}
											if(genderRadio.equals("female")){
												femalechecked = "checked";
											}
									%>
										<input type="radio" id="male" name="gender" value="male" <%= malechecked%> /> Male
										<input type="radio" id="female" name="gender" value="female" <%= femalechecked%> /> Female<br/>
									<%
										}
									%>
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
								<%
									String daylist = reg.getDobday();
									if(daylist != null){
								%>
									<td>
										<b>Date of Birth:</b>
									</td>
									<td>
										<select id="dobday" name="dobday">
											
								<%
										if(daylist.equals("Day")){
											out.println("<option selected>Day</option>");
											for(int i=1; i<32; i++){
												out.println("<option>"+i+"</option>");
											}
										}else{
											out.println("<option>Day</option>");
											for(int i=1; i<32; i++){
												
												if(i == Integer.parseInt(daylist)){
													out.println("<option selected>"+i+"</option>");
												}else{
													out.println("<option>"+i+"</option>");
												}
											}
										}
									}
								%>
										</select>
									</td>
									
									<td width="330">
									<%
										String monthlist = reg.getDobmonth();
										if(monthlist != null){
									%>
										<select id="dobmonth" name="dobmonth">
									<%
											if(monthlist.equals("Month")){
												out.println("<option selected>Month</option>");
												out.println("<option>January</option>");
												out.println("<option>Febuary</option>");
												out.println("<option>March</option>");
												out.println("<option>April</option>");
												out.println("<option>May</option>");
												out.println("<option>June</option>");
												out.println("<option>July</option>");
												out.println("<option>August</option>");
												out.println("<option>September</option>");
												out.println("<option>October</option>");
												out.println("<option>November</option>");
												out.println("<option>December</option>");
											}else{
												out.println("<option>Month</option>");
												if(monthlist.equals("Januray")){
													out.println("<option selected>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("Febuary")){
													out.println("<option>January</option>");
													out.println("<option selected>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("March")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option selected>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("April")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option selected>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("May")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option selected>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("June")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option selected>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("July")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option selected>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("August")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option selected>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("September")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option selected>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("October")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option selected>October</option>");
													out.println("<option>November</option>");
													out.println("<option>December</option>");
												}else if(monthlist.equals("November")){
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option selected>November</option>");
													out.println("<option>December</option>");
												}else{
													out.println("<option>January</option>");
													out.println("<option>Febuary</option>");
													out.println("<option>March</option>");
													out.println("<option>April</option>");
													out.println("<option>May</option>");
													out.println("<option>June</option>");
													out.println("<option>July</option>");
													out.println("<option>August</option>");
													out.println("<option>September</option>");
													out.println("<option>October</option>");
													out.println("<option>November</option>");
													out.println("<option selected>December</option>");
												}
											}
										}
									%>
										</select>
									</td>
									<%
										String yearlist = reg.getDobyear();
										if(yearlist != null){
									%>
									<td>
										 <select id="dobyear" name="dobyear">
									<%
											if(yearlist.equals("Year")){
												out.println("<option selected>Year</option>");
												for(int i=2011; i>1958; i--){
													out.println("<option>"+i+"</option>");
												}
											}else{
												out.println("<option>Year</option>");
												for(int i=2011; i>1958; i--){
													if(i == Integer.parseInt(yearlist)){
														out.println("<option selected>"+i+"</option>");
													}else{
														out.println("<option>"+i+"</option>");
													}
												}
											}
										}
									%>
										</select>
									</td>
								</tr>
							</table>	
							
							<table>
								<tr>
									<td>
										<b>Mailing Address:</b>
									</td>
									<td width="860">
										<input type="text" id="addressinput" name="address" placeholder="Enter address..." value="<%out.println(reg.getAddress()); %>" /><br/>
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Contact No.:</b>
									</td>
									<td>
										<input type="text" id="contactinput" name="contactno" placeholder="Enter contact no..." value="<%out.println(reg.getContactno()); %>" /><br/>
									</td>
								</tr>
								
								<tr>
									<td>
										<b>Subscribe Newsletter:</b>
									</td>
									<td>
									<%
										String subRadio = reg.getSubscribe();
										
										if(subRadio == null){
									%>
										<input type="radio" id="yes" name="subscribe" value="yes" /> Yes
										<input type="radio" id="no" name="subscribe" value="no" /> No<br/><br/>
									<%
										}else{
											String yeschecked = "", nochecked = "";
											if (subRadio.equals("yes")){
												yeschecked = "checked";
											}
											
											if(subRadio.equals("no")){
												nochecked = "checked";
											}
										
									%>
										<input type="radio" id="yes" name="subscribe" value="yes" <%= yeschecked%> /> Yes
										<input type="radio" id="no" name="subscribe" value="no" <%= nochecked%> /> No<br/><br/>
									<%
										}
									%>
									</td>
								</tr>
							
								<tr>
									<td>
									
									</td>
									<td>
										<input type="submit" id="create" name="btnCreate" value="Create" />
										<input type="reset" name="btnReset" value="Reset" />
									</td>
								</tr>							
							</table>
						</form>
					<%
						}
					%>
						<br/><br/>
					<div align="center">
						<%
							out.println("<form action='index.jsp' method='post'>");
							out.println("<input type='submit' name='btnBack' value='Back to main'/>");
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