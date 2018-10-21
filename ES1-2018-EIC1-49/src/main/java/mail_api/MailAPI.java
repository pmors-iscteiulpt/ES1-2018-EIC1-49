package mail_api;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailAPI {
	private static String username;
	private static String password;
	private String from;
	private String pass;
	MailAPI mail;

	public void getMailCredentials(String user, String pass) {
		MailAPI.username = user;
		MailAPI.password = pass;
	}

	public void sendEmail(String address, String subject, String message) throws Exception {
		mail = new MailAPI();
		from = mail.getUsername();
		pass = mail.getPass();
		System.out.println();
		String[] to = { address };
		String host = "smtp-mail.outlook.com";

		
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.trust", host);
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.user", from);
		prop.put("mail.smtp.password", pass);
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.debug", "true");

		Session session = Session.getDefaultInstance(prop);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		InternetAddress[] toaddress = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			toaddress[i] = new InternetAddress(to[i]);

		}
		for (int i = 0; i < toaddress.length; i++) {
			msg.setRecipient(Message.RecipientType.TO, toaddress[i]);
		}

		msg.setSubject(subject);
		msg.setContent(message, "text/html; charset=utf-8");
		Transport transport = session.getTransport("smtp");
		transport.connect(host, from, pass);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}


	public String getPass() {
		return password;
	}

	public void setPass(String pass) {
		this.password = pass;
	}
	
	public String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		MailAPI.username = username;
	}

}