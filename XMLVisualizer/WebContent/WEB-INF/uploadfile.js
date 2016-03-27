$(document).ready(function() {
	$('#addFile').click(function() {
		var fileIndex = $('#fileTable tr').children().length - 1;
		var row = '';
		row += '<tr><td>';
		row += getCheckboxHTML(fileIndex)
		row += '</td><td>';
		row += getFileInputHTML(fileIndex);
		row += '</td></tr>';
		$('#fileTable').append(row);
	});
});

function getCheckboxHTML(fileIndex) {
	return '<input type="checkbox" name="checkboxes[' + fileIndex + ']" value='
			+ fileIndex + '/>';
}

function getFileInputHTML(fileIndex) {
	return '<input type="file" name="files[' + fileIndex + ']" />';
}