<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	
<html>
<head>
	<title>XML visualizer</title>
</head>
<body>
    <br>
    <br>
	<div align="center">
		<h2>Uploaded files</h2>
		<form:form method="post" action="createGraph"
			modelAttribute="filterForm" enctype="multipart/form-data">
			<table>
				<tbody>
				<c:forEach items="${storedFilesModel.storedFiles}" var="file">
					<tr>
						<td><input name="checkboxes" type="checkbox" value="${file.id}" /></td>
						<td>${file.fileName}</td>
					</tr>
	            </c:forEach>
				</tbody>
			</table>
			<br/>
			<label>Filter beans by package:</label>
			<input name="beanPackageFilter" type="text"/>
			<br/>
			<br/>
			<input type="submit" value="Create graph" />
		</form:form>
		<br/>
	</div>
</body>
</html>