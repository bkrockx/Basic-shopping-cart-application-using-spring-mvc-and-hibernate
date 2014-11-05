<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
<%--     ///////////////////////////////////////////////////////////////////////////////////////////////////// --%>   
#footer {
	position: fixed;
    bottom: 0;
    width: 100%;
}


.container {
    width: 1000px;
}
.left {
    max-width: 100%;
    background:#d9edf7;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    -ms-text-overflow:ellipsis;
    float: left;
}
.right {
    background:#d9edf7;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    -ms-text-overflow:ellipsis;
    float: right;
}

#one{
	background: #d9edf7; color:#d9edf7;
	width: 400px; height: 500px;
	float:left; 
}

#two{
   background: #d9edf7; color: #d9edf7;
   margin-left: 62px;
}

#msg1{
	color:red;
}

#myDiv{
	background-color: #d9edf7;
	border-color: #bce8f1;
	width: 200px;
	
}
</style>

<%-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// --%>


</head>
<body bgcolor="#AAE6FA">

<div class="myDiv">
<%-- //////////////////////////////////////////////////////////////// --%>
<center>

<h1>Filter Item By Category</h1>

<c:url var="Url" value="/item/list" />

<form:form modelAttribute="categoryAttribute" method="POST" action="${Url}">
	<table>
		<%-- 
		<tr>
			<td><form:label path="categoryId"></form:label></td>
			<td><form:input path="categoryId" type="hidden"/></td>
		</tr>
		--%>
		
		<tr>
	 		<td>Category:</td> 
			<td><form:select path="categoryName">
				<c:forEach items="${categories}" var="category">
    				<form:option value="${category.categoryName}"></form:option>
    			</c:forEach>
			</form:select>
		<input type="submit" value="Filter" />
		</td>
		
		</tr>
	</table>
</form:form>

</center>
</div>
<h2> </h2>
<%-- //////////////////////////////////////////////////////// --%>

<div class="container">
<div class = "left">
<table border='1.5' width='200' cellpadding='1' cellspacing='1'>
	<tr>
   <%--	<th>CategoryId</th>  --%> 
		<th>CategoryName</th>
	</tr>
	<tbody>
	<c:forEach items="${categories}" var="category">	
			<tr>
	<%-- 		<td><c:out value="${category.categoryId}" /></td>   --%>
				<td><c:out value="${category.categoryName}" /></td>
			</tr>
	</c:forEach>			
	</tbody>

</table>
<div class="myDiv">
</div>
</div>

<center>
<div class="right">
<table border='1.5' width='600' cellpadding='1' cellspacing='1'>
	<tr>
   <%--	<th>ItemId</th> --%>
		<th>ItemName</th>
		<th>ItemPrice</th>
		<th>ItemContent</th>
		<th>ItemImage</th>
		<th>Add to Cart</th>
	<%-- <th>UserName</th>  --%>
	</tr>
	<tbody>
	<c:forEach items="${items}" var="item">
			<tr>
			<%-- <td><c:out value="${item.itemId}" /></td>  --%>
				<td><c:out value="${item.itemName}" /></td>
				<td><c:out value="${item.itemPrice}" /></td>
				<td><c:out value="${item.itemContent}" /></td>
				<td><img src="/Project1/myImage/imageDisplay?id=${item.itemId}"/></td>
	 			<td><a href="http://localhost:8080/Project1/item/addToUser?bid=${userName}&cid=${item.itemId}">Add</a> 

			<%--	<td>${userName}</td> --%>
			</tr> 
			</c:forEach>			
	</tbody>

</table>

</div>
</center>
</div>

<div id="footer">
	<p><a href="http://localhost:8080/Project1/record/Logout">Logout</a></p>
</div>

</body>
</html>