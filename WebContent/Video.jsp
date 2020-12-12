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

	   <div align="center">
            <h4 align="center">                ${video.title }
            </h4>
            	<div align="center">${video.description }</div>
            	<div align="center">Associated Question: <a href="questionVideos?qid=${video.qid }" class="button">${video.question }</a></div>
            	<p>Posted by: ${video.email }</p>
                	<object id="video" width="425" height="350" > 
						<param name="movie" value="${video.url }" /> 
						<embed src="${video.url }" type="application/x-shockwave-flash" width="350" height="350" autostart="false"/> 
					</object> 
					</div>
					<div></div>
					<c:if test="${ favorited }">
					<div align="center">
					<a href="remove?url=${video.url }" class="button"> Remove from Favorite Videos </a>
					</div>
					</c:if>
					<div></div>
					<c:if test="${not  favorited }">
					<div align="center">
					<a href="add?url=${video.url }" class="button"> Add to Favorite Videos </a>
					</div>
					</c:if>
            <div align="center">
            	<c:if test="${reviewed}">
            	
            	 <form action="updateReview?url=${video.url }" method="post">
            	 	<input type="text" name="comment" size="45"
                            value="<c:out value='${review.comment}' />"
                        />
                       <select name="score">
   	                    <option value="poor" <c:if test="${review.score == 'poor'}"> selected </c:if>>Poor</option>
   	                    <option value="fair" <c:if test="${review.score == 'fair'}"> selected </c:if>>Fair</option>
   	                    <option value="good" <c:if test="${review.score == 'good'}"> selected </c:if>>Good</option>
	                    <option value="excellent" <c:if test="${review.score == 'excellent'}"> selected </c:if>>Excellent</option>
                    </select>    
					<input type="submit" value="Update Comment" />
            	</form>
            	<div style="display: inline-block;">
						<a href="removeReview?url=${video.url }" class="button">Remove Comment </a>
						</div>
            	</c:if>
            	
            	<c:if test="${not reviewed}">
            	
            	 <form action="addReview?url=${video.url }" method="post">
            	 	<input type="text" name="comment" size="45"
                            value="<c:out value='${review.comment}' />"
                        />
                      <select name="score">
   	                    <option value="poor" <c:if test="${review.score == 'poor'}"> selected </c:if>>Poor</option>
   	                    <option value="fair" <c:if test="${review.score == 'fair'}"> selected </c:if>>Fair</option>
   	                    <option value="good" <c:if test="${review.score == 'good'}"> selected </c:if>>Good</option>
	                    <option value="excellent" <c:if test="${review.score == 'excellent'}"> selected </c:if>>Excellent</option>
                    </select>  
				<input type="submit" value="Save Comment" />
					
            	</form>
            	</c:if>
            </div>
            <div align="center">
            	<h4 align="center">Other Reviews</h4>
            	   <c:forEach items="${reviews}" var="r">
            	   	<div align="center"> ${r.score}: ${r.comment} Reviewed By: ${r.email }
            	   	</div>
            	   </c:forEach>
            </div>
            

</body>
</html>