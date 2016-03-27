<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<html>
<head>
	<title>XML visualizer</title>
</head>
<body>
    <br>
    <br>
    <div align="center">
        <h1>Uploaded files</h1>
        <ol>
            <c:forEach items="${files}" var="file">
           - ${file} <br>
            </c:forEach>
        </ol>
    </div>
</body>
</html>