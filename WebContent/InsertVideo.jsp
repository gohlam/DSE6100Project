<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <form action="insertVideo" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                       Insert a New Video
                </h2>
            </caption>
     
            <tr>
                <th>URL: </th>
                <td>
                    <input type="text" name="url" size="45"
                            value="<c:out value='${video.url}' />"
                        />
                </td>
            </tr>
             <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${video.title}' />"
                        />
                </td>
            </tr>
              <tr>
                <th>Description: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${video.description}' />"
                        />
                </td>
            </tr>
              <tr>
                <th>Associate Question: </th>
                <td>
                     <select name="qid">
   	                    <c:forEach items="${questions}" var="q">
				        	<option value="${q.questionID}">${q.question}</option>
				    	</c:forEach>
                    </select>    
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Insert Video" />
                </td>
            </tr>
        </table>
        </form>
    </div>   

</body>
</html>