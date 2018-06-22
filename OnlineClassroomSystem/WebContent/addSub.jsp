<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  body {

    background-image:url("subj2.jpg");
    background-size: cover;
    background-repeat: no-repeat;width:100%;height:100vh;
}
</style>
</head>
<%
String uname = request.getParameter("uname");
String forumId = request.getParameter("forumId");
System.out.println(uname);
System.out.println(forumId);
%>
<body>
<form action="ControlServlet" method="POST">
<h4 style="font-family:comic sans ms;text-align:center;color:white;font-size:55px;">Update forum to a new subject</h4><br>
<div align="center" border="6px"><input type="text"  name="subject" placeholder="Subject Name"  cols="40" rows="15">

<input type="submit" class="btn btn-info" value="Update"></div><br><br>
<input type="hidden" value="adminUpdatedView" name="action">
<input type="hidden" name="uname" value="<%= uname %>" />
<input type="hidden" name="forumId" value="<%= forumId %>" />
</form>
<form action="ControlServlet" method="POST">
<div align="center"><input type="submit" class="btn btn-info" value="No thanks"></div>
<input type="hidden" value="adminView" name="action">
<input type="hidden" name="uname" value="<%= uname %>" />
<input type="hidden" name="forumId" value="<%= forumId %>" />
</form>
</body>
</html>
