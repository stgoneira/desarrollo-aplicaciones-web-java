<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>Bienvenido</h1>
	
	<c:if test="${not empty persona}">
	<h2>Persona Válida</h2>
	Nombre: ${persona.nombre} <br />
	Edad: ${persona.edad} <br /> 
	</c:if>
	
</body>
</html>