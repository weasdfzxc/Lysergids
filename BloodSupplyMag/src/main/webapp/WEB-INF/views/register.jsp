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
	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger content" role="alert">
			<h2 class='text-center'>
				<b>${errorMessage}</b>
			</h2>
			<br />
		</div>
	</c:if>
	<form:form commandName="user" cssClass="row" action="./signup.htm" method="post">

		<h2 class='text-center'>
			<b>Sign up</b>
		</h2>
		<br />
		<br />
		<div class="row col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 ">
			<div class="row form-group col-lg-12 ">
				<label class="control-label col-lg-3" for="username">Username:</label>
				<div class="col-lg-9">
					<form:input type="text" cssClass="form-control" path="username" />
					<form:errors path="username" />
				</div>
			</div>
			<div class="row form-group col-lg-12">
				<label class="control-label col-lg-3" for="password">Password:</label>
				<div class="col-lg-9">
					<form:input type="password" cssClass="form-control" path="password" />
					<form:errors path="password" />
				</div>
			</div>
			<div class="row form-group col-lg-12">
				<label class="control-label col-lg-3" for="firstName">First
					Name:</label>
				<div class="col-lg-9">
					<form:input type="text" cssClass="form-control" path="firstName" />
					<form:errors path="firstName" />
				</div>
			</div>
			<div class="row form-group col-lg-12">
				<label class="control-label col-lg-3" for="lastName">Last
					Name:</label>
				<div class="col-lg-9">
					<form:input type="text" cssClass="form-control" path="lastName" />
					<form:errors path="lastName" />
				</div>
			</div>
			<div class="row form-group col-lg-12">
				<label class="control-label col-lg-4" for="gender">Gender:</label>
				<form:select path="gender" class="col-lg-7">
					<form:option value="Male">Male</form:option>
					<form:option value="Female">Female</form:option>
				</form:select>
			</div>
			<div class="row form-group col-lg-12">
				<label class="control-label col-sm-3" for="dateOfBirth">Date
					Of Birth:</label>
				<div class="col-lg-9">
					<form:input value="" cssClass="form-control" path="dateOfBirth"
						type="date" />
					<form:errors path="dateOfBirth" />
				</div>
			</div>
			<div class="row form-group col-lg-12">
				<label class="control-label col-lg-3" for="email">Email:</label>
				<div class="col-lg-9">
					<form:input value="" cssClass="form-control" path="email"
						type="email" required="required"/>
					<form:errors path="email" />
				</div>
			</div>
			<div class="row form-group col-lg-12">
				<label class="control-label col-lg-3" for="phone">Phone:</label>
				<div class="col-lg-9">
					<form:input value="" cssClass="form-control" path="phone"
						type="number" />
					<form:errors path="phone" />
				</div>
			</div>
			<br /> <br />
			<button type="submit" class="btn btn-info col-sm-offset-3 col-sm-2">Confirm</button>
			<a href="index.htm" class="btn btn-default col-sm-offset-2 col-sm-2">Cancel</a>
		</div>
	</form:form>
</body>
</HTML>