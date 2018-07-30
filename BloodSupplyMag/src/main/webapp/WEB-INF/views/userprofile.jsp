<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Jiamin Shang 6225 Final Project</TITLE>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- <link rel='stylesheet' href="./css/style.css"> -->
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
<script
	src='https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
<script
	src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>

</HEAD>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:choose>
		<c:when test="${usertype == 1}">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextPath}/admin/home.htm">Dashboard</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/admin/home.htm">Home</a></li>
					<li><a href="${contextPath}/admin/adduser.htm">Create
							staff</a></li>
					<li><a href="${contextPath}/admin/addorgan.htm">Create
							Organization</a></li>
					<li class="active"><a href="${contextPath}/user/profile.htm">Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/user/profile.htm"><span
							class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
					<li><a href="${contextPath}/logout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
			</nav>
		</c:when>
		<c:when test="${usertype == 2}">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextPath}/user/home.htm">User
						Center</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/user/home.htm">Home</a></li>
					<li><a href="${contextPath}/user/donate.htm">Donate</a></li>
					<li><a href="${contextPath}/user/use.htm">Use</a></li>
					<li><a href="${contextPath}/user/history.htm">History</a></li>
					<li class="active"><a href="${contextPath}/user/profile.htm">Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/user/profile.htm"><span
							class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
					<li><a href="${contextPath}/logout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
			</nav>
		</c:when>
		<c:when test="${usertype == 3}">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextPath}/nurse/home.htm">${user.organ.organName }</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/nurse/home.htm">Work Area</a></li>
					<li class="active"><a href="${contextPath}/user/profile.htm">Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/user/profile.htm"><span
							class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
					<li><a href="${contextPath}/logout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
			</nav>
		</c:when>
		<c:when test="${usertype == 4}">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextPath}/lab/home.htm">${user.organ.organName }</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/lab/home.htm">Work Area</a></li>
					<li class="active"><a href="${contextPath}/user/profile.htm">Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/user/profile.htm"><span
							class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
					<li><a href="${contextPath}/logout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
			</nav>
		</c:when>
		<c:when test="${usertype == 5}">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextPath}/deliver/home.htm">Distribution</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/deliver/home.htm">Work Area</a></li>
					<li class="active"><a href="${contextPath}/user/profile.htm">Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/user/profile.htm"><span
							class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
					<li><a href="${contextPath}/logout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
			</nav>
		</c:when>
		<c:when test="${usertype == 6}">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextPath}/bmc/home.htm">${user.organ.organName }</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/bmc/home.htm">Work Area</a></li>
					<li><a href="${contextPath}/bmc/info.htm">Data Panel</a></li>
					<li class="active"><a href="${contextPath}/user/profile.htm">Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${contextPath}/user/profile.htm"><span
							class="glyphicon glyphicon-user"></span> ${user.username}</a></li>
					<li><a href="${contextPath}/logout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
			</nav>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	<div class="content container">
		<c:if test="${errorMessage != null}">
			<div class="alert alert-danger content" role="alert">
				<h2 class='text-center'>
					<b>${errorMessage}</b>
				</h2>
				<br />
			</div>
		</c:if>

		<h2 class='text-center'>
			<br /> <b>Profile</b>
		</h2>
		<br />
		<ul class="nav nav-tabs col-lg-offset-4 col-lg-4">
			<li class="active"><a data-toggle="tab" href="#home">Info</a></li>
			<li><a data-toggle="tab" href="#menu1">Security</a></li>
		</ul>
		<div class="tab-content">
			<div id="home" class="tab-pane fade in active">
				<div class='text-center row'>
					<img class="col-sm-offset-5 col-sm-2 img-circle" src="${contextPath}/${avator}"></img>
					<form:form commandName="fileupload" action="${contextPath}/uploadavator.htm" method="post"
						enctype="multipart/form-data">
						<input type="file" class="col-sm-offset-5 col-sm-2" name="photo" />
						<input type="submit"
							class="btn btn-info col-sm-offset-5 col-sm-2" value="Upload" />
					</form:form>
				</div>
				<form class="row content form-horizontal" method="post"
					action="${contextPath}/user/profile.htm">
					<div class="col-lg-offset-3 col-lg-6  row">
						<br /> <br />
						<div class="form-group">
							<label class="control-label col-sm-3" for="username">Username:</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="username"
									value="${user.username}" disabled="true" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="firstname">First
								name:</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="firstname"
									value="${user.firstName}" disabled="true" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="lastname">Last
								name:</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="lastname"
									value="${user.lastName}" disabled="true" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="gender">Gender:</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="gender"
									value="${user.gender}" disabled="true" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="dob">Date of
								birth:</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="dob"
									value="${user.dateOfBirth}" disabled="true" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="email">Email:</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="email"
									value="${user.email}" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="phone">Phone:</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" name="phone"
									value="${user.phone}" />
							</div>
						</div>
						<br /> <br />
						<button type="submit"
							class="btn btn-info col-sm-offset-5 col-sm-2">Update</button>
					</div>
				</form>
			</div>
			<div id="menu1" class="tab-pane fade">
				<form class="row content form-horizontal" method="post"
					action="${contextPath}/user/updatepassword.htm">
					<div class="col-lg-offset-3 col-lg-6  row">
						<br /> <br />
						<div class="form-group">
							<label class="control-label col-sm-4" for="cpassword">Current
								Password:</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" name="cpassword" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="npassword">New
								Password:</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" name="npassword" />
							</div>
						</div>
						<br /> <br />
						<button type="submit"
							class="btn btn-info col-sm-offset-5 col-sm-2">Save</button>
					</div>
				</form>
			</div>
		</div>

	</div>
</body>
</HTML>