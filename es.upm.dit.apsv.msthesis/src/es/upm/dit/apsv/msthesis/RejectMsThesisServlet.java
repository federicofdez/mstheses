package es.upm.dit.apsv.msthesis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
public class RejectMsThesisServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String role = req.getParameter("role");
		MsThesisDAO dao = MsThesisDAOImpl.getInstance();
		MsThesis msthesis = dao.getMsThesis((String) req.getParameter("author"));
		String username = (String) req.getSession().getAttribute("username");
		String author = msthesis.getAuthor();
		
		switch (msthesis.getStatus()) {
		case 1:
			if (role.equals("advisor")) {
				msthesis.setRejected(true);
				dao.updateMsThesis(msthesis);

				String subject = "Professor " + username
						+ " rejects your Master thesis";
				String text = "Professor " + username
						+ " rejects the Master thesis proposed by "
						+ msthesis.getAuthor() + " with title " + msthesis.getTitle();

				sendMail(author, subject, text);
				break;
			} else{
				resp.sendRedirect("myMsTheses?error=Bad%20Request%20Please%20try%20again");
				return;
			}
		case 3:
			if (role.equals("advisor")) {
				msthesis.setRejected(true);
				dao.updateMsThesis(msthesis);

				String subject = "Professor " + username
						+ " rejects your report";
				String text = "Professor " + username
						+ " rejects the Master thesis report proposed by "
						+ msthesis.getAuthor() + " with title " + msthesis.getTitle();

				sendMail(author, subject, text);
			}
			break;
		case 4:
			if (role.equals("secretary")) {
				msthesis.setRejected(true);
				dao.updateMsThesis(msthesis);

				String subject = "Secretary " + username
						+ " has failed you.";
				String text = "Secretary " + username
						+ " has failed the Master thesis proposed by "
						+ msthesis.getAuthor() + " with title " + msthesis.getTitle()
						+ " and being advised by " + msthesis.getAdvisor();

				sendMail(author, subject, text);
				sendMail(msthesis.getAdvisor(), subject, text);
			}
			break;
		}
		resp.sendRedirect("myMsTheses");
		
	}

}
