<%@page import="org.springframework.ui.ModelMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>


<script>
	function getText() {
		var nta = document.getElementById("nta").value;
		var blogid = document.getElementById("blogid").value;

		/*   document.getElementById('output').innerHTML +=str + " i did it"; */
	}
</script>
<!------ Include the above in your HEAD tag ---------->

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Blog</title>

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
<%-- <%
	if (request.getParameter("chi") != null && request.getParameter("chi").equals("true")) {
		out.println("<br><br><center><h1>Blog Deleted</h1></center>");
	} else {
		out.println("<center><h1>Congratulations... You have Successfully created a Blog...</h1></center>");
	}
%> --%>
</head>
<body>

	<br>
	<br>
	<c:set var="value" value="edited" />
	<c:if test="${check_edit == value}">
		<center>
			<h2>
				You have successfully <span style="color: #4CAF50;">Edited</span>
				your Blog<br> <br>
			</h2>
		</center>
	</c:if>
	<br>
	<br>
	<c:set var="value" value="added" />
	<c:if test="${check_add == value}">
		<center>
			<h2>
				You have successfully <span style="color: #008CBA;">Added</span>
				your Blog<br> <br>
			</h2>
		</center>
	</c:if>


	<!-- <h1>Welcome to Blogging Site..! Read,Comment and Rate...!</h1> -->


	<c:forEach items="${blogDtoList}" var="temp">
		<c:set var="id" value="${temp.blogid}" />
		<c:set var="title" value="${temp.blogTitle}" />
		<c:set var="blog" value="${temp.blog}" />
		<c:set var="type" value="${temp.type}" />
		<c:set var="bdate" value="${temp.blogDate}" />
		<c:set var="rating" value="${temp.rating}" />
		<c:set var="unamee" value="${temp.user.username}" />
		<c:set var="tcomm" value="${temp.tcomment}" />
	</c:forEach>





	<div class="container">
		<div class="row">


			<div class="col-md-12">
				<!-- <h4>Bootstrap Snipp for Datatable</h4> -->
				<div class="table-responsive">

					<h4>
						<table id="mytable" class="table table-bordred table-striped">

							<thead>

								<!-- <th><input type="checkbox" id="checkall" /></th> -->

								<th>Title</th>
								<th>Type</th>
								<th>Written On</th>
								<th>Author</th>
								<th>Edit</th>
								<th>Delete</th>

							</thead>
							<tbody>



								<tr>
									<!-- <td><input type="checkbox" class="checkthis" /></td> -->
									<td>${title}</td>
									<td>${type}</td>
									<td>${bdate}</td>
									<td>${unamee}</td>
									<td><p data-placement="top" data-toggle="tooltip"
											title="Edit">
											<button class="btn btn-primary btn-xs" data-title="Edit"
												data-toggle="modal" data-target="#edit">
												<span class="glyphicon glyphicon-pencil"></span>
											</button>
										</p></td>
									<td><p data-placement="top" data-toggle="tooltip"
											title="Delete">
											<button class="btn btn-danger btn-xs" data-title="Delete"
												data-toggle="modal" data-target="#delete">
												<span class="glyphicon glyphicon-trash"></span>
											</button>
										</p></td>
								</tr>


							</tbody>

						</table>
					</h4>
					<div class="clearfix"></div>


				</div>

			</div>
		</div>
	</div>


	<div class="modal fade" id="edit" tabindex="-1" role="dialog"
		aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4 class="modal-title custom_align" id="Heading">Edit Your
						Blog</h4>
				</div>

				<form action="/Dashboard/edit" method="post">
					<div class="modal-body">

						Title:
						<div class="form-group">
							<input id="blogid" class="form-control " name="blogTitle"
								type="text" value="${title}">
						</div>

						Blog:
						<div class="form-group">
							<textarea class="form-control" cols="2" rows="10" name="blog"
								placeholder="Your Blog Goes Here..." required>${blog}</textarea>

							<%-- <input rows="3" class="form-control" value="${blog}" type="text"
								name="blog" id="nta"> --%>
							</input> <input type="hidden" name="blogid" value=${id}>
						</div>
					</div>
					<div class="modal-footer ">
						<input type="submit" value="Update" class="btn btn-warning btn-lg"
							class="glyphicon glyphicon-ok-sign" style="width: 100%;">

					</div>


				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>



	<div class="modal fade" id="delete" tabindex="-1" role="dialog"
		aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4 class="modal-title custom_align" id="Heading">Delete this
						entry</h4>
				</div>

				<div class="modal-body">

					<div class="alert alert-danger">
						<span class="glyphicon glyphicon-warning-sign"></span> Are you
						sure you want to delete this Record?
					</div>

				</div>


				<form action="/Dashboard/delete" method="post">
					<div class="modal-footer ">
						<span class="glyphicon glyphicon-ok-sign"></span> <input
							type="submit" class="btn btn-success" value="Yes" name="delok">
						<input type="hidden" name="blogid" value=${id}>
				</form>
				&nbsp; <span class="glyphicon glyphicon-remove"></span> <input
					type="submit" class="btn btn-default" value="No"
					data-dismiss="modal">
			</div>




		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
	</div>

	<br>
	<br>
	<br>
	<center>


		<form action="/Dashboard" method="post">


			<input class="button button1" name="Back to Author Dashboard"
				type="submit" value="Back to Author Dashboard">


		</form>

	</center>


</body>
</html>