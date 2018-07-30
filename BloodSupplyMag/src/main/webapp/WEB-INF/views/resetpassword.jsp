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
<!-- <link rel='stylesheet' href="./css/style.css"> -->
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
<script
	src='https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
<script
	src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>
</HEAD>
<body class='container'>
	<div class="row content form-horizontal">
		<c:if test="${errorMessage != null}">
			<div class="alert alert-danger content" role="alert">
				<h2 class='text-center'>
					<b>${errorMessage}</b>
				</h2>
				<br />
			</div>
		</c:if>

		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<form action="${contextPath}/resetpassword.htm" method="post">
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<h2 class='text-center'>
				<b>Rest Password</b>
			</h2>
			<br />
			<br />
			<div class="col-lg-offset-3 col-lg-6">
				<div class="form-group">
					<label class="control-label col-sm-3" for="username">Password:</label>
					<div class="col-sm-7">
						<input type="password" class="form-control" name="password" required="required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="actor">Re-password:</label>
					<div class="col-sm-7">
						<input type="password" class="form-control" name="repassword" required="required"/>
					</div>
				</div>
				<br />
				<br />
				<button type="submit" class="btn btn-info col-sm-offset-3 col-sm-2">Submit</button>
				<a href="${contextPath}/index.htm" class="btn btn-default col-sm-offset-2 col-sm-2">Cancel</a>
			</div>
		</form>
	</div>
</body>
</HTML>