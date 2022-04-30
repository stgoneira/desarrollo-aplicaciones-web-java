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
		<h1>Formulario Alumno</h1>
		
		<form method="POST" action="${pageContext.request.contextPath}/alumno/guardar">
			<input type="hidden" name="id" value="${fn:escapeXml(alumno.id)}" />
			<div class="mb-3">
				<label for="nombre" class="form-label">Nombre</label>
				<input type="text" class="form-control" id="nombre" name="nombre" value="${fn:escapeXml(alumno.nombre.strip())}" />
			</div>
			<div class="mb-3">
				<label for="nombre" class="form-label">Carrera</label>
				<input type="text" class="form-control" id="carrera" name="carrera" value="${fn:escapeXml(alumno.carrera.strip())}" />
			</div>
			<div class="mb-3">
				<label for="nacimiento" class="form-label">Fecha Nacimiento</label>
				<input type="date" class="form-control" id="nacimiento" name="nacimiento" value="${fn:escapeXml(alumno.fechaNacimiento)}" />
			</div>
			<button class="btn btn-primary" type="submit">Guardar</button>
		</form>
	</div>
</body>
</html>