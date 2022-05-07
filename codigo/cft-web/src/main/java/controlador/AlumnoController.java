package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Carrera;
import validator.AlumnoValidator;

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
		String idStr 				= request.getParameter("id");
		String nombreStr 			= request.getParameter("nombre");
		String fechaNacimientoStr 	= request.getParameter("nacimiento");
		
		AlumnoValidator validador 	= new AlumnoValidator(idStr, nombreStr, fechaNacimientoStr);
		Alumno alumno 				= validador.makeObject();
		
		Carrera carrera 	= null;
		try {
			int carreraId 	= Integer.parseInt( request.getParameter("carrera_id") );
			carrera 		= carreraDAO.findCarreraById(carreraId);			
			// carreras para pasarle al form 
			List<Carrera> carreras = carreraDAO.findAllCarreras();
			request.setAttribute("carreras", carreras);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			response.sendError(500);
			return;
		}
		
		// si alumno es null, es porque se produjeron errores de validación 
		if( alumno == null ) {
			request.setAttribute("validador", validador);
			String jspForm = "/WEB-INF/jsp/vista/alumno/alumno-form.jsp";
			request.getRequestDispatcher(jspForm).forward(request, response);
			return;
		}		
		
		alumno.setCarrera(carrera);				
		if(alumno.getId() == 0) {
			// crear el alumno 
			try {
				alumnoDAO.crearAlumno(alumno);
				response.sendRedirect("/cft-web/AlumnoController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			// editar			
			try {
				alumnoDAO.editarAlumno(alumno);
				response.sendRedirect("/cft-web/AlumnoController?accion=listar");
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}	
	
}
