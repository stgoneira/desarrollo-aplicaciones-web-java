package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Persona;
import validadores.PersonaValidator;

import java.io.IOException;

public class PersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PersonaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/persona.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre 	= request.getParameter("nombre");
		String edadStr 	= request.getParameter("edad");
		
		PersonaValidator validador = new PersonaValidator(nombre, edadStr);
		
		if( validador.isValid() ) {
			// crear en BD 
			Persona persona = validador.makeObject();
			request.setAttribute("persona", persona);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			// mostrar errores en form 
			request.setAttribute("validador", validador);
			request.getRequestDispatcher("/WEB-INF/persona.jsp").forward(request, response);
		}
	}

}
