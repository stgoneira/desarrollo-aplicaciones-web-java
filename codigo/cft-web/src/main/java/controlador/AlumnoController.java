package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AlumnoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String vistaJSP = "";
		switch (accion) {
			case "form":
				vistaJSP = "/WEB-INF/jsp/vista/alumno/alumno-form.jsp";
				request
					.getRequestDispatcher(vistaJSP)
					.forward(request, response)
				;
				break;
			case "listar":
				try {
					List<Alumno> alumnos = getAlumnos();
					request.setAttribute("alumnos", alumnos);
					vistaJSP = "/WEB-INF/jsp/vista/alumno/alumno-listado.jsp";
					request
						.getRequestDispatcher(vistaJSP)
						.forward(request, response)
					;
				} catch (SQLException | NamingException e) {				
					response.sendError(500);
				}
				break;
			default:
				response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt( request.getParameter("id") );
		} catch (NumberFormatException e) {
			System.err.println("id se setea a 0 de manera automática.");
		}
		
		String nombre 	= request.getParameter("nombre");
		String carrera 	= request.getParameter("carrera");
		
		if(id == 0) {
			// crear el alumno 
			Alumno alumnoNuevo = new Alumno(nombre, carrera);
			try {
				crearAlumno(alumnoNuevo);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
			response.sendRedirect("/cft-web/AlumnoController?accion=listar");
		} else {
			// editar 
		}
	}
	
	public Connection getConexion() throws NamingException, SQLException {
		InitialContext contextoInicial = new InitialContext();
		DataSource dataSource = (DataSource) contextoInicial.lookup("java:comp/env/jdbc/postgres");
		return dataSource.getConnection();
	}
		
	// Trae todos los alumnos desde la BD 
	public List<Alumno> getAlumnos() throws SQLException, NamingException {
		try (
			Connection conexion = getConexion();
			Statement declaracion = conexion.createStatement();
		) {			
			ResultSet rs = declaracion.executeQuery("SELECT * FROM alumnos");
			List<Alumno> alumnos = new ArrayList<>();
			while(rs.next()) {
				// recuperar a variables datos de la tabla 
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String carrera = rs.getString("carrera");
				// instanciar objeto alumno 
				Alumno alumno = new Alumno(id, nombre, carrera);
				// agregar a la lista
				alumnos.add(alumno);
			}
			return alumnos;
		}	
	}
	
	public void crearAlumno(Alumno alumno) throws SQLException, NamingException {
		try (
			Connection conexion = getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO alumnos(nombre, carrera) VALUES(?, ?)");
		) {
			declaracion.setString(1, alumno.getNombre());
			declaracion.setString(2, alumno.getCarrera());
			int filasInsertadas = declaracion.executeUpdate();
		}		
	}

}
