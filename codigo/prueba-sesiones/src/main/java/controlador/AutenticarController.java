package controlador;

import java.io.IOException;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AutenticarController extends HttpServlet {

	public AutenticarController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(404);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usuario 	= req.getParameter("usuario");
		String password = req.getParameter("password");
		
		if( "admin".equals(usuario) && "1234".equals(password) ) {
			req.getSession().setAttribute("autenticado", true);
			resp.sendRedirect( getServletContext().getContextPath() + "/administracion1" );
		} else {
			req.setAttribute("mensajeError", "Usuario y/o contraseña incorrecto(s)");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

}
