package mail_api;

import java.util.Properties;
import com.microsoft.aad.adal4j.*;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Authenticator;
import com.outlook.dev.controller.*;
import com.outlook.dev.service.*;
import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailAPI {
	
	private static Authenticator authenticator;
	private static Properties props;
	private static String username;
	private static String password;
	private static Session session;
	private AuthenticationMailWindow amw;
	private static boolean sessionDebug;
	private Session mailSession;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void verifyCredentials(final String username, final String password) {

		this.username = username;
		this.password = password;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "outlook.office365.com");
		props.put("mail.smtp.port", "587");

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public void sendEmail(String ToSent, String mailSubject, String mailText) {
		amw = new AuthenticationMailWindow();
		System.out.println(session);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(amw.getUser()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ToSent));
			message.setSubject(mailSubject);
			message.setText(mailText);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void verifyCredentials2(String username, String password) {
		 sessionDebug = false;
		 	authenticator = new SMTPAuthenticator();
		// Set Java Mail Properties
		 props = new Properties();
		 props.put("mail.smtp.host", "smtp-mail.outlook.com");
		 props.put("mail.smtp.port", "587");
		 props.put("mail.smtp.starttls.enable","true");
		 props.put("mail.smtp.auth", "true"); 
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp-mail.outlook.com");
//		props.put("mail.smtp.port", "888");
//		props.put("mail.smtp.ssl.trust", "smtp-mail.outlook.com");
//		props.put("mail.smtp.connectiontimeout", "t1");
//		props.put("mail.smtp.timeout", "t2");

	}

	public void sendEmail2(String ToSent, String mailSubject, String texto) {

		try {
			java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			mailSession = Session.getDefaultInstance(props, authenticator);
			System.out.println(ToSent + mailSubject + texto);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(username));
			InternetAddress[] address = { new InternetAddress(ToSent) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(mailSubject);
			msg.setSentDate(new Date());
			msg.setText(texto);
			System.out.println(username + ToSent + mailSubject + texto );
			Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp-mail.outlook.com", username, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			
			System.out.println("message send successfully");
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		MailAPI.session = session;
	}
	
	
	static class SMTPAuthenticator extends Authenticator {
		 
        private static final String SMTP_AUTH_USER = username;
        private static final String SMTP_AUTH_PASSWORD = password;
 
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PASSWORD;
 
            return new PasswordAuthentication(username, password);
        }
    }
}
