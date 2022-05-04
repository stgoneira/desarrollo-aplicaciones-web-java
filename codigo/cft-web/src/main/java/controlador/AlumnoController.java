package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Carrera;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.AlumnoDAO;
import dao.AlumnoDAOImp;
import dao.CarreraDAO;
import dao.CarreraDAOImp;

public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CarreraDAO carreraDAO;
	private AlumnoDAO alumnoDAO;

    public AlumnoController() {
        super();
    }
    
	@Override
	public void init() throws ServletException {
		super.init();
		this.carreraDAO = new CarreraDAOImp();
		this.alumnoDAO = new AlumnoDAOImp( this.carreraDAO );
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String vistaJSP = "";
		switch (accion) {
			case "eliminar":
				try {
					int alumnoId = Integer.parseInt( request.getParameter("id") );
					alumnoDAO.borrarAlumno(alumnoId);
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
					List<Carrera> carreras = carreraDAO.findAllCarreras();					
					request.setAttribute("carreras", carreras);
					Alumno alumno = alumnoDAO.findAlumnoById(alumnoId);
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
				List<Carrera> carreras = null;
				try {
					carreras = carreraDAO.findAllCarreras();
				} catch(Exception e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
				request.setAttribute("carreras", carreras);
				request
					.getRequestDispatcher(vistaJSP)
					.forward(request, response)
				;
				break;
			case "listar":
				try {
					List<Alumno> alumnos = alumnoDAO.findAllAlumnos();
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
		
		String nombre 		= request.getParameter("nombre");
		Carrera carrera 	= null;
		try {
			int carreraId 	= Integer.parseInt( request.getParameter("carrera_id") );
			carrera 		= carreraDAO.findCarreraById(carreraId);	
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			response.sendError(500);
			return;
		}
		
		// al servlet le llega el parámetro como string 
		// el control input[date] de HTML5 devuelve el string en formato ISO8601 (yyyy-mm-dd) 
		// así que debemos parsear ese string para convertir en una fecha de Java 
		LocalDate fechaNacimiento = LocalDate.parse( request.getParameter("nacimiento") ) ;
		
		if(id == 0) {
			// crear el alumno 
			Alumno alumnoNuevo = new Alumno(nombre, carrera, fechaNacimiento);
			try {
				alumnoDAO.crearAlumno(alumnoNuevo);
				response.sendRedirect("/cft-web/AlumnoController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			// editar
			Alumno alumnoAEditar = new Alumno(id, nombre, carrera, fechaNacimiento);
			try {
				alumnoDAO.editarAlumno(alumnoAEditar);
				response.sendRedirect("/cft-web/AlumnoController?accion=listar");
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}	
	
}
