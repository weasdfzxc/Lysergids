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
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${contextPath}/lab/home.htm">${user.organ.organName }</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${contextPath}/lab/home.htm">Work
					Area</a></li>
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
				<li class="active"><a data-toggle="tab" href="#home">area1</a></li>
				<li><a data-toggle="tab" href="#menu1">area2</a></li>
			</ul>

			<div class="tab-content  col-md-offset-2 col-md-8"">
				<div id="home" class="tab-pane fade in active">
					<div class="panel panel-info">
						<div class="panel-heading">Request pool</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
									<th>Request date</th>
									<th>Donor</th>
									<th>Blood type</th>
									<th>Status</th>
									<th></th>
								</tr>
								<c:forEach items="${lwrdList}" var="lwr">
									<tr>
										<td>${lwr.requestDate}</td>
										<td>${lwr.person.firstName}</td>
										<td>${lwr.person.bloodType}</td>
										<td>${lwr.status}</td>
										<td><a
											href="${contextPath}/lab/assigntome.htm?wrid=${lwr.wrId}"
											class="btn btn-danger btn-xs">Assign to me</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div id="menu1" class="tab-pane fade">
					<div class="panel panel-info">
						<div class="panel-heading">Request queue</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
								<th></th>
									<th>Request Date</th>
									<th>Donor</th>
									<th>Status</th>
									<th>Blood Type</th>
									<th></th>
								</tr>
								<c:forEach items="${lwrsList}" var="lwrs">
									<tr>
										<td><c:if test="${lwrs.status eq \"Test\"}"><span class="glyphicon glyphicon-chevron-down"
											onclick="spandRows(this)"></span></c:if></td>
										<td>${lwrs.requestDate}</td>
										<td>${lwrs.person.firstName}</td>
										<td>${lwrs.status}</td>
										<td>${lwrs.person.bloodType}</td>
										<td> <c:choose>
												<c:when test="${lwrs.status eq \"Pending(labassistant)\"}" >
													<a href="${contextPath}/lab/test.htm?wrid=${lwrs.wrId}"
											class="btn btn-success btn-xs btn-block">Test</a>
												</c:when>
												<c:when test="${lwrs.status eq \"Test\"}" >
												<a href="${contextPath}/lab/sendback.htm?wrid=${lwrs.wrId}"
											class="btn btn-success btn-xs btn-block">Send report</a>
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose> 
										</td>
									</tr>
									<c:if test="${lwrs.status eq \"Test\"}">
									<tr class="dropDownTextArea">
										<td colspan="6">Test result: <c:forEach
												items="${lwrs.person.vitalSignHistory}" var="vs"
												varStatus="i" begin="${lwrs.person.vitalSignHistory.size()-1}">
											Date: ${vs.date}, Healthy: ${vs.isHealthy}, Hemoglobin: ${vs.hemoglobin}, Infection: ${vs.infection}, Diabetes: ${vs.diabetes}, TempCondition: ${vs.tempCondition}, PermCondition: ${vs.permCondition}.
										</c:forEach>
										</td>
									</tr>
									</c:if>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
</HTML>