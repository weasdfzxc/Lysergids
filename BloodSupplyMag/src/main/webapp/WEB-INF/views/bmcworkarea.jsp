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
			<a class="navbar-brand" href="${contextPath}/bmc/home.htm">${user.organ.organName }</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${contextPath}/bmc/home.htm">Work
					Area</a></li>
			<li><a href="${contextPath}/bmc/info.htm">Data Panel</a></li>
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
			<div class="col-md-offset-2 col-md-8"">
				<div class="panel panel-info">
					<div class="panel-heading">Blood shortage list</div>
					<div class="panel-body databody">
						<table class=" table table-hover">
							<tr class="warning">
								<th></th>
								<th>Request Date</th>
								<th>Status</th>
								<th>Blood Type</th>
								<th>Quantities</th>
							</tr>
							<c:forEach items="${wrubbsList}" var="wrubbs">
								<tr>
									<td><span class="glyphicon glyphicon-chevron-down"
										onclick="spandRows(this)"></span></td>
									<td>${wrubbs.wru.requestDate}</td>
									<td>${wrubbs.wru.status}</td>
									<td>${wrubbs.wru.person.bloodType}</td>
									<td>${wrubbs.wru.quantity}</td>
								</tr>

								<tr class="dropDownTextArea">
									<td colspan="5">
										<form class="row content form-inline" method="post"
											action="${contextPath}/bmc/transfer.htm">
											<input type="hidden" name="wruid" value="${wrubbs.wru.wrId}">
											<div class="col-md-offset-1 col-md-10 row">
												<label for="bb">Select BloodBank:</label>
												<div class="input-append">
													<select class="span2 form-control" id="bb" name="bb" >
														<option value="0">-Select-</option>
														<c:forEach items="${wrubbs.bbsList}" var="bbs">
															<option value="${bbs.bbID}">${bbs.bbName}  type${bbs.bloodType} blood inventory:${bbs.quantity}</option>
														</c:forEach>
													</select>
													<button class="btn btn-success" type="submit">Transfer</button>
												</div>
											</div>
										</form>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</HTML>