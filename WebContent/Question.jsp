<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WSUDIY.com</title>
</head>
<body>
	 <center>
        <h1>WSUDIY.com</h1>
        <h2>
            <a href="login">Login</a>
             
        </h2>
        <h2>
            <a href="new">New User</a>
             
        </h2>
        <h2>
            <a href="welcome">Return to Welcome Page</a>
             
        </h2>
    </center>

	   <div style="position: center;">
            <h4>                ${question.question }
            </h4>
            	<div>${question.question }</div>
            	<p>Posted by: ${question.email }</p>
                	<object id="video" width="425" height="350" > 
						<param name="movie" value="${question.questionID }" /> 
						<embed src="${question.questionID }" type="application/x-shockwave-flash" width="350" height="350" autostart="false"/> 
					</object> 
					</div>
					<div></div>
					<c:if test="${ asked }">
					<div style="display: inline-block;">
					<a href="remove?questionID=${question.url }" class="button"> Remove from Questions </a>
					</div>
					</c:if>
					<div></div>
					<c:if test="${not  asked }">
					<div style="display: inline-block;">
					<a href="add?questionID=${question.url }" class="button"> Add to Questions </a>
					</div>
					
</body>
</html>