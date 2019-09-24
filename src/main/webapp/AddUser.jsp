<%@page import="com.dao.OrgDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("email") != null) {
	%>
	<form action="AddUser" method="post">
		Name:<input type="text" name="name"><br> Designation:<input
			type="text" name="designation"><br> <input type="submit"
			value="Add User">
	</form>
	<%
		} else {
	%>
	<h3>You are not in session...Login Again...</h3>

	<%
		}
	%>

</body>
</html>