package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AlumnosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Crear instancias 
		List<Alumno> alumnos = Arrays.asList(
			new Alumno(1, "Juan Pérez", "11555999-k", "Los Alerces 123", "Qta Normal")
			, new Alumno(2, "Ramón Soto", "12333555-1", "Las Lomas 321", "Cerrillos")
			, new Alumno(3, "Sandra Ceballos", "9333555-9", "Novi 555", "Recoleta")
			, new Alumno(4, "Roberto Cerezo", "12555888-5", "Vitacura 555", "Vitacura")
			, new Alumno(5, "Lucas Melano", "11333222-2", "B. Ohiggins 10500", "Santiago")
		);
		// 2. Setear el atributo que se enviará al JSP 
		request.setAttribute("alumnos", alumnos);
		// 3. Despachar la responsabilidad al JSP
		request
			.getRequestDispatcher("/WEB-INF/jsp/view/alumnos.jsp")
			.forward(request, response)
		;
	}
}
