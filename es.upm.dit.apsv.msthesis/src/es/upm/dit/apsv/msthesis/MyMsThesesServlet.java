package es.upm.dit.apsv.msthesis;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.msthesis.dao.MsThesisDAO;
import es.upm.dit.apsv.msthesis.dao.MsThesisDAOImpl;
import es.upm.dit.apsv.msthesis.model.MsThesis;

/**
 * @author Federico A. Fernández Moreno
 *
 */
public class MyMsThesesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		if (req.getUserPrincipal() == null){
			resp.sendRedirect("/");
		}
		MsThesisDAO dao = MsThesisDAOImpl.getInstance();
		
		// Retrieve user info
		String username = req.getUserPrincipal().getName();
		Boolean isUserStudent = this.isUserStudent(username);
		req.getSession().setAttribute("username", username);
		req.getSession().setAttribute("isUserStudent", isUserStudent);
		
		// Get user MsThesis(s)
		if (isUserStudent){
			MsThesis msthesis = dao.getMsThesis(username);
			req.getSession().setAttribute("msthesis", msthesis);
		} else {
			List<MsThesis> msthesesAsTutor = dao.getMsThesesByTutor(username);
			List<MsThesis> msthesesAsSecretary = dao.getMsThesesBySecretary(username);
			req.getSession().setAttribute("msthesesAsTutor", msthesesAsTutor);
			req.getSession().setAttribute("msthesesAsSecretary", msthesesAsSecretary);
		}
		
		// Dispatch JSP
		RequestDispatcher view = req.getRequestDispatcher("myMsThesesView.jsp");
		view.forward(req, resp);
	}
	
	public boolean isUserStudent(String username){
		MsThesisDAO dao = MsThesisDAOImpl.getInstance();
		if (dao.getMsThesis(username) != null)
			return true;
		else if (dao.getMsThesesByTutor(username).size() > 0 || dao.getMsThesesBySecretary(username).size() > 0)
			return false;
		else
			return true; //default is student
	}
	
}