<%--
  Created by IntelliJ IDEA.
  User: xdwea
  Date: 2017/2/9 0009
  Time: 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
    <title>Add Result</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href="../css/style.css">
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body class="container">
<div class="alert alert-danger content" role="alert">
    <h2 class='text-center'><b>${errorMessage}</b></h2>
    <br/>
    <a href="${contextPath}/user/profile.htm" class="btn btn-default col-sm-offset-4 col-sm-4">Back to Main Page</a>
</div>
</body>
</html>
