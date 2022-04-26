<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<a href="/prueba/PruebaServlet">/prueba/PruebaServlet</a>
	<br />
	
	<h2>JSTL y JSP - pageContext</h2>
	<c:set var="saludo" value="Hola" />
	<c:out value="${saludo}" /><br />
	<%=pageContext.getAttribute("saludo") %>
	
	<h2>JSTL y JSP - request</h2>
	<c:set var="saludo2" value="Hello my friend" scope="request" />
	<c:out value="${saludo2}" /><br />
	<%=request.getAttribute("saludo2") %>
	
	<h2>Scriptlet to JSTL data</h2>
	<%
	String textoParaAtributo = "vengo desde el scriptlet";
	pageContext.setAttribute("desdeScriptlet", textoParaAtributo);
	%>
	<c:out value="${desdeScriptlet}" /><br />
	desdeScriptlet = <%=pageContext.getAttribute("desdeScriptlet") %><br />
	
	
</body>
</html>
