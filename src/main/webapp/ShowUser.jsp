<%@page import="com.dao.OrgDao"%>
<%@page import="com.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<%
		if (session.getAttribute("email") != null) {
		int id = (Integer) session.getAttribute("Id");
		List<UserDto> list = OrgDao.getUser(id);
		if (!list.isEmpty()) {
			for (UserDto user : list) {
	%>
	<table>
		<tr>
			<th>Name</th>
			<th>Designation</th>
		</tr>
		<tr>
			<td>
				<%
					out.print(user.getName());
				%>
			</td>
			<td>
				<%
					out.print(user.getDesignation());
				%>
			</td>
		</tr>
	</table>


	<%
		}%><a href="AddUser.jsp">Add User</a>
	<a href="LogoutServlet">Logout</a>
	<% 
			} else {
				
				out.print("<p>No user present in the organization...</p>");
				request.getRequestDispatcher("AddUser.jsp").include(request, response);
			}
	%>

	<%
		} else {
	%>
	<h3>You are not in session...Login Again...</h3>

	<%
		}
	%>

</body>
</html>