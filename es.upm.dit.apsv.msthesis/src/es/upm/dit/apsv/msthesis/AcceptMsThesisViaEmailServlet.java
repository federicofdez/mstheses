package es.upm.dit.apsv.msthesis;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
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
 *
 */
public class AcceptMsThesisViaEmailServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MimeMessage message = new MimeMessage(Session.getDefaultInstance(new Properties(), null),
					req.getInputStream());
			String author = message.getSubject();
			MsThesisDAO dao = MsThesisDAOImpl.getInstance();

			MsThesis msthesis = dao.getMsThesis(author);
			msthesis.setStatus(4);
			dao.updateMsThesis(msthesis);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
