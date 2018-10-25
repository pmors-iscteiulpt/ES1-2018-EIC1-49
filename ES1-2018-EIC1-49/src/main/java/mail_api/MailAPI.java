package mail_api;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultListModel;

public class MailAPI {
	private static String username;
	private static String password;
	private static String from;
	private static String pass;
	MailAPI mail;
	private Address from1;
	private String subj;

	/**
	 * @return the dlm
	 */
	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	/**
	 * @param dlm
	 *            the dlm to set
	 */
	public void setDlm(DefaultListModel<String> dlm) {
		this.dlm = dlm;
	}

	DefaultListModel<String> dlm = new DefaultListModel<String>();

	public void getMailCredentials(String user, String pass) {
		MailAPI.username = user;
		MailAPI.password = pass;
	}

	public void getEmail() throws Exception {
		mail = new MailAPI();
		from = mail.getUsername();
		pass = mail.getPass();

		try {
			Properties properties = new Properties();
			properties.setProperty("mail.store.protocol", "imaps");

			Session emailSession = Session.getDefaultInstance(properties);

			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap-mail.outlook.com", from, pass);

			Folder emailFolder = emailStore.getFolder("INBOX");

			emailFolder.open(Folder.READ_ONLY);

			Message messages[] = emailFolder.getMessages();
			System.out.println(messages.length);

			for (int i = 0; i < (messages.length + 60) - messages.length; i++) {

				Message message = messages[i];
				if (message.getFrom()[0].toString().contains("iscte-iul.pt")) {
					String result;
					result = getTextFromMessage(message);

					System.out.println("Email Number: " + (i + 1));
					System.out.println("Subject: " + message.getSubject());
					System.out.println("From: " + message.getFrom()[0]);
					System.out.println("Sent Date: " + message.getSentDate());
					System.out.println("Message: " + result);
					from1 = message.getFrom()[0];
					subj = message.getSubject();
					if (message != null) {

						dlm.addElement("FROM: " + from1 + "        " + "SUBJECT: " + subj);

					}
				}
			}

			emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException nspe) {
			nspe.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}

	/**
	 * @return the from1
	 */
	public Address getFrom1() {
		return from1;
	}

	/**
	 * @param from1
	 *            the from1 to set
	 */
	public void setFrom1(Address from1) {
		this.from1 = from1;
	}

	/**
	 * @return the subj
	 */
	public String getSubj() {
		return subj;
	}

	/**
	 * @param subj
	 *            the subj to set
	 */
	public void setSubj(String subj) {
		this.subj = subj;
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

	private String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
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