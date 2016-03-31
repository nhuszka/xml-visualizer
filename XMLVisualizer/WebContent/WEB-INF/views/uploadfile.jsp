<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XML visualizer</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#moreFile').click(function() {
				var fileIndex = $('#fileTable tbody').children().length - 1;
				$('#fileTable').append(getFileUploadRow(fileIndex));
			});
		});
	
		function getFileUploadRow(fileIndex) {
			return '<tr><td>' + getFileInputHTML(fileIndex) + '</td></tr>';
		}
	
		function getFileInputHTML(fileIndex) {
			return '<input name="files" type="file" />';
		}
	</script>
</head>
<body>
	<div align="center">
		<h2>File upload</h2>
		<form:form method="post" action="uploadFiles" modelAttribute="uploadForm" enctype="multipart/form-data">
			<table id="fileTable">
				<tbody>
					<tr>
						<td>
							<input name="files" type="file" />
						</td>
					</tr>
				</tbody>
			</table>
			<br/>
			<input type="submit" value="Upload" />
			<input id="moreFile" type="button" value="More file" />
			<br/>
			<br/>
			Only valid Spring bean XML files will be uploaded (<a href="http://www.springframework.org/schema/beans/spring-beans.xsd">Spring beans XSD</a>)
		</form:form>		
	</div>
</body>
</html>