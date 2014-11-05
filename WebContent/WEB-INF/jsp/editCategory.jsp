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
</style>
</head>
<body bgcolor="#AAE6FA">
<center>
<div id="Table-box">

<h1>Edit Category</h1>

<c:url var="saveUrl" value="/category/edit?id=${categoryAttribute.categoryId}" />
<c:url var="addCUrl" value="/item/add?id=${categoryAttribute.categoryId}" />


<form:form modelAttribute="categoryAttribute"  method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="categoryId">CategoryId:</form:label></td>
			<td><form:input path="categoryId" disabled="true"/></td>
		</tr>
	
		<tr>
			<td><form:label path="categoryName">CategoryName:</form:label></td>
			<td><form:input path="categoryName"/></td>
		</tr>
	</table>
	<input type="submit" value="Save" />
	
	<table border=1.5 width='600' cellpadding='1' cellspacing='1'>
	<tr>
		<th>ItemId</th>
		<th>ItemName</th>
		<th>ItemPrice</th>
		<th>ItemFeatures</th> 
		<th>Edit</th>
		<th>Delete</th>
		<th>ItemImage</th>
		<th>ShowImage</th>
	</tr>
	<tbody>
	
	
		<c:forEach items="${categoryAttribute.item}" var="item">
		<tr>
				<c:url var="editCUrl" value="/item/edit?bid=${categoryAttribute.categoryId}&cid=${item.itemId}" />
				<c:url var="deleteCUrl" value="/item/delete?id=${item.itemId}" />
				<c:url var="UUrl" value="/myImage/imageDisplay?id=${item.itemId}" />
				<td><c:out value="${item.itemId}" /></td>
				<td><c:out value="${item.itemName}"/></td>
				<td><c:out value="${item.itemPrice}"/></td>
				<td><c:out value="${item.itemContent}"/></td>
		 
				<td><a href="${editCUrl}">EditItem</a></td>
				<td><a href="${deleteCUrl}">DeleteItem</a></td>
		
				
		 		<td><img src="/Project1/myImage/imageDisplay?id=${item.itemId}"/></td>
				
				<td><a href="${UUrl}">ShowImage</a></td>
		
		</tr>	
		</c:forEach>
	
		
		  <p><a href="${addCUrl}">Add Item</a></p> 
		
	
	</tbody>
</table>
</form:form>
</div>
</center>
</body>
</html>