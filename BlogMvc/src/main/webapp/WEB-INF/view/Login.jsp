<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<center>
	<h2>Welcome..!</h2>
</center>
<%-- <%
	if (request.getParameter("validity") != null && request.getParameter("validity").equals("f")) {
		out.println("<center><h2>Kindly, Register yourself");
	} /* 
		if (request.getParameter("check") != null && request.getParameter("check").equals("true")) {
			out.println(
					"<center><h2>Congratulations... You are now a Registered Author...! <br><br>You can now sign in...</h2></center>");
		} */
%> --%>

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
	border: 2px solid #008CBA;
}

.button2:hover {
	background-color: #008CBA;
	color: white;
}

.button3 {
	background-color: white;
	color: black;
	border: 2px solid #800000;
}

.button3:hover {
	background-color: #800000;
	color: white;
}
</style>
<title>Login Page</title>
</head>
<br>
<c:set var="value2" value="Incorrect" />
<c:set var="value1" value="NotValid" />
<c:set var="value" value="true" />

<c:if test="${check == value && check!= value1 && check != value2}">
	<center>
		<h2>
			Congratulations... You are now a <span style="color: maroon;">Registered
				Author...! </span><br>
			<br>You can now sign in...
		</h2>
	</center>
</c:if>

<c:if test="${check == value1}">
	<center>
		<h2>
			Kindly, <span style="color: maroon;">Register</span> Yourself
		</h2>
	</center>
</c:if>

<c:if test="${check == value2}">
	<center>
		<h2>
			Username/Password <span style="color: maroon;">Invalid</span>
		</h2>
	</center>
</c:if>

<div class="col-md-12">
	<div class="modal-dialog" style="margin-bottom: 0">
		<div class="modal-content">
			<div class="panel-heading">
				<center>
					<h3>Sign In</h3>
				</center>
			</div>
			<div class="panel-body">
				<form action="/Welcome" method="post">
					<fieldset>
						<div class="form-group">
							<input class="form-control" placeholder="E-mail" name="email"
								type="email" required autofocus="">
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="Password"
								name="password" type="password" required value="">
						</div>
						<div class="checkbox">
							<label> </label>

						</div>
						<input class="button button1" name="saveForm" type="submit"
							value="Sign In">
						<!--                                  <input class="btn btn-sm btn-success" id="saveForm" name="saveForm" type="submit" value="sign in" /><br>
 -->
						<!-- Change this to a button or input when using this as a form -->
						<!--  <a href="javascript:;" class="btn btn-sm btn-success">Login</a> -->
					</fieldset>
				</form>
				<form action="/SignUp" method="post">
					<fieldset>

						<input class="button button2" name="Register" type="submit"
							value="Sign Up">

					</fieldset>
				</form>







			</div>
		</div>
	</div>
</div>
<hr>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<center>


	<form action="/Blog" method="post">

		<br> <br> <input class="button button3" name="Go to Reader"
			type="submit" value="Want to Read a Blog?, Go to Reader Section">


	</form>

</center>

