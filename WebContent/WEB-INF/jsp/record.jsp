<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Records</h1>

<c:url var="addUrl" value="/record/add" />
<table width: 100%; text-align:center">
	<tr>
		<td>UserId</td>
		<td>UserName</td>
		
		<td>RoleId</td>
		<td>RoleName</td>
		
		<td>ItemId</td>
		<td>ItemName</td>
	
	</tr>
	<tbody>
	<c:forEach items="${users}" var="user">
		
		
		<c:if test="${!empty user.role}">
			<c:forEach items="${user.role}" var="role">
			<tr>
				<td><c:out value="${user.userId}" /></td>
				<td><c:out value="${user.userName}" /></td>

				
				
				<td><c:out value="${role.roleId}" /></td>
				<td><c:out value="${role.roleName}" /></td>
				
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${!empty user.item}">
			<c:forEach items="${user.item}" var="item">
			<tr>
				<td><c:out value="${item.itemId}" /></td>
				<td><c:out value="${item.itemName}" /></td>
				
			</tr>
			</c:forEach>
		</c:if>
		
		
	</c:forEach>
	</tbody>
</table>

</body>
</html>