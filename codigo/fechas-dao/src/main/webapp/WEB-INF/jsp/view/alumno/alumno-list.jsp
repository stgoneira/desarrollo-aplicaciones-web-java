<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Alumnos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<div class="container">
	<h1>Alumnos</h1>
	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Carrera</th>
				<th>Fecha Nacimiento</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
		<a class="btn btn-success" href="${pageContext.request.contextPath}/alumno/crear">Crear</a>
		<c:forEach items="${alumnos}" var="alumno">
			<tr>
				<td><c:out value="${alumno.id}" /></td>
				<td><c:out value="${alumno.nombre}" /></td>
				<td><c:out value="${alumno.carrera}" /></td>
				<td><c:out value="${alumno.fechaNacimiento}" /></td>
				<td>
					<a href="${pageContext.request.contextPath}/alumno/editar?id=${alumno.id}">Editar</a>
					<a href="${pageContext.request.contextPath}/alumno/eliminar?id=${alumno.id}">Eliminar</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>