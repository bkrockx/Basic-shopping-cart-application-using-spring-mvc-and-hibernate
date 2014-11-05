<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>

.right {
    background:#d9edf7;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    -ms-text-overflow:ellipsis;
    float: right;
}
#Table-box {
	width: 600px;
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
#myDiv{
	background-color: #d9edf7;
	border-color: #bce8f1;
	width: 200px;
	
}
</style>
</head>
<body bgcolor="#AAE6FA">
<center>

<div id="Table-box">

<h1>Your Cart</h1>

<%-- 
<c:url var="saveUrl" value="/category/edit?id=${categoryAttribute.categoryId}" />
--%>
<%-- /////////////////////////////////////////////////////////////////////////////////////////////// --%>
<table border='1.5' width='100' cellpadding='1' cellspacing='1'>
	<tr>
		<td>Name</td>
		<td>${CurrentUser.userName}</td>
	</tr>
</table>

<%-- /////////////////////////////////////////////////////////////////////////////////--%>

<h2> Item Saved in Cart</h2>
<table border='1.5' width='600' cellpadding='1' cellspacing='1'>
	<tr>   
		<th>ItemName</th>
		<th>ItemPrice</th>
		<th>ItemImage</th>
	</tr>
	<tbody>
	
	<c:forEach items="${CurrentItems}" var="item">
			<tr>
				<td><c:out value="${item.itemName}" /></td>
				<td><c:out value="${item.itemPrice}" /></td>
				<td><img src="/Project1/myImage/imageDisplay?id=${item.itemId}"/></td>
			</tr> 
	</c:forEach>
	<tr>
		<td> </td>
		<td>Amount payable</td>
		<td><c:out value="${Total}"/></td>
	<tr>
	</tbody>

</table>

<p><a href="http://localhost:8080/Project1/item/list"> Add more to cart</a></p>
</center>

</div>
<%-- 
<div class="myDiv">
--%>


<center>
 
<form action="/Project1/item/checkOut?id=${CurrentUser.userName}" method="post">
	<table>
	<tr>
		<td>Recipient Name:</td>
		<td><input type = "text" name = "rName"/></td>
	</tr>
	<tr>
		<td>Billing Address:</td>
		<td><input type = "text" name = "bAddress"/></td>
	<tr>
		<td> </td>
		<td><input type = "submit" value = "CheckOut"/></td>
	</tr>
	</table>	
</form>

<%-- 
<c:url var="saveUrl" value="/Project1/item/checkOut?bid=${billableAttribute.billableId}&cid=${CurrentUser.userName}" />
<form:form modelAttribute="billableAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="billableId"></form:label></td>
			<td><form:input path="billableId" type="hidden"/></td>
		</tr>
		<tr>
			<td><form:label path="billableName">Recipient Name</form:label></td>
			<td><form:input path="billableName"/></td>
		</tr>
		<tr>
			<td><form:label path="billableAddress">Recipient Address</form:label></td>
			<td><form:input path="billableAddress"/></td>
		</tr>
		
	</table>
	
	<input type="submit" value="checkOut" />
</form:form>

--%>
</center>



</body>
</html>