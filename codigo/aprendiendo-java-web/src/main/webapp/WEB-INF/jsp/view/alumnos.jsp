<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Alumnos</title>
	</head>
<body>
	<h1>Alumnos</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Dirección</th>
				<th>Comuna</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${alumnos}" var="alumno">
			<tr>
				<td>${alumno.id}</td>
				<td>${alumno.nombre}</td>
				<td>${alumno.direccion}</td>
				<td>${alumno.comuna}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>