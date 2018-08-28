<%@page import="org.springframework.ui.ModelMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Insert title here</title>

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

.tooltipi {
	color: black;
	position: relative;
	display: inline-block;
}

.tooltipi .tooltiptexti {
	visibility: hidden;
	width: 300px;
	background-color: #008CBA;
	color: white;
	text-align: center;
	border-radius: 6px;
	padding: 5px 0;
	/* Position the tooltip */
	position: absolute;
	z-index: 1;
	bottom: 100%;
	left: 50%;
	margin-left: -60px;
}

.tooltipi:hover .tooltiptexti {
	visibility: visible;
}

.tooltipii {
	color: black;
	position: relative;
	display: inline-block;
}

.tooltipii .tooltiptextii {
	visibility: hidden;
	width: 200px;
	background-color: teal;
	color: white;
	text-align: center;
	border-radius: 6px;
	padding: 5px 0;
	/* Position the tooltip */
	position: absolute;
	z-index: 1;
	top: 100%;
	left: 50%;
	margin-left: -60px;
	min-width: 250px; /* I have changed here */
	max-width: 250px;
}

.tooltipii:hover .tooltiptextii {
	visibility: visible;
}
</style>
<script>
	function removeUser(deleteURL) {

		$.ajax({

			type : "DELETE",
			url : deleteURL,

			success : function() {
				/* 				$('#re').remove();
				 */
				window.location.reload();
				window.location.href = "/Dashboard/view/deleted";
				$('#aa').remove();

			},

			failure : function(errMsg) {
				console.logger(errMsg.toString())
			}
		});
	}
</script>
<script>
	function editUser(editUrl) {

		$.ajax({

			type : "POST",
			url : editUrl,

			success : function() {
				alert(msg);

			},

			failure : function(errMsg) {
				console.logger(errMsg.toString())
			}
		});
	}
</script>
</head>
<body>
	<br>
	<br>

	<!-- <h1>Welcome to Blogging Site..! Read,Comment and Rate...!</h1> -->

	<%
		out.println("<center><h1>Welcome to your <span style='color:#008CBA'>BlogList...</span></h1></center>");

		if (request.getParameter("chi") != null && request.getParameter("chi").equals("true")) {
			out.println("<br><br><center><h1>Blog Deleted</h1></center>");
		}

		/* if (request.getParameter("kk") != null && request.getParameter("kk").equals("tt")) {
			out.println("<span id='a'><br><br><center><h1>Blog Edited</h1></center></span>");
		} */
	%>
	<br>
	<br>
	<div class="container">
		<div id="re">
		<c:forEach items="${blogDtoList}" var="btemp">
		<c:set var="id" value="${btemp.blogid}" />
		</c:forEach>
			<c:set var="value" value="edited" />
			<c:if test="${edit == value}">
				<center>
					<h2>
						You have successfully <span style="color: maroon;">Edited</span>
						your Blog<br> <br>
					</h2>
				</center>
			</c:if>

			<c:set var="value" value="deleted" />
			<c:if test="${del == value}">
				<center>
					<h2>
						You have successfully <span style="color: maroon;">Deleted</span>
						your Blog<br> <br>
					</h2>
				</center>
			</c:if>

		</div>
		<div class="row">


			<div class="col-md-12">
				<!-- <h4>Bootstrap Snipp for Datatable</h4> -->
				<div class="table-responsive">

					<h4>
						<table id="mytable" class="table table-bordred table-striped">

							<thead>


								<!-- 
								<th><input type="checkbox" id="checkall" /></th> -->
								<th>Title</th>
								<th>Blog</th>
								<th>Type</th>
								<th>Written On</th>
								<th>Author</th>
								<th>Rating</th>
								<th></th>
								<th>Comments</th>
								<th>Edit</th>
								<th>Delete</th>

							</thead>
							<tbody>

<%int uid = (Integer) session.getAttribute("uid"); %>
								<c:forEach items="${blogDtoList}" var="temp">
									<c:set var="total" value="${temp.user.userid}" />

									<c:if test="${total == uid}">

										<tr id="mytablerow">

											<td>${temp.blogTitle}</td>
											<td>${temp.blog}</td>
											<td>${temp.type}</td>
											<td>${temp.blogDate}</td>
											<td>${temp.user.username}</td>
											<td class="tooltipi">${temp.rating}<span
												class="tooltiptexti">${temp.rateCount} Reader(s) rated 
													this Blog</span></td>

											<td></td>

											<td class="tooltipii"><center>
													<span class="glyphicon glyphicon-comment" style="color: teal;"></span><span
														class="tooltiptextii"> <c:set var="tcom"
															value="${temp.blogid}" /> <span style="color: black;"><p>
																<h5><strong>${temp.rateCount} Reader(s) Commented on your Blog</strong></h5> </span>
																	<hr>
															<c:forEach items="${commentsDtoList}" var="ctemp">

																	<c:set var="total" value="${ctemp.blog.blogid}" />
															
															<c:if test="${total == tcom}">

															
																<div class="panel-body" align="left">

																	<span style="color: black"><p>
																		
																				<h5><span class="glyphicon glyphicon-comment" style="color:orange;"></span> <strong>${ctemp.rName}</strong>-{ ${ctemp.rEmail} }</h5>
																		</p></span>
																	<p>${ctemp.comment}</p>

																</div>
															</c:if>
														</c:forEach>
													
															</span>
												</center></td>


											<%-- <td><center><p data-placement="top" data-toggle="tooltip"
											title="commm">
											<button class="btn btn-danger btn-xs" data-title="commm"
												data-toggle="modal" data-target="#commm" value="${temp.blogid}">
												
																			<c:set var="tcom" value="${temp.blogid}" />
												
											</button>
										</p></center></td> --%>


											<td><center>
													<p data-placement="top" data-toggle="tooltip" title="Edit">
													
													<form action="/Dashboard/view/edit" method="post">
														<button class="btn btn-primary btn-xs" data-title="Edit"
															data-toggle="modal" value="${temp.blogid}">
															<span class="glyphicon glyphicon-pencil"></span>
														</button>
														<input class="btn btn-primary btn-xs" type="hidden"
															name="blogid" value="${temp.blogid}">
													</form>
													</p>
												</center></td>


											<td><center>
													<p data-placement="top" data-toggle="tooltip"
														title="Delete">

														<c:url value="/Dashboard/delete/${temp.blogid}"
															var="deleteUrl" />
														<button class="btn btn-danger btn-xs" data-title="Delete"
															data-toggle="modal" onclick="removeUser('${deleteUrl}')">
															<span class="glyphicon glyphicon-trash"></span>
														</button>
													</p>
												</center></td>
										</tr>

									</c:if>
								</c:forEach>

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
				<form action="DelBlog" method="post">
					<div class="modal-footer ">
						<span class="glyphicon glyphicon-ok-sign"></span> <input
							type="submit" class="btn btn-success" value="Yes" name="delok">
						<input type="hidden" name="blogid" value=${id}>
				
				</form>
				<span class="glyphicon glyphicon-remove"></span>  <input
					type="submit" class="btn btn-default" value="No"
					data-dismiss="modal">


			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
	</div>


	<div class="modal fade" id="commm" tabindex="-1" role="dialog"
		aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button style="color: black;" type="button" class="close"
						data-dismiss="modal">&times;</button>
					<h4 class="modal-title custom_align" id="Heading">
						<span style="color: #008CBA"><h3>Comments:</h3></span>
					</h4>

				</div>

				<div class="modal-body">


					<c:forEach items="${commentsDtoList}" var="ctemp">
						<c:set var="total" value="${ctemp.blog.blogid}" />
						<c:if test="${total == tcom}">


							<div class="panel-body" align="left">

								<span style="color: maroon"><p>
									
									<h5>${ctemp.rName}-{ ${ctemp.rEmail} }</h5>
									</p></span>
								<p>${ctemp.comment}</p>

							</div>
						</c:if>
					</c:forEach>

				</div>



			</div>




		</div>
		<!-- /.modal-content -->
	</div>


	<br>
	<br>
	<br>
	<center>


		<form action="/Dashboard" method="post">


			<input class="button button2" name="Back to Author Dashboard"
				type="submit" value="Back to Author Dashboard">


		</form>

	</center>


</body>

</html>