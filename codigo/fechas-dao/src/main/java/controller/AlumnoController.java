package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import dao.AlumnoDAO;
import dao.PersistenciaException;
import dao.pg.AlumnoPg;

public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HttpServletRequest request;
	private HttpServletResponse response;
	private AlumnoDAO alumnoDAO;
	
    public AlumnoController() {
        super();
    }
    
	@Override
	public void init() throws ServletException {
		super.init();
		this.alumnoDAO = new AlumnoPg();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruta 	= request.getServletPath();
		this.request 	= request;
		this.response 	= response;
		
		switch(ruta) {
			case "/alumno/guardar":
				guardarAlumno();
				break;
			default:
				response.sendError(404);
		}
	}
	
	private int getId() {
		try {
			return Integer.parseInt( request.getParameter("id") );
		} catch(Exception e) {
			return 0;
		}
	}
	
	private Alumno makeAlumnoFromRequest() {
		int id = getId();
		String nombre = request.getParameter("nombre");
		String carrera = request.getParameter("carrera");
		String nacimiento = request.getParameter("nacimiento");
		LocalDate fechaNacimiento = LocalDate.parse(nacimiento);
		return new Alumno(id, nombre, carrera, fechaNacimiento);
	}
	
	private void guardarAlumno() throws IOException {
		try {
			Alumno alumno = makeAlumnoFromRequest();
			if( alumno.getId() > 0) {
				alumnoDAO.update(alumno);
			} else {
				alumnoDAO.insert(alumno);
			}
			response.sendRedirect("/prueba/alumnos");
		} catch(PersistenciaException pe) {
			pe.printStackTrace();
			response.sendError(500);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruta 	= request.getServletPath();
		this.request 	= request;
		this.response 	= response;
		
		switch(ruta) {
			case "/alumnos":
				listarAlumnos();
				break;
			case "/alumno/crear":
				formularioAlumno(false);
				break;
			case "/alumno/editar":
				formularioAlumno(true);
				break;
			case "/alumno/eliminar":
				eliminarAlumno();
				break;
			default:
				response.sendError(404);
		}
	}

	private void eliminarAlumno() throws IOException {
		int id = getId();
		if( id > 0) {
			try {
				alumnoDAO.remove(id);
				response.sendRedirect("/prueba/alumnos");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			response.sendError(400);
		}
	}

	private void formularioAlumno(boolean editar) throws ServletException, IOException {
		if( editar ) {
			int id = getId();
			Alumno alumno;
			try {
				alumno = alumnoDAO.getById(id);
			} catch (PersistenciaException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("alumno", alumno);
		} 
		despachar("/WEB-INF/jsp/view/alumno/alumno-form.jsp");
	}

	private void despachar(String jsp) throws ServletException, IOException {
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
	private void listarAlumnos() throws IOException, ServletException {
		try {
			List<Alumno> alumnos = this.alumnoDAO.getAll();
			request.setAttribute("alumnos", alumnos);
			despachar("/WEB-INF/jsp/view/alumno/alumno-list.jsp");
		} catch (PersistenciaException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
	
	
}
