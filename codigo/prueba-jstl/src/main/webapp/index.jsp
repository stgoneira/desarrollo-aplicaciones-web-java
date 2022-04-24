<%@page import="java.time.ZoneOffset"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"
	errorPage="500.jsp" 
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Inicio</title>
	</head>
	<body>
		<h1>Uso de JSTL</h1>
		Agregar a /WEB-INF/lib:
		<ul>
			<li>jakarta.servlet.jsp.jstl-2.0.0.jar</li>
			<li>jakarta.servlet.jsp.jstl-api-2.0.0.jar</li>
		</ul>
		
		<h2>Param URL</h2>
		<%-- ?nombre=<u>holi</u> --%>
		<c:out value="${param.nombre}" default="sin nombre" /><br /><%-- previene XSS (c:out) --%>
		${param.nombre}<br /><%-- vulnerable --%>
		${fn:escapeXml(param.nombre)}<br /><%-- NO vulnerable --%>
		<%=request.getParameter("nombre")%><br />	
		
		<h2>Make URLs</h2>
		<c:url value="/mi-conteto/ruta">
			<c:param name="nombre" value="Joaquín Soto" />
			<c:param name="edad" value="25" />
		</c:url>	
		
		<h2>Conditions</h2>
		<c:if test="${param.action == 'condicional'}">
			<p>Mostrar mensaje si la acción es "condicional"</p>
		</c:if>
		
		<h2>Bucles</h2>
		<ul>
		<c:forTokens items="uno,dos,tres,cuatro" delims="," var="numeroTexto">
			<li>${numeroTexto}</li>
		</c:forTokens>
		</ul>
		
		<h2>Import</h2>
		<textarea rows="10" cols="20">
		<c:import url="https://mindicador.cl/api" />
		</textarea>
		
		<h2>Formato Fechas</h2>		
		<c:set var="ahora" value="<%=new java.util.Date() %>" />
		<fmt:formatDate value="${ahora}" pattern="yyyy-MM-dd" /><br />
		<fmt:formatDate value="${ahora}" type="date" /><br />
		<fmt:formatDate value="${ahora}" type="time" /><br />
		<fmt:formatDate value="${ahora}" type="both" /><br />
		
		<h2>i18n</h2>		
		<fmt:setLocale value="en" />
		<fmt:setBundle basename="messages"/>
		<fmt:message key="label.welcome" /><br />
		<fmt:message key="store.greeting">
			<fmt:param value="3"/>
		</fmt:message>
		<%-- https://phrase.com/blog/posts/internationalization-basic-jsp-servlet/ --%>
		
		<%-- double a = 10 / 0; --%><%-- para forzar exception y probar la página de error --%>
	</body>
</html>