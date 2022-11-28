<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spring Boot Basic Project</title>
</head>
<body>
    Welcome Spring Boot Basic Project<br>
    다국어 : ${message} <br>
    다국어 TagLib : <spring:message code="alert.saved" />
    
    <table>
        <tr>    
            <th>ID</th>
            <th>TITLE</th>
        </tr>
        <c:forEach var="base" items="${baseList.list}">
            <tr>
              <td><c:out value="${base.id}"/></td>
              <td><c:out value="${base.title}"/></td>
            </tr>
        </c:forEach>    
    </table>
    <c:forEach  var="x" begin="1" end="${total + 1}" step="1">
        <a href="?page=${x}" >
            ${x}
        </a>
    </c:forEach>  

</body>
</html>