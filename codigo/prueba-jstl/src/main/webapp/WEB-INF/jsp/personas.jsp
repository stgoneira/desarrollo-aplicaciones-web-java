<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personas</title>
</head>
<body>
	<h2>Direct EL</h2>
	<ul>
	<c:forEach var="persona" items="${personas}">
		<li>${persona.nombre}</li>
	</c:forEach>
	</ul>
	
	<h2>c:out</h2>
	<%-- recomendado para prevenir ataques XSS --%>
	<ul>
	<c:forEach var="persona" items="${personas}">
		<li>
			<c:out value="${persona.nombre}" />
		</li>
	</c:forEach>
	</ul>
	
</body>
</html>