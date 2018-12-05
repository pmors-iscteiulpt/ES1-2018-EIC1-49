package mail_api;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class MailAPI {
	private static String username;
	private static String password;
	private static String from;
	private static String pass;
	MailAPI mail;
	private Address from1;
	private String subj;
	public DefaultListModel<String> listaDeEmails = new DefaultListModel<String>();
	public DefaultListModel<String> listaDeProcuraDeEmails = new DefaultListModel<String>();
	public DefaultListModel<String> post_24h = new DefaultListModel<String>();
	private String result;
	protected List<Message> message2 = new ArrayList<Message>();
	private Message[] messages;

	
	public static void main(String[] args) {
	}

	public Message[] getMessages() {
		return messages;
	}

	public void setMessages(Message[] messages) {
		this.messages = messages;
	}

	/**
	 * @return the listaDeEmails
	 */
	public DefaultListModel<String> getlistaDeEmails() {
		return listaDeEmails;
	}

	/**
	 * @param listaDeEmails the listaDeEmails to set
	 */
	public void setlistaDeEmails(DefaultListModel<String> listaDeEmails) {
		this.listaDeEmails = listaDeEmails;
	}

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

			properties.put("mail.pop3.host", "outlook.office365.com");
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3s.ssl.trust", "*"); // This is the most IMP property

			Session emailSession = Session.getInstance(properties);

			// create the POP3 store object and connect with the pop server

			Store store = emailSession.getStore("pop3s"); // try imap or impas
			store.connect("outlook.office365.com", from, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			messages = emailFolder.getMessages();

			for (int i = 0; i < (messages.length + 60) - messages.length; i++) {

				Message message = messages[i];
				if (message.getFrom()[0].toString().contains("iscte-iul.pt")) {
					from1 = message.getFrom()[0];
					subj = message.getSubject();
					System.out.println(from1);
					System.out.println(subj);
					if (message != null) {
						
						listaDeEmails.addElement("FROM: " + from1 + "        " + "SUBJECT: " + subj);
					}
				}
			}
			emailFolder.close(false);
			store.close();
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
	 * @param from1 the from1 to set
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
	 * @param subj the subj to set
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

	public String getTextFromMessage(Message message) throws MessagingException, IOException {
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

	public void searchForTagInMailBox(String tag) {
		listaDeProcuraDeEmails.clear();
		for (int tweet = 0; tweet < listaDeEmails.size(); tweet++) {
			String element = listaDeEmails.getElementAt(tweet);
			String[] partes = element.split(" ");
			for (int palavras_do_tweet = 0; palavras_do_tweet < partes.length; palavras_do_tweet++) {
				if (partes[palavras_do_tweet].equals(tag)) {
					listaDeProcuraDeEmails.addElement(element);
				}
			}
		}
	}

	public void showListMailsDirector() throws Exception {
		listaDeProcuraDeEmails.clear();
		String mailDaReitora = "<reitora@iscte-iul.pt>";
		for (int tweet = 0; tweet < listaDeEmails.size(); tweet++) {
			String element = listaDeEmails.getElementAt(tweet);
			String[] partes = element.split(" ");
			for (int palavras_do_tweet = 0; palavras_do_tweet < partes.length; palavras_do_tweet++) {
				if (partes[palavras_do_tweet].equals(mailDaReitora)) {
					listaDeProcuraDeEmails.addElement(element);
				}
			}
		}
	}

	public void showListMailsISCTE() throws IOException {
		from = mail.getUsername();
		pass = mail.getPass();

		try {
			Properties properties = new Properties();

			properties.put("mail.pop3.host", "outlook.office365.com");
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3s.ssl.trust", "*"); // This is the most IMP property

			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server

			Store store = emailSession.getStore("pop3s"); // try imap or impas
			store.connect("outlook.office365.com", from, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();

			for (int i = 0; i < (messages.length + 60) - messages.length; i++) {
				Message message = messages[i];
				if (message.getSubject().toString().contains("ISCTE-IUL")) {

					result = getTextFromMessage(message);
					System.out.println("From: " + message.getFrom()[0]);
					from1 = message.getFrom()[0];
					subj = message.getSubject();
					if (message != null) {
						listaDeEmails.addElement("FROM: " + from1 + "        " + "SUBJECT: " + subj);
					}
				}
			}
			emailFolder.close(false);
			store.close();
		} catch (NoSuchProviderException nspe) {
			nspe.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public List<Message> getMessage2() {
		return message2;
	}

	public void setMessage2(List<Message> message2) {
		this.message2 = message2;
	}

	public boolean conectedToInternet() {

		Socket sock = new Socket();
		InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);

		try {
			sock.connect(addr, 3000);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				sock.close();
			} catch (Exception e) {
			}
		}
	}

	public void filtrarUltimas24horas() throws MessagingException {
		Date today = new Date();
		Long dateInLong = today.getTime();
		for (int mail = 0; mail < message2.size(); mail++) {
			String element = listaDeEmails.getElementAt(mail);
			Long millie = message2.get(mail).getReceivedDate().getTime();
			Long periodo_24 = dateInLong - 86400000;
			// ultimas 24horas
			if (millie >= periodo_24)
				post_24h.addElement(element);
		}
		if (post_24h.isEmpty())
			post_24h.addElement("::Não existe nenhum mail nas últimas 24h!::");
	}
}
