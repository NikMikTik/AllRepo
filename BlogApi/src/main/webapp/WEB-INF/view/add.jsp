<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Blog</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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
</style>
</head>
<body>
	<center>
		<h1>
			<%
				int uid = (Integer) session.getAttribute("uid");
		
			%>



		</h1>

		<div class="col-md-12">
			<div class="modal-dialog" style="margin-bottom: 0">
				<div class="modal-content">
					<div class="panel-heading">
						<center>
							<h3>Write Your Blog Here</h3>
						</center>
					</div>
					<div class="panel-body">
						<form action="/Dashboard/add/submit" method="post">

							<input type="radio" name="type" value="Java" required><span>Java</span><br>
							<br> <input type="radio" name="type" value="DotNet"><span>DotNet</span><br>
							<br> <input type="hidden" name="userid" value=<%=uid%>>


							<div class="form-group">
								<input class="form-control"
									placeholder="Your Blog Title Goes Here..." name="blogTitle"
									type="text" required autofocus="">
							</div>
							<div class="form-group">
							<textarea class="form-control" cols="2" rows="10" name="blog" placeholder="Your Blog Goes Here..." required></textarea>
						<!-- 		<input class="form-control" placeholder="Your Blog Goes Here..."
									name="Blog" type="text" required> -->
							</div>

							<input class="button button1" name="saveForm" type="submit"
								value="Save Your Blog">
<%String email=(String)session.getAttribute("emailn"); %>

						</form>
						<form action="/Dashboard" method="post">

<input class="button button2" name="email"
								type="hidden" value=<%=email%>>

							<input class="button button2" name="Back to Autor Dashboard"
								type="submit" value="Dashboard">





						</form>

					</div>
				</div>
			</div>
		</div>
		<hr>










	</center>
</body>
</html>