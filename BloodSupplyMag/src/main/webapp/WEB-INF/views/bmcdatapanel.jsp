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
<body onload="initial()">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${contextPath}/bmc/home.htm">${user.organ.organName }</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${contextPath}/bmc/home.htm">Work
					Area</a></li>
			<li class="active"><a href="${contextPath}/bmc/info.htm">Data Panel</a></li>
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
		<h2 class='text-center'>
			<b>Blood inventory</b>
		</h2>
		<br /> <br />
		<div class="row">
			<c:forEach items="${bbiList}" var="bbi">
			<div class="col-md-4">
				<div class="panel panel-info">
					<div class="panel-heading">${bbi.bbName}</div>
					<div class="panel-body databody row">
						<div class="col-md-3 col-xs-3">
                        <p><strong>A:</strong></p>
                        <p><strong>B:</strong></p>
                        <p><strong>AB:</strong></p>
                        <p><strong>O:</strong></p>
                    </div>
                    <div class="col-md-6 col-xs-6">
                        <p>${bbi.typea}ml</p>
                        <p>${bbi.typeb}ml</p>
                        <p>${bbi.typeab}ml</p>
                        <p>${bbi.typeo}ml</p>
                    </div>
                    <div class="col-md-3 col-xs-3">
                        <p>${bbi.typearatio}</p>
                        <p>${bbi.typebratio}</p>
                        <p>${bbi.typeabratio}</p>
                        <p>${bbi.typeoratio}</p>
                    </div>
					</div>
				</div>
			</div>
			</c:forEach>
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-offset-9 col-md-2">
						<a href="${contextPath}/bmc/report.xls"
										class="btn btn-success btn-block">Export report</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</HTML>