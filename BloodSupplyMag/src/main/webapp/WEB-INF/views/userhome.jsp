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
				<a class="navbar-brand" href="${contextPath}/user/home.htm">User Center</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="${contextPath}/user/home.htm">Home</a></li>
				<li><a href="${contextPath}/user/donate.htm">Donate</a></li>
				<li><a href="${contextPath}/user/use.htm">Use</a></li>
				<li><a href="${contextPath}/user/history.htm">History</a></li>
				<li><a href="${contextPath}/user/profile.htm">Profile</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/user/profile.htm"><span class="glyphicon glyphicon-user"></span>
						${user.username}</a></li>
				<li><a href="${contextPath}/logout"><span class="glyphicon glyphicon-log-in"></span>
						Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<br /> <br /> <br /> <br /> <br /> <br />
		<h1 class='text-center'>
			<b>Hi, <c:out value="${user.firstName}"></c:out> Welcome to user center</b>
		</h1>

		<br /> <br /> <br /> <br />
		<h3 class='text-center'>Choose your option.</h3>
		<div class="row">
			<a href="${contextPath}/user/donate.htm" class="btn btn-info col-sm-offset-4 col-sm-4">I want to donate blood</a><br /> <br /> <br /> 
			<a href="${contextPath}/user/use.htm"
				class="btn btn-info col-sm-offset-4 col-sm-4">I need blood</a><br /> <br /> <br /> 
			<a href="${contextPath}/user/history.htm"
				class="btn btn-info col-sm-offset-4 col-sm-4">View my information</a>
		</div>
	</div>
</body>
</html>