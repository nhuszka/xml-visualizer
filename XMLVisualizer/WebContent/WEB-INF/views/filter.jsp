<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XML visualizer</title>
</head>
<body>
	<div align="center">
		<h2>Uploaded files</h2>
		
		<form:form method="post" action="showGraph" modelAttribute="filterForm" enctype="multipart/form-data">
			<table>
				<tbody>
				<c:forEach items="${storedFilesModel.storedFiles}" var="file">
					<tr>
						<td><input name="ids" type="checkbox" value="${file.id}" checked="checked" /></td>
						<td>${file.fileName}</td>
					</tr>
	            </c:forEach>
				</tbody>
			</table>
			<br/>
			<label>Filter beans by package:</label>
			<input name="beanPackageFilter" type="text" value="${beanPackageFilter}"/>
			<br/>
			<br/>
			<input type="submit" value="Show graph" />
		</form:form>
		
		<br/>
		
		<form:form method="get" action="showUploadForm">
			<input type="submit" value="Back to upload"/>
		</form:form>
		
		<br/>
		
		<c:if test="${not empty graphBase64}">
			<img src="data:image/png;base64,${graphBase64}" width="600" height="600">
		</c:if>
	</div>
</body>
</html>