package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Rectangulo;

import java.io.IOException;

public class RectanguloControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RectanguloControllerServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) Recuperar los valores enviados desde el formulario
		String operacion 	= request.getParameter("operacion");
		String baseStr 		= request.getParameter("base");
		String alturaStr 	= request.getParameter("altura");
		
		// 2) Convertir al tipo de dato deseado
		int base 	= Integer.parseInt(baseStr); // parseInt() es un miembro de Clase (static)
		int altura 	= Integer.parseInt(alturaStr);
		
		// 3) Utilizar la(s) clase(s) modelo para hacer cálculos o acciones
		Rectangulo rectangulo = new Rectangulo(base, altura);
		if( operacion.equalsIgnoreCase("area") ) {
			int area = rectangulo.calcularArea(); // calcularArea() es un miembro de Instancia
			request.setAttribute("resultado", area);
			request.setAttribute("operacion", "área"); // para traspasar la palabra con tildes 
		} else {
			int perimetro = rectangulo.calcularPerimetro();
			request.setAttribute("resultado", perimetro);
			request.setAttribute("operacion", "perímetro");// para traspasar la palabra con tildes 
		}
		
		// 4) Delegar a una vista para que muestre el o los resultados		
		String rutaJSP = "/WEB-INF/jsp/view/rectangulo.jsp";
		// pasamos los datos al JSP		
		request.getRequestDispatcher(rutaJSP).forward(request, response);
				
		
		// esto de acá NO corresponder, porque es responsabilidad de la vista
		/*
		response.getWriter()
			.append("Base = "+base+"\n")
			.append("Altura = "+altura)
		;
		*/
	}

}
