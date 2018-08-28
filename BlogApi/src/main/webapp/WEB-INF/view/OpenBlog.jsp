<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- ===============================================
 -->
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 600px;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
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
	width: 400px;
}

.button2:hover {
	background-color: #008CBA;
	color: white;
}

.button4 {
	background-color: white;
	color: black;
	border: 2px solid maroon;
	width: 400px;
}

.button4:hover {
	background-color: maroon;
	color: white;
}
.tooltipi {
	color: black;
	position: relative;
	display: inline-block;
}

.tooltipi .tooltiptexti {
	 visibility: hidden;
  width: 200px;
    background-color: #008CBA;
    color: white;
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
    min-height: 120px;
    max-height: 120px;
}

.tooltipi:hover .tooltiptexti {
	visibility: visible;
}
#comm {
	/* background-color: #008CBA; /* Green */ */
	border: double;
	border-color: #008CBA;
	width: 30em;
	height: 3em;
	font-size: 16px;
	/* color: white; */
	border-radius: 4px;
	padding: 16px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
}

#rate {
	width: 10em;
	height: 2.5em;
	font-size: 12px;
}

#scomment {
	width: 10em;
	height: 2.5em;
	font-size: 12px;
}
</style>
</head>
<body>
	<br>
	<div class="col-md-12">
		<div class="modal-dialog"
			style="margin-bottom: 0; border: dashed; border-color: #008CBA">
			<c:set var="value" value="done" />
			<c:if test="${rate == value}">
				<h2>
					<center>You have successfully</center>
					<center>
						<span style="color: maroon;">Rated </span>& <span
							style="color: maroon;">Commented</span>
					</center>
					<center>
						your Blog<br>
						<br>
					</center>
				</h2>
			</c:if>

			<c:forEach items="${blogDtoList}" var="temp">
				<c:set var="total" value="${temp.blogid}" />

				<c:if test="${total == blogidd}">


					<div class="modal-content">
						<div class="panel-heading">
							<center>
								<span style="color: #008CBA"><h2>${temp.blogTitle}</h2></span>
							</center>
						</div>
						<div class="panel-body">
							<center>
								<h4>
									<span>Wrtiiten By:</span> ${temp.user.username}
								</h4>	<center>
			 <span class="tooltipi">
				<h3> <span class="glyphicon glyphicon-user" style="color: maroon;"></span></h3>
				<span class="tooltiptexti"><strong><br>Profile:</strong><br><hr>
				 <strong>Email: </strong>${emailn} <br>
				  <strong>Total Blogs Written: </strong>${totalBlogs}<br>
				 </span>
				 </span>
				 </center>
								<p>${temp.type}</p>
								<p>Rating: ${temp.rating}</p>
								<p>Comments: ${temp.tcomment}</p>
								<p>${temp.blogDate}</p>
							</center>
							<hr>
							<strong><p>${temp.blog}</p></strong>
							<hr>


							<center>

								<!-- Trigger/Open The Modal -->
								<button class="button button4" id="myBtn">Rate &
									Comment..!</button>

								<!-- The Modal -->
								<div id="myModal" class="modal">

									<!-- Modal content -->
									<div class="modal-content">
										<span class="close">&times;</span>
										<script>
											// Get the modal
											var modal = document
													.getElementById('myModal');

											// Get the button that opens the modal
											var btn = document
													.getElementById("myBtn");

											// Get the <span> element that closes the modal
											var span = document
													.getElementsByClassName("close")[0];

											// When the user clicks the button, open the modal 
											btn.onclick = function() {
												modal.style.display = "block";
											}

											// When the user clicks on <span> (x), close the modal
											span.onclick = function() {
												modal.style.display = "none";
											}

											// When the user clicks anywhere outside of the modal, close it
											window.onclick = function(event) {
												if (event.target == modal) {
													modal.style.display = "none";
												}
											}
										</script>

										<center>
											<div class="form-group">
												<form action="/Blog/open/rateComment" method="post"
													style="color: black;">
													<div align="left">
														<h4>
															<span style="color: #008CBA;">*Name:</span>
														</h4>
													</div>
													<br>
													<input style="width: 400px" class="form-control"
														type="text" name="rName" placeholder="Enter your Name"
														required /><br>
													<div align="left">
														<h4>
															<span style="color: #008CBA;">*Email:</span>
														</h4>
													</div>
													<br>
													<input style="width: 400px" class="form-control"
														type="email" name="rEmail" placeholder="Enter your Email"
														required /><br> <Br>
													<h4>
														<span style="color: #008CBA;">Your Rating</span>
													</h4>


													<input type="radio" name="rate" id="str1" value="1"
														required> 1&nbsp; <input type="radio"
														name="rate" id="str2" value="2"> 2&nbsp; <input
														type="radio" name="rate" id="str3" value="3">3&nbsp;
													<input type="radio" name="rate" id="str4" value="4">4&nbsp;
													<input type="radio" name="rate" id="str5" value="5">5&nbsp;

													<input type="hidden" name="rating" id="frates"
														value=${temp.rating } /> <input type="hidden"
														name="rateCount" id="ratecs" value=${temp.rateCount } /> <br>
													<br> <input type="text" id="comm" name="comment"
														placeholder="Write Your Comment" required> <input
														type="hidden" name="blogid" id="Blogids"
														value=${temp.blogid } /> <input type="hidden"
														name="tcomment" id="tcomment" value=${temp.tcomment } />
													<br> <br>
													<br>
													<input type="submit" class="button button2"
														name="rateComment" value="Submit Comment"
														style="color: black;" />
												</form>

											</div>
										</center>
										<br> <br>
										<hr>


									</div>

								</div>




							</center>
							<br> <span style="color: #008CBA"><h3>Comments:</h3></span>
							<c:forEach items="${commentsDtoList}" var="ctemp">
								<c:set var="total" value="${ctemp.blog.blogid}" />
								<c:if test="${total == blogidd}">


									<div class="panel-body" align="left">


										<p>
											<span class="glyphicon glyphicon-comment"
												style="color: orange;"></span><strong style="color: maroon;">
												${ctemp.rName}</strong> - { ${ctemp.rEmail} }
										</p>



										<p>${ctemp.comment}</p>

									</div>
								</c:if>
							</c:forEach>

							<center>
								<form action="/Blog" method="post">
									<fieldset>

										<input class="button button2" name="ViewBlogs" type="submit"
											value="Back to All Blog(s)">

									</fieldset>
								</form>

							</center>



						</div>
					</div>
				</c:if>

			</c:forEach>



		</div>
	</div>
	<hr>
</body>