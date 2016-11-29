package es.upm.dit.apsv.msthesis;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.msthesis.dao.MsThesisDAO;
import es.upm.dit.apsv.msthesis.dao.MsThesisDAOImpl;
import es.upm.dit.apsv.msthesis.model.MsThesis;

/**
 * 
 * @author Federico A. Fernández Moreno
 * @version 2016-11
 *
 */
public class CleanMsThesesServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MsThesisDAO dao = MsThesisDAOImpl.getInstance();
		List<MsThesis> mstheses = dao.getMsThesesRejected();
		
		for (MsThesis msthesis : mstheses){
			dao.deleteMsThesis(msthesis);
		}
		
	}

}
