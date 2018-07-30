<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Welcome to Blood Donate System</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<!-- <link rel='stylesheet' href="../css/style.css"> -->
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
<script
	src='https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
<script
	src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>
</head>
<body class="content">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${contextPath}/index.htm">Blood
					Manage System</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="${contextPath}/index.htm">Home</a></li>
		</div>
	</nav>
	<div class="container">

		<br /> <br /> <br /> <br /> <br /> <br />
		<h1 class='text-center'>
			<b>Welcome to Blood Manage Center</b>
		</h1>

		<br /> <br /> <br /> <br />
		<h3 class='text-center'>If you donate or use blood first time,
			Please register first.</h3>

		<h3 class='text-center'>If you already have account login now.</h3>
		<br /> <br />
		<div class="row">
			<a href="./login.htm" class="btn btn-info col-sm-offset-4 col-sm-4">Log
				in</a><br /> <br /> <br /> <a href="./signup.htm"
				class="btn btn-default col-sm-offset-4 col-sm-4">Sign up</a>
		</div>
	</div>
</body>
</html>