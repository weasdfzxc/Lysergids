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
<script type="text/javascript">
	var spandFlag = false;
	function initial() {
		for (var i = 0; i < document.getElementsByClassName("dropDownTextArea").length; i++) {
			document.getElementsByClassName("dropDownTextArea")[i].style.display = "none";
		}

	}
	function spandRows(that) {
		if (spandFlag == false) {
			that.parentNode.parentNode.nextElementSibling.style.display = "table-row";
			spandFlag = true;
		} else {
			that.parentNode.parentNode.nextElementSibling.style.display = "none";
			spandFlag = false;
		}

	}
</script>
</HEAD>
<body onload="initial()">
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
					<li class="active"><a href="${contextPath}/user/history.htm">History</a></li>
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
		</c:when>
		<c:when test="${usertype == 3}">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextPath}/nurse/home.htm">${user.organ.organName }</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/nurse/home.htm">Work Area</a></li>
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
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	<div class="content container-fluid">
		<c:if test="${errorMessage != null}">
			<div class="alert alert-danger content" role="alert">
				<h2 class='text-center'>
					<b>${errorMessage}</b>
				</h2>
				<br />
			</div>
		</c:if>
		<h2 class='text-center'>
			<b>History records</b>
		</h2>
		<br /> <br />
		<div class="row">
			<ul class="nav nav-tabs col-md-offset-3 col-md-6">
				<li class="active"><a data-toggle="tab" href="#home">blood
						record</a></li>
				<li><a data-toggle="tab" href="#menu1">vital sign</a></li>
			</ul>

			<div class="tab-content  col-md-offset-2 col-md-8"">
				<div id="home" class="tab-pane fade in active">
					<div class="panel panel-info">
						<div class="panel-heading">Blood donate and use history</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
									<th></th>
									<th>Request date</th>
									<th>Request type</th>
									<th>Status</th>
									<th>Quantities</th>
								</tr>
								<c:forEach items="${uwrdList}" var="wrd">
									<tr>
										<td><span class="glyphicon glyphicon-chevron-down"
											onclick="spandRows(this)"></span></td>
										<td>${wrd.requestDate}</td>
										<td>${wrd.message}</td>
										<td>${wrd.status}</td>
										<td>${wrd.quantity}</td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="5">blood used by <c:out
												value="${wrd.blood.usePerson.firstName}">no one</c:out>
										</td>
									</tr>
								</c:forEach>
								<c:forEach items="${uwruList}" var="wru">
									<tr>
										<td><span class="glyphicon glyphicon-chevron-down"
											onclick="spandRows(this)"></span></td>
										<td>${wru.requestDate}</td>
										<td>${wru.message}</td>
										<td>${wru.status}</td>
										<td>${wru.quantity}</td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="5">blood provided by: <c:forEach
												items="${wru.useBloodList}" var="ubl">
											${ubl.donor.firstName} ,
										</c:forEach>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div id="menu1" class="tab-pane fade">
					<div class="panel panel-default">
						<div class="panel-heading">Vital sign records</div>
						<div class="panel-body databody">
							<table class=" table table-hover" id="myTable">
								<tr class="warning">
									<th></th>
									<th>Date</th>
									<th>Healthy?</th>
								</tr>
								<c:forEach items="${vsList}" var="vsl">
									<tr>
										<td><span class="glyphicon glyphicon-chevron-down"
											onclick="spandRows(this)"></span></td>
										<td>${vsl.date}</td>
										<td>${vsl.isHealthy}</td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="3" class="row"><span
											class="col-md-offset-0 col-md-3">Blood type:
												${vsl.bloodtype}</span> <span class="col-md-offset-1 col-md-3">Hemoglobin:
												${vsl.hemoglobin}</span> <span class="col-md-offset-1 col-md-4">Infection:
												${vsl.infection}</span> <span class="col-md-offset-0 col-md-3">Diabetes:
												${vsl.diabetes}</span> <span class="col-md-offset-1 col-md-3">Other
												temporary condition: ${vsl.tempCondition}</span> <span
											class="col-md-offset-1 col-md-4">Other permanent
												condition: ${vsl.permCondition}</span></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="panel-footer">
							<div class="row">
								<div class="col-md-offset-9 col-md-2">
									<a href="${contextPath}/user/report.xls?pid=${idforexcel}"
										class="btn btn-success btn-block">Export report</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</HTML>