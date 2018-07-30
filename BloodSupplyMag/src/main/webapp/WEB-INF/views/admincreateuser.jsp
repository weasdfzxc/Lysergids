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
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta name='viewport' content='width=device-width, initial-scale=1'>
<!-- <link rel='stylesheet' href="./css/style.css"> -->
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
<script
	src='https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
<script
	src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>
<script type="text/javascript">
	$(function() {
		$("#urole").change(	function() {
			var params = 'urole=' + $(this).val();
				$.ajax({
						type : "POST",
						url : "${contextPath}/ajax/user",
						data : params,
						dataType : "text",
						//contentType : "application/json; charset=utf-8",
						contentType : "application/x-www-form-urlencoded; charset=ISO-8859-1",
						beforeSend : function(XMLHttpRequest) {
						$('#attachlabel').text("Attached to(fetching data...):");
						},
						success : function(msg) {
						$('#attachlabel').text("Attached to:");
						$("#organ option").each(function() {
							$(this).remove();});
						$(msg).appendTo("#organ");
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
			<li class="active"><a href="${contextPath}/admin/adduser.htm">Create
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
	<div class=" container">
		<form class="row" action="${contextPath}/admin/adduser.htm"
			method="post">

			<h2 class='text-center'>
				<b>Create User</b>
			</h2>
			<br /> <br />
			<div class="row col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 ">
				<div class="row form-group col-lg-12 ">
					<label class="control-label col-lg-3" for="username">Username:</label>
					<div class="col-lg-9">
						<input type="text" class="form-control" name="username" required="required"/>
					</div>
				</div>
				<div class="row form-group col-lg-12">
					<label class="control-label col-lg-3" for="password">Password:</label>
					<div class="col-lg-9">
						<input type="password" class="form-control" name="password" required="required"/>
					</div>
				</div>
				<div class="row form-group col-lg-12">
					<label class="control-label col-lg-3" for="firstName">First
						Name:</label>
					<div class="col-lg-9">
						<input type="text" class="form-control" name="firstName" required="required"/>
					</div>
				</div>
				<div class="row form-group col-lg-12">
					<label class="control-label col-lg-3" for="lastName">Last
						Name:</label>
					<div class="col-lg-9">
						<input type="text" class="form-control" name="lastName" required="required"/>
					</div>
				</div>
				<div class="row form-group col-lg-12">
					<label class="control-label col-lg-4" for="gender">Gender:</label>
					<select name="gender" class="col-lg-7">
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select>
				</div>
				<div class="row form-group col-lg-12">
					<label class="control-label col-sm-3" for="dateOfBirth">Date
						Of Birth:</label>
					<div class="col-lg-9">
						<input value="" class="form-control" name="dateOfBirth"
							type="date" required="required"/>
					</div>
				</div>
				<div class="row form-group col-lg-12">
					<label class="control-label col-lg-3" for="email">Email:</label>
					<div class="col-lg-9">
						<input value="" class="form-control" name="email" type="email"
							required="required" required="required"/>
					</div>
				</div>
				<div class="row form-group col-lg-12">
					<label class="control-label col-lg-3" for="phone">Phone:</label>
					<div class="col-lg-9">
						<input value="" class="form-control" name="phone" type="number" required="required"/>
					</div>
				</div>
				<div class="row col-lg-12">
					<div class="col-md-offset-1 col-md-4">
						<div class="form-group">
							<label for="otype">Select Role:</label> <select
								class="form-control" id="urole" name="urole">
								<option value="none">-Select-</option>
								<option value="nurse">Nurse</option>
								<option value="labassistant">Lab assistant</option>
								<!-- <option value="bbm">Bloodbank staff</option> -->
								<option value="bmcm">BloodManagecenter staff</option>
								<option value="deliver">Distribution staff</option>
								<option value="user">normal user</option>
							</select>
						</div>
					</div>
					<div class="col-md-offset-2  col-md-4">
						<div class="form-group">
							<label for="organ" id="attachlabel">Select role first:</label> <select
								class="form-control" id="organ" name="organ">
								<option value="none">---</option>
							</select>
						</div>
					</div>
				</div>
				<br /> <br />
				<button type="submit" class="btn btn-info col-sm-offset-3 col-sm-2">Confirm</button>
				<a href="${contextPath}/admin/adduser.htm"
					class="btn btn-default col-sm-offset-2 col-sm-2">Cancel</a>
			</div>
		</form>
	</div>
</body>
</HTML>