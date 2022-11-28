<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    id : ${base.id}<br>
    title : ${base.title}<br>
    다국어 : ${message} <br>
    다국어 TagLib : <spring:message code="alert.saved" />
</body>
</html>