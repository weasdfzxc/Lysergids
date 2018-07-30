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
	$(function() {
		$("#otype")
				.change(
						function() {
							//var params = '{otype:"' + $(this).val() + '"}';
							var params = 'otype=' + $(this).val();
							$.ajax({
										type : "POST",
										url : "${contextPath}/ajax/organ",
										data : params,
										dataType : "text",
										//contentType : "application/json; charset=utf-8",
										contentType : "application/x-www-form-urlencoded; charset=ISO-8859-1",
										beforeSend : function(XMLHttpRequest) {
											$('#attachlabel')
													.text(
															"Attached to(fetching data...):");
										},
										success : function(msg) {
											$('#attachlabel').text(
													"Attached to:");
											$("#pareneto option").each(
													function() {
														$(this).remove();
													});
											$(msg).appendTo("#pareneto");
											//$(eval("(" + msg + ")").d).appendTo("#pareneto");
										},
										error : function(xhr, msg, e) {
											alert("error");
										}
									});
						});
	});
</script>
</HEAD>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${contextPath}/admin/home.htm">Dashboard</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${contextPath}/admin/home.htm">Home</a></li>
			<li><a href="${contextPath}/admin/adduser.htm">Create staff</a></li>
			<li class="active"><a href="${contextPath}/admin/addorgan.htm">Create
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
		<c:if test="${errorMessage != null}">
			<div class="alert alert-danger content" role="alert">
				<h2 class='text-center'>
					<b>${errorMessage}</b>
				</h2>
				<br />
			</div>
		</c:if>
		<c:if test="${successMessage != null}">
		<div class="alert alert-success content" role="alert">
			<h2 class='text-center'>
				<b>${successMessage}</b>
			</h2>
			<br />
		</div>
	</c:if>
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<form class="row content form-horizontal" method="post"
			action="${contextPath}/admin/addorgan.htm">
			<h2 class='text-center'>
				<br /> <br /> <br /> <br /> <br /> <br /> <b>Create new
					Organization</b>
			</h2>
			<br /> <br />
			<div class="col-lg-offset-2 col-lg-8  row">
				<div class="form-group col-lg-12">
					<label class="control-label col-sm-4" for="title">Organization
						Name:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="oname" id="oname"
							required="required">
					</div>
				</div>
				<div class="row col-lg-12">
					<div class="col-lg-offset-1 col-lg-4">
						<div class="form-group">
							<label for="otype">Select Organization Type:</label> <select
								class="form-control" id="otype" name="otype">
								<option value="none">-Select-</option>
								<option value="bmc">BloodManageCenter</option>
								<option value="bb">BloodBank</option>
								<option value="clinic">Clinic</option>
							</select>
						</div>
					</div>
					<div class="col-lg-offset-2  col-lg-4">
						<div class="form-group">
							<label for="pareneto" id="attachlabel">Select type first:</label>
							<select class="form-control" id="pareneto" name="pareneto">
								<option value="none">---</option>
							</select>
						</div>
					</div>
				</div>
				<br /> <br />
				<br /> <br />
				<button type="submit" class="btn btn-info col-sm-offset-3 col-sm-2">Create</button>
				<a href="${contextPath}/admin/home.htm" class="btn btn-default col-sm-offset-2 col-sm-2">Cancel</a>
			</div>
		</form>
	</div>
</body>
</HTML>