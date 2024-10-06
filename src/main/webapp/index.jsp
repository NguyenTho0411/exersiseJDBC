<%-- 
    Document   : index.jsp
    Created on : Oct 6, 2024, 9:03:06 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Murach's Java Servlet and JSP</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <c:if test="${sqlStatement==null}">
            <c:set var="sqlStatement" value="Select * from User"></c:set>
        </c:if>
        <h1>The SQL Gateway</h1>
        <p>Enter an SQL statement and click the Execute button</p>
        <p><b>SQL statement:</b></p>
        <form action="sqlGateway" method="post">
            <textarea name="sqlStatement" rows="8" cols="60">${sqlStatement}</textarea>
            <br>
            <input type="submit" value="Execute">
        </form>
            <p><b>SQL result:</b></p>
        ${sqlResult}
    </body>
</html>
