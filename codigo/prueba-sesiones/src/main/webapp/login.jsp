<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingreso</title>
</head>
<body>
	<%@ include file="/WEB-INF/includes/menu.jsp" %>
	<h1>Ingreso Sistema</h1>
	
	<c:if test="${not empty mensajeError}">
	<div style="background-color: red; padding: 10px; margin-bottom: 10px;">
		${mensajeError} 
	</div>
	</c:if>
	
	<form method="post" action="${pageContext.request.contextPath}/autenticar">
		Usuario: <input type="text" name="usuario" />
		Password: <input type="password" name="password" />
		<button type="submit">Ingresar</button>
	</form>
</body>
</html>