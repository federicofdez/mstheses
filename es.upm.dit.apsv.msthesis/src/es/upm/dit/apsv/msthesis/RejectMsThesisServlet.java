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

/**
 * 
 * @author Federico A. Fernández Moreno
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
			if (role.equals("tutor")) {
				msthesis.setRejected(true);
				dao.updateMsThesis(msthesis);

				String subject = "El profesor " + username
						+ " rechaza la solicitud del MsThesis.";
				String text = "El profesor " + username
						+ " rechaza la solicitud del MsThesis propuesto por "
						+ msthesis.getAuthor() + "con título " + msthesis.getTitle();

				sendMail(author, subject, text);
				break;
			} else{
				resp.sendRedirect("myMsThesiss?error=Solicitud%20Incorrecta,%20por%20favor%20prueba%20de%20nuevo");
				return;
			}
		case 3:
			if (role.equals("tutor")) {
				msthesis.setRejected(true);
				dao.updateMsThesis(msthesis);

				String subject = "El profesor " + username
						+ " rechaza la memoria del MsThesis.";
				String text = "El profesor " + username
						+ " rechaza la memoria del MsThesis propuesto por "
						+ msthesis.getAuthor() + "con título " + msthesis.getTitle();

				sendMail(author, subject, text);
			}
			break;
		case 4:
			if (role.equals("secretary")) {
				msthesis.setRejected(true);
				dao.updateMsThesis(msthesis);

				String subject = "El secretario " + username
						+ " ha calificado el MsThesis.";
				String text = "El secretario " + username
						+ " ha calificado el MsThesis propuesto por "
						+ msthesis.getAuthor() + "con título " + msthesis.getTitle()
						+ "y tutorizado por " + msthesis.getTutor();

				sendMail(author, subject, text);
				sendMail(msthesis.getTutor(), subject, text);
			}
			break;
		}
		resp.sendRedirect("myMsThesiss");
		
	}
	
	public void sendMail(String recipient, String subject, String text) {
		Message msg = new MimeMessage(Session.getDefaultInstance(
				new Properties(), null));
		try {
			msg.setFrom(new InternetAddress("msthesis@isst-msthesis.appspotmail.com",
					"Sistema de gestion de MsThesiss"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					recipient, "Solicitante de MsThesis"));
			msg.setSubject(subject);
			msg.setText(text);
			Transport.send(msg);
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
