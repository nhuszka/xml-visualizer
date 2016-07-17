<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XML visualizer</title>
</head>
<body>
	<div align="center">
		<h2>Jegyertekesito rendszer, katt a startra az induláshoz</h2>

		<form:form method="get" action="showFelhasznalok">
<!-- 	a controller-ben a /showFelhasznalok metodus feltolti a  -->
<!-- 	Model objektum "felhasznalok" parameteret a felhasznalo beanek listajaval  -->

<!-- 	es visszater a "felhasznalok_lista" string-gel, igy betoltodik
		a felhasznalok_lista.jsp a felhasznalok parameterrel,
		amit meg tud jeleniteni mar -->
			<input type="submit" value="Start"/>
		</form:form>
	</div>
</body>
</html>