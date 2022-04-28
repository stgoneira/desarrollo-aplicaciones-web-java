Desarrollo Aplicaciones Web en Java
====================================

Miércoles 
2022-04-27
Sesión 7 de 17 

BREAK de 20:10 a 20:25hrs 

Para hoy 
----------
- CRUD 
- Enunciado Trabajo Módulo  

Ejercicio Centro Formación Técnica  
------------------------------------
El CFT necesita matricular a sus alumnos en alguna de las carreras disponibles. 

Entidades: Alumno y Carrera 

Propuesta de Organización de Solución (opcional)
-------------------------------------------------

- 1 Servlet por cada entidad (CRUD) 
- Organización de los archivos:

src/main/java 
	modelo 
		Alumno.java 
		Carrera.java 
	controlador
		AlumnoController.java 
		CarreraController.java 
webapp
	WEB-INF
		jsp/vista
			alumno 
				alumno-form.jsp
				alumno-listado.jsp 
			carrera 
				carrera-form.jsp 
				carrera-listado.jsp 
	index.jsp 

Servlets 
---------
Ver form: GET 
(C)reate: POST 
(R)ead:   GET  
(U)pdate: POST   
(D)elete: GET   	

Iteración 1
------------
Iteración sólo tiene 1 entidad para hacer el CRUD.

Alumno: nombre y carrera 

CREATE TABLE alumnos(id serial PRIMARY KEY, nombre CHAR(20), carrera CHAR(20));

Iteración 2
------------
Iteración trabaja con 2 entidades:

1) Alumno: nombre y carrera 
2) Carrera: nombre 

Iteración 3
------------
Vamos a agregar más campos, filtros y validaciones.


Trabajos Módulo 5
-------------------
1) Trabajo Práctico Final M5 (4 de mayo)
Instituto de Capacitación "Java People"
Resolver problema sin EJBs 

2) Evaluación Portafolio Módulo 5 (3 de mayo)
Servicios de Reparación de Electrodomésticos 

2) Evidencia Portafolio Módulo 5 23-04-2022 
Ejercicio de cálculo de áreas 

3) Evidencia Portafolio Módulo 30-04-2022 
CRUD de las entidades Alumno y Carrera 


Temáticas 
----------- 

- Servlet Container 
	* Tomcat 
	* Jetty 
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


Martes 
2022-04-26
Sesión 6 de 17 

BREAK de 20:00 a 20:15hrs 

Para hoy 
----------
- Bases de Datos 
- Piscina de conexiones 
- JSTL SQL 
- JNDI 

Aprendizajes Esperados 
------------------------
- Distinguir las ventajas de usar una piscina de conexiones 
- Aplicar SQL y Java para mantener entidades 


Actividad 1 - Crear Piscina de Conexiones 
------------------------------------------ 
1. Si no tenemos el Driver JDBC, debemos descargarlo (JAR) 
2. Vamos a colocar el JAR JDBC en la carpeta lib de Tomcat 
3. Editar el archivo de configuración de Tomcat (dentro de Eclipse) llamado context.xml. Agregamos la etiqueta Resource con los datos de conexión.

Actividad 2 - Utilizar una conexión de la piscina 
--------------------------------------------------
1. Crear un proyecto java web 
2. Agregar los 2 JAR para soporte de JSTL 
3. Editar el archivo web.xml para referenciar la piscina de conexiones 
4. Crear un JSP para consultar alguna tabla 

* IMPORTANTE: Debe tener creada una tabla con datos 

Actividad 3 - Crear Mantenedor (CRUD) Alumnos 
--------------------------------------------------
1. Crear formulario con campos nombre y carrera. El formulario debe enviar los datos por POST 
2. Crear Servlet para procesar los datos del formulario 
3. En el Servlet rescatar una conexión de la piscina 
4. Insertar los datos a la tabla 
5. Redirigir a página que muestra todos los datos de la tabla 

Actividad 3 - Carreras 
----------------------------------------------
Cree una 2da entidad en su proyecto para:
3.1) Mostrar todos los registros de esa tabla 
3.2) Poder insertar datos a la tabla desde un formulario web 



///////////////////////////////////////////  


Lunes  
2022-04-25
Sesión 5 de 17 

BREAK 20:30 hasta 20:45hrs

Para hoy 
---------- 
- JSP 
- JSTL 
- Lo aconsejado y desaconsejado 

Aprendizajes Esperados 
-----------------------
5.1. Conocer las tecnologías disponibles en Java Web 
5.2. Entender cómo éstas se relacionan 
5.3. Instalar librerías de JSTL en Tomcat 10 
5.4. Entender y aplicar las etiquetas JSTL más importantes 
5.5. Conocer las directivas, declaraciones, scriptlets y expresiones JSP 
5.6. Entender el uso aconsejado y desaconsejado de los elementos JSP 

5.3 Instalación JSTL en Tomcat 10 
----------------------------------
1) Descargar librerías JSTL (API y normal) 
2) Colocar los JAR en WEB-INF/lib 

///////////////////////////////////////////  

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
