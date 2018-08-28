<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Blogs</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="stylesheet.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
#comm {
	background-color: #008CBA; /* Green */
	border: none;
	width: 10em;
	height: 2.5em;
	font-size: 12px;
	color: white;
	border-radius: 4px;
	/* padding: 16px 32px; */
	text-align: center;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
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
	border: 2px solid #4CAF50;
}

.button2:hover {
	background-color: #4CAF50;
	border:thick;
	border-color:black;
	color: white;
}
.button5 {
	background-color: white;
	color: black;
	border: 2px solid #4CAF50;
}

.button5:hover {
	background-color: #4CAF50;
	border:thick;
	border-color:black;
	color: white;
}
</style>

<style>
@import
	url(https://fonts.googleapis.com/css?family=Roboto:400,100italic,100,300,300italic,900italic,900,700,700italic,500italic,500,400italic)
	;

body {
	font-family: 'Roboto', sans-serif;
}
#sel
{width: 10em;
	height: 2.5em;
	font-size: 12px;color:green;
	border: thick;border-color: green;border-radius: 4px;background:white;}
	
	#sel:hover {
	background-color: #4CAF50;
	color: white;
}
.publicaciones-blog-home {
	padding-bottom: 50px;
	background: url("") no-repeat fixed center center;
	background-size: 100% auto;
	background-color: #f1f1f1;
}

.publicaciones-blog-home h2 {
	text-align: center;
	font-weight: 300;
	margin-bottom: 30px;
	font-size: 44px;
	margin-top: 70px;
}

.publicaciones-blog-home h2 b {
	color: #2BBCDE;
}

.publicaciones-blog-home .fondo-publicacion-home {
	background: #ffffff;
	border-radius: 3px;
	overflow: hidden;
	height: 400px;
	margin-bottom: 20px;
	display: block;
	color: inherit;
	text-decoration: none;
	position: relative;
}

.publicaciones-blog-home .fondo-publicacion-home:hover h3 {
	color: #2BBCDE;
	/*    box-shadow: 0px 4px 3px 3px rgba(0, 0, 0, 0.08);*/
}

.publicaciones-blog-home .fondo-publicacion-home:hover .mascara-enlace-blog-home
	{
	height: 400px;
	width: 100%;
	color: #aaa;
	background-color: #2BBCDE;
	position: absolute;
	top: 0;
	opacity: 0.95;
	-webkit-transition: all 0.4s ease-out 0s;
	-o-transition: all 0.4s ease-out 0s;
	transition: all 0.4s ease-out 0s;
}

.publicaciones-blog-home .black {
	background: #2BBCDE;
}

.publicaciones-blog-home .fondo-publicacion-home .img-publicacion-principal-home
	{
	display: inline-block;
	width: 50%;
	overflow: hidden;
	height: 100%;
}

.publicaciones-blog-home .fondo-publicacion-home .img-publicacion-principal-home img
	{
	height: 100%;
	width: auto;
}

.publicaciones-blog-home .fondo-publicacion-home .contenido-publicacion-principal-home
	{
	display: inline-block;
	vertical-align: top;
	width: 49%;
	padding: 0 10px;
}

.publicaciones-blog-home .fondo-publicacion-home .contenido-publicacion-principal-home h3
	{
	font-weight: 900;
	color: #fff;
	text-transform: uppercase;
	font-size: 30px;
}

.publicaciones-blog-home .fondo-publicacion-home .contenido-publicacion-principal-home p
	{
	color: #ffffff;
	font-size: 16px;
	font-weight: 300;
}

.publicaciones-blog-home .fondo-publicacion-home .contenido-publicacion-home
	{
	padding: 0 10px;
}

.publicaciones-blog-home .fondo-publicacion-home .contenido-publicacion-home h3
	{
	font-weight: 900;
	font-size: 20px;
	text-transform: uppercase;
}

.publicaciones-blog-home .fondo-publicacion-home .img-publicacion-home {
	overflow: hidden;
	max-height: 180px;
}

.mascara-enlace-blog-home {
	height: 400px;
	width: 0%;
	color: #aaa;
	background-color: #2BBCDE;
	position: absolute;
	top: 0;
	opacity: 0.0;
	-webkit-transition: all 0.4s ease-out 0s;
	-o-transition: all 0.4s ease-out 0s;
	transition: all 0.4s ease-out 0s;
	text-align: center;
	padding-top: 180px;
}

.mascara-enlace-blog-home span {
	text-align: center;
	max-height: 400px;
	border: 1px solid #fff;
	display: inline-block;
	padding: 10px 30px;
	border-radius: 3px;
	color: #fff;
	font-weight: 900;
	font-size: 16px;
}

.publicaciones-blog-home .todas-las-publicaciones-home {
	background: #2BBCDE;
	height: 400px;
	width: 100%;
	display: inline-block;
	padding: 20px;
	text-decoration: none;
	border-radius: 3px;
}

.publicaciones-blog-home .todas-las-publicaciones-home span {
	color: #fff;
	font-weight: 900;
	text-transform: uppercase;
	font-size: 25px;
	line-height: 26px;
}

@media ( max-width : 768px) {
	.publicaciones-blog-home h2 {
		text-align: center;
		font-weight: 300;
		margin-bottom: 30px;
		font-size: 34px;
		margin-top: 70px;
	}
	.publicaciones-blog-home .fondo-publicacion-home {
		background: #ffffff;
		border-radius: 3px;
		overflow: hidden;
		height: inherit;
		margin-bottom: 20px;
		display: block;
		color: inherit;
		text-decoration: none;
		position: relative;
	}
	.publicaciones-blog-home .fondo-publicacion-home .img-publicacion-principal-home
		{
		display: inline-block;
		width: 100%;
		overflow: hidden;
		height: auto;
	}
	.publicaciones-blog-home .fondo-publicacion-home .img-publicacion-principal-home img
		{
		height: auto;
		width: 100%;
	}
	.publicaciones-blog-home .black {
		background: #fff;
	}
	.publicaciones-blog-home .fondo-publicacion-home .contenido-publicacion-principal-home
		{
		display: inline-block;
		vertical-align: top;
		width: 100%;
		padding: 0 10px;
	}
	.publicaciones-blog-home .fondo-publicacion-home .contenido-publicacion-principal-home h3
		{
		font-weight: 900;
		color: #333;
		text-transform: uppercase;
		font-size: 20px;
	}
	.publicaciones-blog-home .fondo-publicacion-home .contenido-publicacion-principal-home p
		{
		color: #333;
		font-size: 14px;
		font-weight: 400;
	}
	.publicaciones-blog-home .todas-las-publicaciones-home {
		background: #2BBCDE;
		height: 100%;
		width: 100%;
		display: inline-block;
		padding: 20px;
		text-decoration: none;
		border-radius: 3px;
	}
}

#scomment {
	width: 10em;
	height: 2.5em;
	font-size: 12px;
}

#openb {
	width: 10em;
	height: 2.5em;
	font-size: 12px;
}

#op
{width: 10em;
	height: 2.5em;
	font-size: 12px;color:green;}
	
.a
{color:red;}

#h {
    display: block;
    height: 1px;
    border: 0;
    border-top: 1px solid #ccc;
    margin: 1em 0;
    padding: 0; 
}

#bb2
{width: 20em;
	height: 2.5em;
	font-size: 12px;}
	.button3 {
	background-color: white;
	color: black;
	border: 2px solid #4CAF50;
}

.button3:hover {
	background-color: #4CAF50;
	color: white;
}
</style>
</head>
<body>

	<section class="publicaciones-blog-home">
	<div class="container">
			<div class="">
<br>	<div align="right">

		<form action="/Login" method="post">
			<input class="button button2" id="bb2" name="Go to Author Login" type="submit"
				value="Want to Add a Blog?, Go to Author Login">
		</form>
</div><h2>
				Welcome to <b>Blogging Site...</b> <br>
			</h2>
			<br>
		
			<hr id="h">
			<center>
			<h4>
				Search By catalogue: <a href="/Blog/java">Java</a> | <a href="/Blog/dotNet">DotNet</a> | <a href="/Blog">All</a>
				<br>

<br>
				Search By Author:
<form action="/Blog/catalogue/author" method="post">
<select id="sel" name="Author" required>  <option disabled selected value> -- select an option -- </option>
				<c:forEach items="${userDtoList}" var="utemp">
				
						<option class="button button3" value="${utemp.username}">${utemp.username} { ${utemp.email} }</option>
</c:forEach>					</select>
<input type="hidden" name="type" value="Java">
<input type="submit" class="button button3" name="submit" id="scomment" value="Choose Author">
</form>
			</h4>
</center><hr id="h">
	<h3>
				<span style=color:#008CBA;>Recent</span> <b><span id="sps" style=color:#4CAF50;>JAVA</span></b><span style=color:#008CBA;> Posts </span><span class="glyphicon glyphicon-pencil"
												style="color: orange;;"></span><br><br>
			</h3>
			
			<c:forEach items="${blogDtoList}" var="temp">
				<div class="col-page col-sm-4 col-md-3">
					<div class="fondo-publicacion-home">
						<!--  <div class="img-publicacion-home">
                  <img class="img-responsive" src="https://placeholdit.imgix.net/~text?txtsize=34&txt=&w=500&h=300">
                </div> -->
						<div class="contenido-publicacion-home">
							<center>
								<h3>${temp.user.username}</h3>
								<p>${temp.type}</p>
								<p>Rating: ${temp.rating}</p>
								<p>Comments: ${temp.tcomment}</p>
								<p>${temp.blogDate}</p>
							</center>
							<hr>
							<p>${temp.blog}<span>...</span>
							</p>
							<hr>

						</div>

						<div class="mascara-enlace-blog-home">
							<p style="color: black;"><strong>Click Here to Open:</strong></p>
							<form action="/Blog/open" method="post">
								<input type="hidden" name=blogid value=${temp.blogid }>
								<input id="openb" class="button button5" type="submit"
									name="OpenBlog" value=${temp.blogTitle}></input>
							</form>

							<br> <br>
							</center>
						
						</div>
					</div>


				</div>



			</c:forEach>
			<hr>
		</div>
	</div>
	</div>


	<!-- <br><br> -->
	<center>


		<form action="/Login" method="post">
			<input class="button button2" name="Go to Author Login" type="submit"
				value="Want to Add a Blog?, Go to Author Login">
		</form>



	</center>
	</section>
	<br>
	<br>
</body>
</html>