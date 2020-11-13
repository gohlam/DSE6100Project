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
    </div>   
</body>
</html>