<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Alumnos</title>
	</head>
	<body>
		<h1>Alumnos</h1>
		
		<sql:query var="rs" dataSource="jdbc/postgres">
			SELECT * FROM alumnos
		</sql:query>
		
		<ol>
		<c:forEach var="alumno" items="${rs.rows}">
			<li>${alumno.nombre}</li>
		</c:forEach>
		</ol>
		
	</body>
</html>