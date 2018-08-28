<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please, Sign Up</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<center>
	<!--  <h2>Welcome..!</h2> -->
</center>
<%
	if (request.getParameter("check") != null && request.getParameter("check").equals("true")) {
		out.println(
				"<center><h2>Congratulations... You are now a Registered Author...! <br><br>You can now sign in...</h2></center>");
	}
%>



<style>
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

.button1 {
	background-color: white;
	color: black;
	border: 2px solid #4CAF50;
}

.button1:hover {
	background-color: #4CAF50;
	color: white;
}

.button2 {
	background-color: white;
	color: black;
	border: 2px solid #800000;
}

.button2:hover {
	background-color: #800000;
	color: white;
}
</style>
</head>
<body>
	<center>
		<h1>
			<br> Welcome to Registration Page:<br>

			<%
				if (request.getParameter("check") != null && request.getParameter("check").equals("false")) {
					out.println("<center><h2>Already Existing Email Id...</h2></center>");
				}
			%>
<c:set var="value" value="Existing" />

<c:if test="${check == value}">
	<center>
		<h2>
			Already<span style="color: maroon;"> Existing </span> Email...!
		</h2>
	</center>
</c:if>
			<br>
			<div class="col-md-12">
				<div class="modal-dialog" style="margin-bottom: 0">
					<div class="modal-content">
						<div class="panel-heading">
							<center>
								<h3>Sign Up</h3>
							</center>
						</div>
						<div class="panel-body">
							<form action="/SignUp/registered" method="post">
								<fieldset>
									<div class="form-group">
										<input class="form-control" placeholder="UserName"
											name="username" type="text" required autofocus="">
									</div>
									<div class="form-group">
										<input class="form-control" placeholder="E-mail" name="email"
											type="email" required autofocus="">
									</div>
									<div class="form-group">
										<input class="form-control" placeholder="Password"
											name="password" type="password" required value="">
									</div>

									<input class="button button1" name="createuser" type="submit"
										value="Sign Up">
							</form>

							<form action="/Login" method="post">
								<fieldset>

									<input class="button button2" name="Submit" type="submit"
										value="Back to Sign In">

								</fieldset>
							</form>


						</div>
					</div>
				</div>
			</div>
			<hr>
</body>
</html>