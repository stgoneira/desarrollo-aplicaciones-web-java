<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Persona</h1>
	
	<c:if test="${not empty validador}">
	<div style="background:red; color: yellow; padding: 15px; margin-bottom: 15px;">
		${validador.toString()}
	</div>
	</c:if>
	
	<form method="post" action="${pageContext.request.contextPath}/PersonaServlet">
		Nombre: <input type="text" name="nombre" value="${validador.nombre}" /><br />
		Edad: <input type="text" name="edad" value="${validador.edadStr}" /><br />
		<button type="submit">Guardar</button>
	</form>
</body>
</html>