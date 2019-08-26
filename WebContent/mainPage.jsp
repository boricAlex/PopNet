<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, kurs.popnet.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main</title>
<link  rel="stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/test.css" />
</head>
<body>
<%
		UserBean ub = (UserBean)request.getAttribute("owner");
		List<MessageBean> lmb = (List<MessageBean>)request.getAttribute("msgDisplay");
%>
<table>
		<tr>
			<th>Sender</th>
			<th>Message</th>
			<th>Like</th>
			<th>Time</th>
			<th>Other</th>
		</tr>
		<%
			for (MessageBean mb : lmb) {
		%>
		<tr>
			<td><%=mb.getSenderName()%></td>
			<td><%=mb.getText()%></td>
			<td><%=mb.getLikes()%></td>
			<td><%=mb.getTimePosted()%></td>
			<td><% if(mb.getUserId()== ub.getUserId()){ %><a href="PopController?cmd=main&deleteMsg=<%=mb.getMessageId()%>">Delete</a><% } %></td>

		</tr>
		<%
			}
		%>
	</table>
</body>
</html>