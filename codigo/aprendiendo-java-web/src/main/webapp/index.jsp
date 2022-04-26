<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
	<h2>Internacionalización (i18n)</h2>
	Pasos:
	<ol>
		<li>Crear archivo messages.properties dentro de la carpeta src/main/java</li>
		<li>Crear archivo messages_en.properties</li>
	</ol>
	<fmt:setLocale value="es" />
	<%-- messages el nombre del archivo sin extensión --%>
	<fmt:setBundle basename="messages"/>
	<fmt:message key="label.saludo" /><br />
	<fmt:message key="label.stock">
		<fmt:param>50</fmt:param>
	</fmt:message>
	<br />
	

	<h2>Formato de Fechas</h2>
	<c:set var="ahora" value="<%=new java.util.Date() %>" /><%-- JSTL c:set y la <%= %> es una expression de JSP --%>
	<fmt:formatDate value="${ahora}" pattern="yyyy-MM-dd"/><br />
	<fmt:formatDate value="${ahora}" type="date" /><br />
	<fmt:formatDate value="${ahora}" type="time" /><br />
	<fmt:formatDate value="${ahora}" type="both" />		

	<h2>Import</h2>
	<textarea rows="10" cols="20">
		<%-- <c:import url="https://mindicador.cl/api" /> --%>
	</textarea>

	<h2>Bucle forEach</h2>
	<%-- Recupera los nombres de los parámetro que vienen desde la URL --%>
	<ul>
	<c:forEach items="${paramValues}" var="parametro">
		<li>${parametro.key}</li>
	</c:forEach>
	</ul>

	<h2>Bucle forTokens</h2>
	<ul>
	<c:forTokens items="uno,dos,tres,cuatro" delims="," var="numero">
		<li>${numero}</li>
	</c:forTokens>
	</ul>

	<h2>Condiciones</h2>
	<c:if test="${param.activo == 'true'}">
		<p>El usuario está activo</p>
	</c:if>

	<h2>Output</h2>
	<c:out value="${param.nombre}" default="Sin nombre" /><br /><%-- Así se previene ataque XSS --%>
	${param.nombre}<br /><%-- vulnerable a ataque --%>
	${fn:escapeXml(param.nombre)}<br /><%-- Así se previene ataque XSS --%>
	
	<h2>Generar URL</h2>
	<c:url value="/aprendiendo-java-web/index.jsp">
		<c:param name="nombre" value="Santiago Neira" />
	</c:url>
</body>
</html>