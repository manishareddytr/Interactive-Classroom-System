<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Set subjects</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
  body {

    background-image:url("book1.jpg");
    background-size: cover;
    background-repeat: no-repeat;width:100%;height:100vh;
}
</style>
<%
String id=request.getParameter("id");
request.setAttribute("id",id);
%>
<body>
<form action="ControlServlet" method ="POST">
<h1 style="font-family:Palatino Linotype;margin-left:12%;color:brown">Welcome <%=id%>!</h1>
<font size="4" color="teal" font-family="Palatino Linotype">
<table class="table table-striped table-dark" style="width:50%">
<thead class="thead-dark">
		<tr>
			<th>Subject</th>
			<th>Department</th>
			<th>Select Subject</th>
		</tr>
</thead>
<tbody>
		<c:forEach var="Subject" items="${subjectList}">
			<tr>
				<td>${Subject.getName()}</td>
				<td>${Subject.getDept()}</td>
				<td><input type="checkbox" name = "select" value="${Subject.getName()}" ></td>
			</tr>
				</c:forEach>
</tbody>
				</table>
				</font>
				<input type = "submit" value ="SUBMIT" class="btn btn-info" style="margin-left:18%;">
				<input type="hidden" name = "id" value =<%=id %>>
				<input type="hidden" name = "action" value = "setSubjects">
</form>
</body>
</html>