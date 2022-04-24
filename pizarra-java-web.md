Desarrollo Aplicaciones Web en Java
====================================

Sábado  
2022-04-23
Sesión 4 de 17 

BREAK de 10:31 hasta 10:46

Para hoy
--------- 
- Servlets 
- JSP 
- Expression Language 
- Patrón MVC 

Actividad
---------------------
Usando el patrón Modelo Vista Controlador (MVC) genere la funcionalidad para calcular:
1) El área del Rectángulo 
2) El perímetro del Rectángulo 
3) El área del círculo
4) El perímetro del círculo 

Actividad Directiva Include (Refactoring)
---------------------------------------------
1) Convertir todas las vistas a JSP 
	1.1) Asegurarse que tengan la directiva <%@ page %> como la línea 1 
2) Integrar el CSS y JS de Bootstrap en todas las vistas de la app.
3) Creamos un archivo JSP, yo le puse header.jsp, que representa el encabezado se compartirá en todas las vistas de la aplicación.
4) Agregar directiva include de JSP en las siguientes páginas:
	- Inicio 
	- Form Rectángulo 
	- Form Círculo 
	- JSP resultado Rectángulo 
	- JSP resultado Círculo 


Enlaces Útiles
---------------

https://en.wikipedia.org/wiki/Jakarta_EE

https://tomcat.apache.org/whichversion.html 

https://jakarta.ee/specifications/servlet/5.0/

https://jakarta.ee/specifications/servlet/5.0/jakarta-servlet-spec-5.0.html

https://jakarta.ee/specifications/servlet/5.0/apidocs/

https://jakarta.ee/specifications/pages/3.0/jakarta-server-pages-spec-3.0.html

https://jakarta.ee/xml/ns/jakartaee/

https://maven.apache.org/plugins/maven-war-plugin/usage.html

https://blog.payara.fish/getting-started-with-jakarta-ee-9-hello-world


Temáticas 
----------- 

- Servlet Container 
	* Tomcat 
	* Jetty 
	* Undertow 
- Servlets 
	* Ciclo de Vida 
- Configuración (web.xml)
- Java Server Pages (JSP) 
	* Expression Language (EL) 
	* JSTL 
- Protocolo HTTP 
	* Métodos 
	* Request 
	* Response 
- Base de Datos 
	* Conexión 
	* Connection Pool
- Maven 
- WAR (Web ARchive) 
- Sesiones 
- Patrones de Diseño 
	* Model-View-Controller (MVC)
	* Singleton
	* Factory 
	* Decorator 
	* Observer 
	* Facade 
	* Data Access Object (DAO) 
	* Data Transfer Object (DTO) 
- Trabajo con Archivos 
	


///////////////////////////////////////////  

Viernes 
2022-04-22
Sesión 3 de 17 

BREAK de 20:20 a 20:35hrs 

Para hoy
--------- 
- Servlets 
- JSP 
- Expression Language 
- Patrón MVC 

Actividad
---------------------
Usando el patrón Modelo Vista Controlador (MVC) genere la funcionalidad para calcular:
1) El perímetro del Rectángulo 
2) El área del círculo
3) El perímetro del círculo 

Entrega de Trabajos
---------------------



///////////////////////////////////////////  

Jueves  
2022-04-21
Sesión 2 de 17 

BREAK 10:12 a 10:27

Para hoy
--------- 
- Repaso Fundamentos de Java 
- Servlets 
- JSP 
- web.xml 
- Configuraciones de Tomcat 

Entrega de Trabajos
---------------------

///////////////////////////////////////////  

Miércoles  
2022-04-20
Sesión 1 de 17 

Para hoy 
----------
- Temáticas a cubrir en este módulo 
- Instalación de Herramientas 

Herramientas 
-------------
1) Eclipse: "Eclipse for Enterprise Java and  Web Developers"
2) Tomcat 10: Descomprimimos el ZIP y colocamos la carpeta en algún directorio que recordemos posteriormente 

Hola Mundo 
-----------
1) En Eclipse crear proyecto del tipo "Dynamic Web Project"
2) Configurar Runtime a Tomcat 10 
3) Y marcar la opción "Generate web.xml deployment descriptor" 



///////////////////////////////////////////  
