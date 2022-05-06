package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdministracionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdministracionController() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object authObject = request.getSession().getAttribute("autenticado");					
		boolean autenticado = false;
		if( authObject == null) {
			autenticado = false;
		} else {
			autenticado = (Boolean) authObject;
		}
		
		if( autenticado ) {
			String ruta = request.getServletPath();
			String jsp 	= "/WEB-INF/admin/admin1.jsp";
			
			switch( ruta ) {
				case "/administracion1":
					jsp = "/WEB-INF/admin/admin1.jsp";
					break;
				case "/administracion2":
					jsp = "/WEB-INF/admin/admin2.jsp";
					break;
				case "/salir":
					request.getSession().invalidate();
					response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
					return;
				default:
					jsp = "/WEB-INF/admin/admin1.jsp";					
			}
			request.getRequestDispatcher(jsp).forward(request, response);
		} else {
			// opcion 1
			// response.sendError(403);
			// opcion 2
			response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}

}
