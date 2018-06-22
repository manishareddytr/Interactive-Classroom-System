<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
<style>
#subjects {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#subjects td, #subjects th {
    border: 1px solid #ddd;
    padding: 8px;
}

#subjects tr:nth-child(even){background-color: #f2f2f2;}

#subjects tr:hover {background-color: #ddd;}

#subjects th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
}
</style>
</head>
<%
String uname=request.getParameter("uname"); 
String clear="clear";
%>
<body>
	<center><h1 style="font-family:'Comic Sans MS'">WELCOME ADMIN!</h1></center>

	<table id="subjects">
		<tr>
			<th>Subject</th>
			<th>Change Subject</th>
		</tr>
		<c:forEach var="subject" items="${subjectList}">
			<tr>
				<td>${subject.getName()}</td>
				<td><a href="ControlServlet?forumId=${subject.getforumId()}&action=<%=clear %>&uname=<%= uname%>">Clear Forum</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>