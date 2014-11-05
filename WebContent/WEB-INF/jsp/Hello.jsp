<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
	border: 1px solid #31708f;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

</style>
</head>
<body bgcolor="#AAE6FA">

	
	<center>
	<c:url var="UserUrl" value="/item/list" />
	<div id="login-box">
	<p><a href="${UserUrl}">User Section </a>	
	</div>
	
	<c:url var="adminUrl" value="/record/admin" />
	<div id = "login-box">
	<p><a href="${adminUrl}">Admin Section</a></p>
	</div>
	
	<div id = "login-box">
	<p><a href="http://localhost:8080/Project1/record/Logout">Logout</a></p>
	</div>
	</center>

</body>
</html>