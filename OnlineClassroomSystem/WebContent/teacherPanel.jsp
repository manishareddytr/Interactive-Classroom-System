<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher panel</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    /* Remove the navbar's default margin-bottom and rounded borders */ 
	body {
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
	
</style>
</head>
<%@page import="com.ts.dao.*"%>
<%
String viewForum = "viewForum";
memberDao memberdao = new memberDao();
String id = request.getParameter("id");
String uname = memberdao.getUname(id);
%>
<body>
<h2>Welcome!</h2>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="login.html">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="jumbotron">
  <div class="container text-left">
    <h2 style="font-family:Comic Sans MS">Welcome!! <%=uname %>!</h2>
  </div>
</div>
<table id="subjects">
		<tr>
			<th>Subject</th>
			<th>View Forum</th>
		</tr>
		<c:forEach var="subject" items="${subs}">
			<tr>
				<td>${subject}</td>
				<td><a href="ControlServlet?forumId=${memberdao.getforumId(id)}&action=<%= viewForum%>&uname=<%= uname%>">View Forum</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>