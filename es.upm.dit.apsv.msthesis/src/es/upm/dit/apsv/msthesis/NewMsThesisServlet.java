package es.upm.dit.apsv.msthesis;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.msthesis.dao.MsThesisDAO;
import es.upm.dit.apsv.msthesis.dao.MsThesisDAOImpl;

public class NewMsThesisServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map data = req.getParameterMap();
		if (data.containsKey("title") && data.containsKey("summary") && data.containsKey("tutor")) {
			String title = req.getParameter("title");
			String summary = req.getParameter("summary");
			String tutor = req.getParameter("tutor");
			MsThesisDAO dao = MsThesisDAOImpl.getInstance();
			dao.createMsThesis((String) req.getSession().getAttribute("username"), title, summary, tutor, "", "", 1,
					false);
			resp.sendRedirect("myMsTheses");
		} else {
			resp.sendRedirect("myMsTheses?error=Bad%20Request,%20please%20try%20again");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("myMsTheses");
	}

}
