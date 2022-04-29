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
			case "eliminar":
				try {
					int alumnoId = Integer.parseInt( request.getParameter("id") );
					eliminarAlumno( alumnoId );
					response.sendRedirect("/cft-web/AlumnoController?accion=listar");
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					response.sendError(500);
				} catch (NamingException ne) {
					ne.printStackTrace();
					response.sendError(500);
				}				
				break;
			case "editar":
				try {
					int alumnoId = Integer.parseInt( request.getParameter("id") );
					Alumno alumno = getAlumnoById( alumnoId );
					request.setAttribute("alumno", alumno);
					vistaJSP = "/WEB-INF/jsp/vista/alumno/alumno-form.jsp";
					request
						.getRequestDispatcher(vistaJSP)
						.forward(request, response)
					;
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					response.sendError(500);
				} catch (NamingException ne) {
					ne.printStackTrace();
					response.sendError(500);
				}
				break;
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
				response.sendRedirect("/cft-web/AlumnoController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			// editar
			Alumno alumnoAEditar = new Alumno(id, nombre, carrera);
			try {
				editarAlumno(alumnoAEditar);
				response.sendRedirect("/cft-web/AlumnoController?accion=listar");
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}
	
	public Connection getConexion() throws NamingException, SQLException {
		InitialContext contextoInicial = new InitialContext();
		DataSource dataSource = (DataSource) contextoInicial.lookup("java:comp/env/jdbc/postgres");
		return dataSource.getConnection();
	}
	
	public Alumno getAlumnoById(int alumnoId) throws SQLException, NamingException {
		try (
			Connection conexion = getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM alumnos WHERE id = ?");
		) {
			declaracion.setInt(1, alumnoId);
			ResultSet rs = declaracion.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String carrera = rs.getString("carrera");
				return new Alumno(id, nombre, carrera);
			} else {
				return null;
			}
		}	
	}
	
	
	public void eliminarAlumno(int alumnoId) throws SQLException, NamingException {
		try (
			Connection conexion = getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM alumnos WHERE id = ?");
		) {
			declaracion.setInt(1, alumnoId);
			int filasEliminadas = declaracion.executeUpdate();
		}	
	}
	
	public void editarAlumno(Alumno alumno) throws SQLException, NamingException {
		try (
			Connection conexion = getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("UPDATE alumnos SET nombre = ?, carrera = ? WHERE id = ?");
		) {
			declaracion.setString(1, alumno.getNombre());
			declaracion.setString(2, alumno.getCarrera());
			declaracion.setInt(3, alumno.getId());
			declaracion.executeUpdate();
		}	
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
