package es.upm.dit.apsv.msthesis.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Federico A. Fernández Moreno
 * @version 2016-11
 *
 */
public class MailUtils {

	public static void sendMail(String recipient, String subject, String text) {
		Message msg = new MimeMessage(Session.getDefaultInstance(
				new Properties(), null));
		try {
			msg.setFrom(new InternetAddress("msthesis@apsv-msthesis.appspotmail.com",
					"Master Thesis Management System"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					recipient, "Master Thesis Applicant"));
			msg.setSubject(subject);
			msg.setText(text);
			Transport.send(msg);
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
