<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  
  <meta charset="utf-8">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="http://code.jquery.com/jquery-latest.js"></script> 
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
 <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
window.onload = function(){
	  var button = document.getElementById('refresh'),
	    form = button.form;

	  form.addEventListener('submit', function(){return false;})

	     //Here put the number of times you want to auto submit
	  (function submit(){
	    form.submit();
	    setTimeout(submit, 1000);   //Each second
	  })(); 
}
</script>

  <style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 50%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
	overflow:hidden;
position:relative;
      background-color: #555;
      color: white;
      margin-top:-4em;
height:4em;
clear:both;
    }
    body {
	overflow : hidden;
	 background-image:url("images/blue.jpg");
		background-repeat:no-repeat;
		background-size:cover;
	}
    /*background-color:#9febe3; On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 700px) {
      .sidenav {
        height: 50%;
        padding: 5px;
      }
      .row.content {height: 50%;} 
    }
	 #chatbox {
	 opacity:0.8;
	 border-radius:10px;
	  text-align:left;
    margin:0 auto;
    margin-bottom:25px;
    padding:10px;
    background:#fff;
    height:100%;
    width:100%;
    border:1px solid #ACD8F0;
    overflow:auto; 
	  }
  </style>
</head>
<%
String uname = (String)request.getAttribute("username");
System.out.println(uname);
String fId = (String)request.getAttribute("forumId");
//System.out.println(fId);
int forumId = Integer.parseInt(fId);
//System.out.println(forumId);
//request.setAttribute("forumID",fId);
System.out.println("chat.jsp");
String memType = (String)request.getAttribute("memType");
//System.out.println(memType);
%>
<body>

<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h4>Subject chat</h4>
      <ul class="nav nav-pills nav-stacked">
        <li><a href="#section1">calendar</a></li>
        <li><a href="file:///C:/Users/Navya/Desktop/OnlineClassroom/OnlineClassroom/WebContent/images">Documents</a></li>
        <li><a href="#section3">announcements</a></li>
      </ul><br>
    </div>
	
    <div class="col-sm-9">	<br>
	<div class= "container-fluid" style="height:290px;">
	<div id="chatbox" name="textmessage">

	<c:forEach items="${chatList}" var="chat">
	<c:if test="${chat.getType()<2}">
    	<p style="padding:30px;background-color:lightblue;border-radius:10px;">${chat.getUsername()} : ${chat.getMessage()}</p>
  	</c:if>
  	<c:if test="${chat.getType()>1}">
    	<p style="padding:30px;background-color:lightblue;border-radius:10px;">${chat.getUsername()} : <img src = "${chat.getMessage()}" alt="image" width="70px" height="70px"></p>
  	</c:if>
	</c:forEach>
		</div>
	
</div>
      <br><br>
      <hr>
      <h4>Leave a Comment:</h4>
      <form name = "form1" role="form" action="chatServlet" method="POST" > 
        <div class="form-group">
        <textarea class="form-control" name='message' value='message' required rows="3"  style="border-radius:10px"></textarea>
        </div>
        <button type="submit" class="btn btn-success">Send</button>
		
		<input type="hidden" name="action" value="chat">
		<input type="hidden" name="forumID" value=<%= fId%>>
		<input type="hidden" name="uname" value=<%= uname%>>
     
     <script type="text/javascript">
         window.onload=function() { document.getElementsByName('textmessage')[0].scrollTop=document.getElementsByName('textmessage')[0].scrollHeight;}
     </script>
      </form>
      <form name = "form2" role="form" action="chatServlet" method="POST" enctype="multipart/form-data"> 
        <input type="file" name = "photo" style="border-radius:5px;background-color:white;">
        <button type="submit" class="btn btn-success">Upload</button>
 <input type="hidden" name="action" value="photo">
 <input type="hidden" name="forumID" value=<%= fId%>>
 <input type="hidden" name="uname" value=<%= uname%>>
 <script type="text/javascript">
         window.onload=function() { document.getElementsByName('textmessage')[0].scrollTop=document.getElementsByName('textmessage')[0].scrollHeight;}
      </script>
      </form>
      <form name = "form3" role="form" action="chatServlet" method="POST" >
       <button value="Start Research !" id = "refresh" style="visibility:hidden;" onclick="click();"></button>
       <input type = "hidden" name="action" value="refresh">
       <input type="hidden" name="forumID" value=<%= fId%>>
       <input type="hidden" name="uname" value=<%= uname%>>
       <script>
            setInterval(function () {document.getElementById("refresh").click();}, 30000);
       </script>
       
       
      </form>
      <br><br>
      </div>
    </div>
  </div>
</body>


</html>

