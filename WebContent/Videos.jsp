<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<head>
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
    
   <h3 align="center">${pageTitle }</h3>
    
   <c:forEach items="${videos}" var="video">
            <div align="center">
            <h4 align="center">                ${video.title }
            </h4>
            	<div>${video.description }</div>
            	<div align="center">Associated Question: <a href="questionVideos?qid=${video.qid }" class="button">${video.question }</a></div>
            	
            	<p>Posted by: ${video.email }</p>
                	<object id="video" width="425" height="350" > 
						<param name="movie" value="${video.url }" /> 
						<embed src="${video.url }" type="application/x-shockwave-flash" width="350" height="350" autostart="false"/> 
					</object> 
					<div></div>
					<c:if test="${not  fn:contains( reviewedVideos, video.url ) }">
						<div style="display: inline-block;">
						<a href="video?url=${video.url }" class="button">Add Comment </a>
						</div>
					</c:if>
					<c:if test="${  fn:contains( reviewedVideos, video.url ) }">
						<div style="display: inline-block;">
						<a href="video?url=${video.url }" class="button">Edit Comment </a>
						</div>
						<div style="display: inline-block;">
						<a href="removeReview?url=${video.url }" class="button">Remove Comment </a>
						</div>
					</c:if>
					<div></div>
					<c:if test="${  fn:contains( favVideos, video.url ) }">
					<div style="display: inline-block;">
					<a href="remove?url=${video.url }" class="button"> Remove from Favorite Videos </a>
					</div>
					</c:if>
					<div></div>
					<c:if test="${not  fn:contains( favVideos, video.url ) }">
					<div style="display: inline-block;">
					<a href="add?url=${video.url }" class="button"> Add to Favorite Videos </a>
					</div>
					</c:if>
            </div>
        </c:forEach>
    

</body>
</html>