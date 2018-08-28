<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.service.UserServiceImpl"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
.tooltipi {
	color: black;
	position: relative;
	display: inline-block;
}

.tooltipi .tooltiptexti {
	 visibility: hidden;
  width: 200px;
    background-color: #4CAF50;
    color: black;
    font-size:small;
    text-align: center;
    border-radius: 6px;
    /* padding: 5px 0; */
    
    /* Position the tooltip */
    position: absolute;
    z-index: 1;
    top: 100%;
    left: 50%;
    margin-left: -60px;
    min-height: 190px;
    max-height: 190px;
}

.tooltipi:hover .tooltiptexti {
	visibility: visible;
}

.button {
	background-color: #4CAF50; /* Green */
	border: none;
	width: 35em;
	height: 3em;
	color: white;
	border-radius: 8px;
	/* padding: 16px 32px; */
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	-webkit-transition-duration: 0.4s; /* Safari */
	transition-duration: 0.4s;
	cursor: pointer;
}

.button2 {
	background-color: white;
	color: black;
	border: 2px solid #008CBA;
}

.button2:hover {
	background-color: #008CBA;
	color: white;
	border: 2px solid #4CAF50;
}

.panel {
	-webkit-animation: .5s ease;
	-moz-animation: .5s ease;
	-o-animation: .5s ease;
	margin-bottom: 10px;
}

.panel:hover {
	-webkit-transform: scale(1.05);
	-moz-transform: scale(1.05);
	-ms-transform: scale(1.05);
	-o-transform: scale(1.05);
	transform: scale(1.05);
	color: white;
}

.desc {
	border: 0px;
	text-align: center;
	padding-top: 10px;
}

.panel-updated {
	margin-left: -15px;
	margin-right: -15px;
	margin-bottom: -15px;
	height: 100px;
	padding-left: 15px;
	padding-right: 15px;
	padding-top: 0px;
}

.panel-oracle {
	margin-left: -15px;
	margin-right: -15px;
	margin-bottom: -4px;
}

.panel-cust {
	border: 2px solid #a8a8a8;
	box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
	width: 95%;
}

.panel-commingsoon:hover {
	background: rgba(0, 0, 0, .75);
	color: white;
}

.panel-commingsoon {
	height: 80px;
}
</style>

</head>
<body>


	<%
		String emailn = (String) session.getAttribute("emailn");
		int uid = (Integer) session.getAttribute("uid");
		String uname = (String) session.getAttribute("uname");
	%>

	<center>
	
		<div class="row jumbotron vertical-center-row">
		<c:set var="value" value="deleted" />	
	<c:if test="${del == value}">
	<center><h2>You have successfully <span style=color:maroon;>Deleted</span> your Blog<br><br></h2></center>
	</c:if>
			<h1 style="font-size: 40px;" align="center">
				Author
				<%-- <%=emailn %> <br><%=uid %><br> --%><span style="color: #008CBA;"><%=uname%></span>
				
			</h1>
			<center>
			 <span class="tooltipi">
				<h3> <span class="glyphicon glyphicon-user" style="color: orange;"></span></h3>
				<center> <span class="tooltiptexti"><strong><br><h4><span class="glyphicon glyphicon-user" style="color: white;"></span></h4> Profile:</strong><br><hr>
				 <strong>Email: </strong>${emailn} <br>
				  <strong>Total Blogs Written: </strong>${totalBlogs}<br>
				 </span></center>
				 </span>
				 </center>
			<p class="lead" align="center">
				<i class="fa fa-list"></i>  Welcome<br>
			<hr>
			<div class="row">
				<!-- Spacer -->
				<div class="col-md-2 " ></div>
				<div class="col-md-4">
					<div class="panel panel-default panel-cust"
						style="width: 50%; height: 50%; background-color: #008CBA;">
						<div class="panel-body panel-updated">
							<h2 align="center">
								<img src=" " height="10" width="132" alt="View Blogs">
							</h2>
							<h4 class="desc"></h4>
						</div>
						<div class="btn-group btn-block foot">
							<a style="background-color: black;" href="/Dashboard/view"
								class="btn btn-block btn-ibm dropdown-toggle"><span
								class="glyphicon glyphicon-cog"></span><strong>View
									Blogs</strong> <span class="caret"></span> </a>

						</div>
					</div>
				</div>



				<div class="col-md-4">
					<div class="panel panel-default panel-cust"
						style="width: 50%; height: 50%; background-color: #008CBA;">
						<div class="panel-body panel-updated">
							<h2 align="center">
								<img src=" " height="50" width="50" alt="Add Blog">
							</h2>
							<h4 class="desc"></h4>
						</div>
						<div class="btn-group btn-block foot">
							<a style="background-color: black;" href="/Dashboard/add"
								class="btn btn-block btn-ibm dropdown-toggle"><span
								class="glyphicon glyphicon-cog"></span><strong>Add Blog</strong>
								<span class="caret"></span> </a>

						</div>
					</div>
				</div>




			</div>
		</div>

	</center>
	<form action="/Logout">
		<fieldset>
			<center>
				<input class="button button2" name="logout" type="submit"
					value="Logout">
			</center>
		</fieldset>
	</form>











































</body>
</html>