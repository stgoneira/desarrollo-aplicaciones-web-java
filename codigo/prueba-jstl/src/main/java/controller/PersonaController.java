package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Persona;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PersonaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PersonaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Persona> personas = Arrays.asList(
			new Persona("Jorge")
			, new Persona("Ramón")
			, new Persona("Sergio")
		);
		request.setAttribute("personas", personas);
		request
			.getRequestDispatcher("/WEB-INF/jsp/personas.jsp")
			.forward(request, response)
		;
	}

}
