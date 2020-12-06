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
            
          <form action="addQuestion" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                        Add a New Question
                </h2>
            </caption>
     
            <tr>
                <th>Question: </th>
                <td>
                    <input type="text" name="question" size="45"
                            value="<c:out value='${question.question}' />"
                        />
                </td>
            </tr>
      <%--        <tr>
                <th>Tag: </th>
                <td>
                    <input type="text" name="tag" size="45"
                            value="<c:out value='${question.tag}' />"
                        />
                </td>
            </tr> --%>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Add Question" />
                </td>
            </tr>
        </table>
        </form>
					
</body>
</html>