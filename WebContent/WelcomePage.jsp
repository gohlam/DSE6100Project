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
		<a href="reviewer" class="button"> Top Reviewer </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="poor" class="button"> Poor Questions </a>
		</div>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="todayQuestions" class="button"> New Questions </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="topQuestions" class="button"> Top Questions </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="common" class="button"> Common Questions </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="positive" class="button"> Positive Reviewers </a>
		</div>
		</c:if>
		<c:if test="${sessionScope.email == 'root' }">
		<div>
		<a href="inactive" class="button"> Inactive Users </a>
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
	    
	<c:if test="${sessionScope.email == 'root' && positiveReviewers != null}">
	<div align="center">
	<h4 align="center">Positive Reviewers</h4>
	<table>
		<c:forEach items="${positiveReviewers}" var="reviewer">
			<tr>
			   <td> <a href="user?email=${reviewer }" class="button">${reviewer } </a>
			   </td>
			</tr>
			</c:forEach>
			</table>
	</div> 
	</c:if>

	<c:if test="${sessionScope.email == 'root' && inactiveUsers != null}">
	<div align="center">
	<h4 align="center">Inactive Users</h4>
	<table>
		<c:forEach items="${inactiveUsers}" var="user">
			<tr>
			   <td> <a href="user?email=${userr }" class="button">${user } </a>
			   </td>
			</tr>
			</c:forEach>
			</table>
	</div> 
	</c:if>    
	
	<c:if test="${sessionScope.email == 'root' && users != null}">
		<div align="center">
		 <h4 align="center">Common Questions Form</h4>
		  <form action="commonQuestionsResults" method="post">
		 
		 <table>
			<tr>
                <th>Select User 1: </th>
                <td>
                     <select name="user1">
   	                    <c:forEach items="${users}" var="u">
				        	<option value="${u}" <c:if test="${user1 == u}"> selected </c:if>>${u}</option>
				    	</c:forEach>
                    </select>    
                </td>
            </tr>
            <tr>
                <th>Select User 2: </th>
                <td>
                     <select name="user2">
   	                    <c:forEach items="${users}" var="u2">
				        	<option value="${u2}" <c:if test="${user2 == u2}"> selected </c:if>>${u2}</option>
				    	</c:forEach>
                    </select>    
                </td>
            </tr>
             <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Find Common Questions" />
                </td>
            </tr>
			</table>
			        </form>
			
		</div> 
	</c:if>
	<c:if test="${sessionScope.email == 'root' && users != null  && commonQuestions != null}">
		<div align="center">
		 <h4 align="center">Common Question Results</h4>
		 <table>
			<c:forEach items="${commonQuestions}" var="q">
				<tr>
			   		<td> ${q.question }
			   		</td>
			   	</tr>
				</c:forEach>
			</table>
		</div> 
	</c:if>
	
</body>
</html>
