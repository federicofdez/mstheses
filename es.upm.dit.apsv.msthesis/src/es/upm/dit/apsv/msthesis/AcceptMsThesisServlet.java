package es.upm.dit.apsv.msthesis;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.msthesis.dao.MsThesisDAO;
import es.upm.dit.apsv.msthesis.dao.MsThesisDAOImpl;
import es.upm.dit.apsv.msthesis.model.MsThesis;
import static es.upm.dit.apsv.msthesis.utils.MailUtils.sendMail;

/**
 * 
 * @author Federico A. Fernández Moreno
 * @version 2016-11
 *
 */
public class AcceptMsThesisServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String role = req.getParameter("role");
		Map data = req.getParameterMap();
		MsThesisDAO dao = MsThesisDAOImpl.getInstance();
		MsThesis msthesis = dao.getMsThesis((String) req.getParameter("author"));
		String username = (String) req.getSession().getAttribute("username");
		String author = msthesis.getAuthor();

		switch (msthesis.getStatus()) {
		case 1:
			if (role.equals("advisor") && data.containsKey("secretary")) {
				String secretary = req.getParameter("secretary");
				msthesis.setSecretary(secretary);
				msthesis.setStatus(2);
				dao.updateMsThesis(msthesis);

				String subject = "Professor " + username
						+ " accepts to be your advisor";
				String text = "Professor " + username
						+ " accepts to be the advisor of the Master thesis proposed by "
						+ msthesis.getAuthor() + " with title " + msthesis.getTitle();

				sendMail(author, subject, text);
				break;
			} else{
				resp.sendRedirect("myMsTheses?error=Bad%20Request%20Please%20try%20again");
				return;
			}
		case 3:
			if (role.equals("advisor")) {
				msthesis.setStatus(4);
				dao.updateMsThesis(msthesis);

				String subject = "Professor " + username
						+ " accepts your report";
				String text = "Professor " + username
						+ " accepts the report of the Master thesis proposed by "
						+ msthesis.getAuthor() + "with title " + msthesis.getTitle();

				sendMail(author, subject, text);
			}
			break;
		case 4:
			if (role.equals("secretary")) {
				msthesis.setStatus(5);
				dao.updateMsThesis(msthesis);

				String subject = "The secretary " + username
						+ " has graded your Master thesis.";
				String text = "The secretary " + username
						+ " has graded the Master thesis proposed by "
						+ msthesis.getAuthor() + " with title " + msthesis.getTitle()
						+ " and advisor being " + msthesis.getAdvisor();

				sendMail(author, subject, text);
				sendMail(msthesis.getAdvisor(), subject, text);
			}
			break;
		}
		resp.sendRedirect("myMsTheses");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("myMsTheses");
	}

}
