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
			<a class="navbar-brand" href="${contextPath}/admin/home.htm">Dashboard</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${contextPath}/admin/home.htm">Home</a></li>
			<li><a href="${contextPath}/admin/adduser.htm">Create staff</a></li>
			<li><a href="${contextPath}/admin/addorgan.htm">Create
					Organization</a></li>
			<li><a href="${contextPath}/user/profile.htm">Profile</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${contextPath}/user/profile.htm"><span
					class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
			<li><a href="${contextPath}/logout"><span
					class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		</ul>
	</div>
	</nav>
	<div class="container">
		<br /> <br /> <br /> <br /> <br /> <br />
		<h1 class='text-center'>
			<b>Hi, <c:out value="${user.firstName}"></c:out> Welcome to Dashboard</b>
		</h1>

		<br /> <br /> <br /> <br />
		<h3 class='text-center'>Choose your option.</h3>
		<div class="row">
			<a href="${contextPath}/admin/home.htm"" class="btn btn-info col-sm-offset-4 col-sm-4">Manage information</a><br /> <br /> <br /> 
			<a href="${contextPath}/admin/adduser.htm"
				class="btn btn-info col-sm-offset-4 col-sm-4">Create staff</a><br /> <br /> <br /> 
			<a href="${contextPath}/admin/addorgan.htm"
				class="btn btn-info col-sm-offset-4 col-sm-4">Create Organization</a>
		</div>
	</div>
</body>
</html>