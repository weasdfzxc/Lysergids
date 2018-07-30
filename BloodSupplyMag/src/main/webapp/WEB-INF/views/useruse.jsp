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
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${contextPath}/user/home.htm">User
				Center</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${contextPath}/user/home.htm">Home</a></li>
			<li><a href="${contextPath}/user/donate.htm">Donate</a></li>
			<li class="active"><a href="${contextPath}/user/use.htm">Use</a></li>
			<li><a href="${contextPath}/user/history.htm">History</a></li>
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
	<div class="content container">
		<c:if test="${errorMessage != null}">
			<div class="alert alert-danger content" role="alert">
				<h2 class='text-center'>
					<b>${errorMessage}</b>
				</h2>
				<br />
			</div>
		</c:if>
		<form class="row form-horizontal" method="post"
			action="${contextPath}/user/use.htm">
			<h2 class='text-center'>
				<br /> <br /> <br /> <br /> <br /> <br /> <b>Select
					quantity and clinic</b>
			</h2>
			<br /> <br />
			<div class="col-lg-offset-3 col-lg-6  row">
				
				<div class="col-lg-offset-1 col-lg-10">
						<div class="form-group">
							<label for="otype">Select Clinic:</label> <select
								class="form-control" id="oid" name="oid">
								<option value="0">-Select-</option>
								<c:forEach items="${clinicList}" var="clinic" varStatus="si">
									<option value="${clinic.oid}">${clinic.organName}</option>
								</c:forEach>
							</select>
						</div>
				</div>
				<div class="col-lg-offset-1 col-lg-10">
					<div class="form-group">
						<label for="donations">required quantities(ml):</label> <input type="number"
							class="form-control" id="quantities" name="quantities" min="200" max="5000">
					</div>
				</div>
				<br /> <br /> <br /> <br />
				<button type="submit" class="btn btn-info col-sm-offset-3 col-sm-2">Submit</button>
				<a href="${contextPath}/user/home.htm" class="btn btn-default col-sm-offset-2 col-sm-2">Cancel</a>
			</div>
		</form>
	</div>
</body>
</HTML>