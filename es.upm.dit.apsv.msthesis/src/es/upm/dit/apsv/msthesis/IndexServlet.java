package es.upm.dit.apsv.msthesis;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author Federico A. Fernandez Moreno
 *
 */
public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		String loginURL = userService.createLoginURL("/myMsTheses");
		String logoutURL = userService.createLogoutURL("/");
		req.getSession().setAttribute("loginURL", loginURL);
		req.getSession().setAttribute("logoutURL", logoutURL);
		if (req.getUserPrincipal() != null) {
			resp.sendRedirect("/myTFGs");
        }else{
        	req.getSession().removeAttribute("username");
        	req.getSession().removeAttribute("isUserStudent");
        	req.getSession().removeAttribute("tfg");
        	req.getSession().removeAttribute("tfgsAsTutor");
        	req.getSession().removeAttribute("tfgsAsSecretary");
        }
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
		view.forward(req, resp);
	}
	
}

