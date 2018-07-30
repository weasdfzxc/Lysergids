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
			<a class="navbar-brand" href="${contextPath}/nurse/home.htm">${user.organ.organName }</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${contextPath}/nurse/home.htm">Work
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
				<li><a data-toggle="tab" href="#menu2">area3</a></li>
			</ul>

			<div class="tab-content  col-md-offset-2 col-md-8"">
				<div id="home" class="tab-pane fade in active">
					<div class="panel panel-info">
						<div class="panel-heading">Request pool</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
									<th>Request date</th>
									<th>Aim</th>
									<th>Status</th>
									<th>Quantities</th>
								</tr>
								<c:forEach items="${wrList}" var="wr">
									<tr>
										<td>${wr.requestDate}</td>
										<td>${wr.message}</td>
										<td>${wr.status}</td>
										<td>${wr.quantity}</td>
										<td><a
											href="${contextPath}/nurse/assigntome.htm?wrid=${wr.wrId}"
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
									<th>Request Date</th>
									<th>Request Type</th>
									<th>Status</th>
									<th>Blood Type</th>
									<th>Quantities</th>
									<td></td>
								</tr>
								<c:forEach items="${wrsList}" var="wrs">
									<tr>
										<td>${wrs.requestDate}</td>
										<td>${wrs.message}</td>
										<td>${wrs.status}</td>
										<td>${wrs.person.bloodType}</td>
										<td>${wrs.quantity}</td>
										<td><a
											href="${contextPath}/nurse/viewuser.htm?pid=${wrs.person.pID}"
											class="btn btn-info btn-xs">detail</a> <c:choose>
												<c:when test="${wrd.message == \"Donate\"}" >
													<a href="${contextPath}/nurse/sendtest.htm?wrid=${wrs.wrId}"
											class="btn btn-success btn-xs">send to test</a>
												</c:when>
												<c:when test="${wrd.message == \"Use\"}" >
												<a href="${contextPath}/nurse/sendrequire.htm?wrid=${wrs.wrId}"
											class="btn btn-success btn-xs">require</a>
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>  <a
											href="${contextPath}/nurse/deny.htm?wrid=${wrs.wrId}"
											class="btn btn-danger btn-xs">deny</a></td>
									</tr>
								</c:forEach>
								<%-- <c:forEach items="${wruList}" var="wru">
									<tr>
										<td>${wru.requestDate}</td>
										<td>${wru.message}</td>
										<td>${wru.status}</td>
										<td>${wru.person.bloodType}</td>
										<td>${wru.quantity}</td>
										<td><a
											href="${contextPath}/nurse/viewuser.htm?pid=${wru.person.pID}"
											class="btn btn-info btn-xs">detail</a> <a
											href="${contextPath}/nurse/sendrequire.htm?pid=${wru.wruId}"
											class="btn btn-success btn-xs">require</a> <a
											href="${contextPath}/nurse/deny.htm?pid=${wru.wrdId}"
											class="btn btn-danger btn-xs">deny</a></td>
									</tr>
								</c:forEach> --%>
							</table>
						</div>
					</div>
				</div>
				<div id="menu2" class="tab-pane fade">
					<div class="panel panel-info">
						<div class="panel-heading">Blood draw waiting list</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
									<th></th>
									<th>Request Date</th>
									<th>Status</th>
									<th>Blood Type</th>
									<th>Quantities</th>
									<td></td>
								</tr>
								<c:forEach items="${wrdtList}" var="wrdt">
									<tr>
										<td><span class="glyphicon glyphicon-chevron-down"
											onclick="spandRows(this)"></span></td>
										<td>${wrdt.requestDate}</td>
										<td>${wrdt.status}</td>
										<td>${wrdt.person.bloodType}</td>
										<td>${wrdt.quantity}</td>
										<td><a
											href="${contextPath}/nurse/drawblood.htm?wrid=${wrdt.wrId}"
											class="btn btn-success btn-xs">draw</a> <a
											href="${contextPath}/nurse/deny.htm?wrid=${wrdt.wrId}"
											class="btn btn-danger btn-xs">deny</a></td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="5">Test result: <c:forEach
												items="${wrdt.person.vitalSignHistory}" var="vs"
												varStatus="i" begin="${i.count-1}" end="${i.count-1}">
											Date: ${vs.date}, Hemoglobin: ${vs.hemoglobin}, Infection: ${vs.infection}, Diabetes: ${vs.diabetes}, TempCondition: ${vs.tempCondition}, PermCondition: ${vs。permCondition}.
										</c:forEach>
										</td>
									</tr>
								</c:forEach>
								<%-- <c:forEach items="${wrddList}" var="wrdd">
									<tr>
										<td>${wrdd.requestDate}</td>
										<td>${wrdd.message}</td>
										<td>${wrdd.status}</td>
										<td>${wrdd.person.bloodType}</td>
										<td>${wrdd.quantity}</td>
										<td><a href="${contextPath}/nurse/sendbb.htm?pid=${wrdd.wrdId}"
														class="btn btn-success btn-xs">send to bloodbank</a>
										</td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="5">Test result: <c:forEach
												items="${wrdd.person.vitalSignHistory}" var="vs" varStatus="i" begin="${i.count-1}"  end="${i.count-1}">
											Date: ${vs.date}, Hemoglobin: ${vs.hemoglobin}, Infection: ${vs.infection}, Diabetes: ${vs.diabetes}, TempCondition: ${vs.tempCondition}, PermCondition: ${vs。permCondition}.
										</c:forEach>
										</td>
									</tr>
								</c:forEach> --%>
							</table>
						</div>
					</div>
				</div>
				<div id="menu3" class="tab-pane fade">
					<div class="panel panel-info">
						<div class="panel-heading">Blood use waiting list</div>
						<div class="panel-body databody">
							<table class=" table table-hover">
								<tr class="warning">
									<th></th>
									<th>Request Date</th>
									<th>Status</th>
									<th>Blood Type</th>
									<th>Quantities</th>
									<td></td>
								</tr>
								<c:forEach items="${wrdtList}" var="wrdt">
									<tr>
										<td><span class="glyphicon glyphicon-chevron-down"
											onclick="spandRows(this)"></span></td>
										<td>${wrdt.requestDate}</td>
										<td>${wrdt.status}</td>
										<td>${wrdt.person.bloodType}</td>
										<td>${wrdt.quantity}</td>
										<td><a
											href="${contextPath}/nurse/drawblood.htm?wrid=${wrdt.wrId}"
											class="btn btn-success btn-xs">draw</a> <a
											href="${contextPath}/nurse/deny.htm?wrid=${wrdt.wrId}"
											class="btn btn-danger btn-xs">deny</a></td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="5">Test result: <c:forEach
												items="${wrdt.person.vitalSignHistory}" var="vs"
												varStatus="i" begin="${i.count-1}" end="${i.count-1}">
											Date: ${vs.date}, Hemoglobin: ${vs.hemoglobin}, Infection: ${vs.infection}, Diabetes: ${vs.diabetes}, TempCondition: ${vs.tempCondition}, PermCondition: ${vs。permCondition}.
										</c:forEach>
										</td>
									</tr>
								</c:forEach>
								<%-- <c:forEach items="${wrddList}" var="wrdd">
									<tr>
										<td>${wrdd.requestDate}</td>
										<td>${wrdd.message}</td>
										<td>${wrdd.status}</td>
										<td>${wrdd.person.bloodType}</td>
										<td>${wrdd.quantity}</td>
										<td><a href="${contextPath}/nurse/sendbb.htm?pid=${wrdd.wrdId}"
														class="btn btn-success btn-xs">send to bloodbank</a>
										</td>
									</tr>
									<tr class="dropDownTextArea">
										<td colspan="5">Test result: <c:forEach
												items="${wrdd.person.vitalSignHistory}" var="vs" varStatus="i" begin="${i.count-1}"  end="${i.count-1}">
											Date: ${vs.date}, Hemoglobin: ${vs.hemoglobin}, Infection: ${vs.infection}, Diabetes: ${vs.diabetes}, TempCondition: ${vs.tempCondition}, PermCondition: ${vs。permCondition}.
										</c:forEach>
										</td>
									</tr>
								</c:forEach> --%>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</HTML>