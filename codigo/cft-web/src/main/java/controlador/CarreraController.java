package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Carrera;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.naming.NamingException;

import dao.CarreraDAO;
import dao.CarreraDAOImp;

public class CarreraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CarreraDAO carreraDAO; 
	
    public CarreraController() {
        super();
    }
    
    @Override
	public void init() throws ServletException {
		super.init();
		this.carreraDAO = new CarreraDAOImp();
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String vistaJSP = "";
		switch (accion) {
			case "eliminar":
				try {
					int carreraId = Integer.parseInt( request.getParameter("id") );
					carreraDAO.borrarCarrera(carreraId);
					response.sendRedirect("/cft-web/CarreraController?accion=listar");
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
					int carreraId = Integer.parseInt( request.getParameter("id") );
					Carrera carrera = carreraDAO.findCarreraById(carreraId);
					request.setAttribute("carrera", carrera);
					vistaJSP = "/WEB-INF/jsp/vista/carrera/carrera-form.jsp";
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
				vistaJSP = "/WEB-INF/jsp/vista/carrera/carrera-form.jsp";
				request
					.getRequestDispatcher(vistaJSP)
					.forward(request, response)
				;
				break;
			case "listar":
				try {
					List<Carrera> carreras = carreraDAO.findAllCarreras();
					request.setAttribute("carreras", carreras);
					vistaJSP = "/WEB-INF/jsp/vista/carrera/carrera-listado.jsp";
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
		
		if(id == 0) {
			// crear el alumno 
			Carrera carreraNueva = new Carrera(nombre);
			try {
				carreraDAO.crearCarrera(carreraNueva);
				response.sendRedirect("/cft-web/CarreraController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			// editar
			Carrera carreraAEditar = new Carrera(id, nombre);
			try {
				carreraDAO.editarCarrera(carreraAEditar);
				response.sendRedirect("/cft-web/CarreraController?accion=listar");
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		} 
	}	
}
