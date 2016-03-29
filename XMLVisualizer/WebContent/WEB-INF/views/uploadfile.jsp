<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
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
			return '<tr><td>' + getCheckboxHTML(fileIndex) + '</td><td>'
					+ getFileInputHTML(fileIndex) + '</td></tr>';
		}
	
		function getCheckboxHTML(fileIndex) {
			return '<input name="checkboxes" type="checkbox" value="' + fileIndex
					+ '" />';
		}
	
		function getFileInputHTML(fileIndex) {
			return '<input name="files" type="file" />';
		}
	</script>
</head>
<body>
	<div align="center">
		<h2>XML file upload</h2>
		<form:form method="post" action="saveFiles"
			modelAttribute="uploadForm" enctype="multipart/form-data">

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
		</form:form>
		<br/>
	</div>
</body>
</html>