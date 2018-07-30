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
			<a class="navbar-brand" href="${contextPath}/admin/home.htm">Dashboard</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${contextPath}/admin/home.htm">Home</a></li>
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
			<br /> <br /> <b>Info</b>
		</h2>
		<br /> <br />
		<ul class="nav nav-tabs col-lg-offset-2 col-lg-8">
			<li class="active"><a data-toggle="tab" href="#home">User</a></li>
			<li><a data-toggle="tab" href="#menu1">Organization</a></li>
			<li><a data-toggle="tab" href="#menu2">Work requests</a></li>
		</ul>
		<div class="tab-content">
			<div id="home" class="tab-pane fade in active row">
				<div class="col-lg-offset-2 col-lg-8">
					<div class="panel panel-info">
						<div class="panel-heading">User List</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
									<th></th>
									<th>UID</th>
									<th>Username</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Role</th>
									<th></th>
								</tr>
								<c:forEach items="${userList}" var="user">
									<tr>
										<td><span class="glyphicon glyphicon-chevron-down"
											onclick="spandRows(this)"></span></td>
										<td>${user.pID}</td>
										<td>${user.username}</td>
										<td>${user.firstName}</td>
										<td>${user.lastName}</td>
										<td>${user.role}</td>
										<td><c:choose>
												<c:when test="${user.status eq \"active\"}">
													<a
														href="${contextPath}/admin/disableuser.htm?pid=${user.pID}"
														class="btn btn-danger btn-xs btn-block">Disable</a>
												</c:when>
												<c:otherwise>
													<a
														href="${contextPath}/admin/activeuser.htm?pid=${user.pID}"
														class="btn btn-success btn-xs btn-block">Active </a>
												</c:otherwise>
											</c:choose></td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="7"><span class="col-md-offset-0 col-md-2">gender:
												${user.gender}</span> <span class="col-md-offset-1 col-md-2">Date
												Of Birth: ${user.dateOfBirth}</span> <span
											class="col-md-offset-1 col-md-2">Phone: ${user.phone}</span>
											<span class="col-md-offset-1 col-md-2">Email:
												${user.email}</span></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="menu1" class="tab-pane fade row">
				<div class="col-lg-offset-2 col-lg-8">
					<div class="panel panel-info">
						<div class="panel-heading">Organization List</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
									<th></th>
									<th>OID</th>
									<th>Organization name</th>
									<th>Organization type</th>
									<th></th>
								</tr>
								<c:forEach items="${organList}" var="organ">
									<tr>
										<td><span class="glyphicon glyphicon-chevron-down"
											onclick="spandRows(this)"></span></td>
										<td>${organ.oid}</td>
										<td>${organ.organName}</td>
										<td>${organ.organType}</td>
										<td><a
											href="${contextPath}/admin/deleteorgan.htm?oid=${organ.oid}"
											class="btn btn-danger btn-xs btn-block">Delete</a></td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="5">User belong to it: <c:forEach
												items="${organ.userSet}" var="ou">
											${ou.username} ,
										</c:forEach> 
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="menu2" class="tab-pane fade row">
				<div class="col-lg-offset-2 col-lg-8">
					<div class="panel panel-info">
						<div class="panel-heading">Work requests queue</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
									<th></th>
									<th>Request date</th>
									<th>Request type</th>
									<th>Status</th>
									<th>Quantities</th>
								</tr>
								<c:forEach items="${wrdList}" var="wrd">
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
												value="${wrd.blood.usePerson.firstName}">no one</c:out><br/>
												request is responsible by
											<c:forEach items="${wrd.userSet}" var="users">
											Name:${users.firstName} Role:${users.role},
											</c:forEach>
										</td>
									</tr>
								</c:forEach>
								<c:forEach items="${wruList}" var="wru">
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
										</c:forEach><br/>
												request is responsible by
											<c:forEach items="${wrd.userSet}" var="users">
											Name:${users.firstName} Role:${users.role},
											</c:forEach>
										</td>
									</tr>
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