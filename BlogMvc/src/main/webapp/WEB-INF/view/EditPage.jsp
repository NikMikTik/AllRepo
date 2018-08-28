<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Edit Page</title>
</head>
<br>
<div class="col-md-12">
	<div class="modal-dialog" style="margin-bottom: 0">
		<div class="modal-content">
			<div class="panel-heading">
				<center>
					<h3>Edit Page</h3>
				</center>
			</div>
			<div class="panel-body">
				<form action="/Dashboard/view/edit/show" method="post">
					<fieldset>
						<div class="form-group">
							<input class="form-control" placeholder="BlogTitle" name="blogTitle"
								type="text" required autofocus value="${bt}">
						</div>
						<div class="form-group">
													<textarea class="form-control" cols="2" rows="10" name="blog"  placeholder="Your Blog Goes Here..." required >${b}</textarea>
						
						<%-- 	<input class="form-control" placeholder="Blog"
								name="blog" type="text" required value="${b}"> --%>
								<input class="form-control" placeholder="Blog"
								name="blogid" type="hidden" required value="${id}">
						</div>
						<div class="checkbox">
							<label> 
							</label>

						</div>
						<input class="button button1" name="saveForm" type="submit"
							value="Edit">
						<!--                                  <input class="btn btn-sm btn-success" id="saveForm" name="saveForm" type="submit" value="sign in" /><br>
 -->
						<!-- Change this to a button or input when using this as a form -->
						<!--  <a href="javascript:;" class="btn btn-sm btn-success">Login</a> -->
					</fieldset>
				</form>
				<form action="/Dashboard/view" method="post">
					<fieldset>

						<input class="button button2" name="ViewBlog" type="submit"
							value="Back to All Blogs">

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

		<br>
		<br> <input class="button button3" name="Go to Reader"
			type="submit" value="Want to Read a Blog?, Go to Reader Section">


	</form>

</center>

