<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>WSUDIY.com</title>
</head>
<body>
    <center>
        <h1>WSUDIY.com</h1>
        <h2>
            <a href="new">New User</a>
             
        </h2>
    </center>
    <div align="center">
            <form action="authenticate" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                        User Login
                </h2>
            </caption>
     
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                            value="<c:out value='${user.email}' />"
                        />
                </td>
            </tr>
             <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="password" size="45"
                            value="<c:out value='${user.password}' />"
                        />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Login" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>
