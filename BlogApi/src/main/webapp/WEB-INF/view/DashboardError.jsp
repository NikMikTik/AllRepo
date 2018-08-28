<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Error Page</title>
<style>
.button2 {
	background-color: white;
	color: black;
	border: 2px solid #008CBA;
}

.button2:hover {
	background-color: #008CBA;
	color: white;
	width: 10em;
	height: 2.5em;
	font-size: 12px;
	border-radius: 8px;
}
</style>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<center>
		<div class="container"
			style="border: dashed; width: 800px; height: 250px">
			<div class="row">
				<div class="col-md-12">
					<div class="error-template">
						<h1>Oops!</h1>
						<h2>Your Requested Page cannot be Opened ::</h2>
						<div class="error-details">Sorry, an error has occured..! 
							${exception}!</div>
						<div class="error-actions">
							<br>
							<!--  <a href="/Blog" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                        Take Me Home </a><br><a href="#" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Contact Support </a> -->

							<form action="/Dashboard" method="post">
								<input class="button button2" name="Go to Blog" type="submit" style="font-size: medium;"
									value="Take Me Home">
							</form>


						</div>
					</div>
				</div>
			</div>
		</div>
	</center>
</body>
</html>