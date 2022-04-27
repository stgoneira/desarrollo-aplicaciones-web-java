package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AlumnoController() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos los datos enviados desde el formulario 
		String nombre 	= request.getParameter("nombre");
		String carrera 	= request.getParameter("carrera");
		
		try {
			// recuperar conexión desde la piscina de Tomcat
			InitialContext initialContext = new InitialContext();
			Context contexto = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) contexto.lookup("jdbc/postgres");
			// end recupera conexión 
			
			try (
				Connection conexion = dataSource.getConnection();
				PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO alumnos(nombre, carrera) VALUES(?, ?)");
			) {
				// reemplazamos con los valores necesarios para la declaracion 
				declaracion.setString(1, nombre);
				declaracion.setString(2, carrera);
				
				// ejecutamos el SQL en la BD 
				int filasInsertadas = declaracion.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} catch (NamingException e) {
			e.printStackTrace();
		}		
		response.sendRedirect("/bd-web");		
	}

}
