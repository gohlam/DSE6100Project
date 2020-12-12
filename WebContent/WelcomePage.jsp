<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
    </center>
	
    <div align="center">
       <h3 align="center"> Welcome ${sessionScope.name }</h3>
       <c:if test="${sessionScope.email == 'root' }">
		<a href="initialize" class="button"> Initialize Database </a>
		</c:if>
		<form action="search" method="get">
			Search for a Video: <input type="text" name="keyword">
			<input type ="submit" value="Search">
		</form>
		<div>
		<a href="upload" class="button"> Upload a Video </a>
		</div>
		<div>
		<a href="ask" class="button"> Ask a Question </a>
		</div>
		<div>
		<a href="all" class="button"> View All Videos </a>
		</div>
		<div>
		<a href="favorites" class="button"> View Your Favorite Videos </a>
		</div>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="cool" class="button"> Cool Videos </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="hot" class="button"> Hot Videos </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="popular" class="button"> Popular Tags </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="top" class="button"> Top Reviewer </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="poor" class="button"> Poor Questions </a>
		</div>
		</c:if>
    </div> 
     <c:if test="${sessionScope.email == 'root' && topReviewer != null}">
		<div align="center">
		 <h4 align="center">Top Reviewer</h4>
		 <table>
			   <c:forEach items="${topReviewer}" var="reviewer">
			   	<tr>
			   		<td> <a href="user?email=${reviewer }" class="button">${reviewer } </a>
			   		</td>
			   	</tr>
				</c:forEach>
		</table>
		</div> 
	</c:if>
	<c:if test="${sessionScope.email == 'root' && poorQuestions != null}">
		<div align="center">
		 <h4 align="center">Poor Questions</h4>
		 <table>
			<c:forEach items="${poorQuestions}" var="question">
				<tr>
			   		<td> ${question.question }
			   		</td>
			   	</tr>
				</c:forEach>
				</table>
		</div> 
	</c:if>
	   <c:if test="${sessionScope.email == 'root' && popularTags != null}">
		<div align="center">
		 <h4 align="center">Popular Tags</h4>
		 <table>
			<c:forEach items="${popularTags}" var="tag">
				<tr>
			   		<td> ${tag }
			   		</td>
			   	</tr>
				</c:forEach>
			</table>
		</div> 
	</c:if>
	
</body>
</html>