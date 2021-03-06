package mail_api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

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

/**
 * @author Pedro
 *
 */

public class MailAPI {
	private static String username;
	private static String password;
	private static String from;
	private static String pass;
	private MailAPI mail;
	private Address from1;
	private String subj;
	private DefaultListModel<String> listaDeEmails = new DefaultListModel<String>();
	private DefaultListModel<String> listaDeProcuraDeEmails = new DefaultListModel<String>();
	private DefaultListModel<String> post_24h = new DefaultListModel<String>();
	private String result;
	protected List<Message> message2 = new ArrayList<Message>();
	private Message[] messages;
	private DefaultListModel<String> emailsReitor = new DefaultListModel<String>();
	private String Content;
	private PrintWriter pw;

	/**
	 * obtem mails da reitora
	 * @throws Exception
	 */
	public void getEmailfromReitora() throws Exception {

		if (!connectedToInternet()) {
			getEmailsReitoraOffline();
		} else {
			mail = new MailAPI();
			from = mail.getUsername();
			pass = mail.getPass();

			try {
				Properties properties = new Properties();
				properties.put("mail.pop3.host", "outlook.office365.com");
				properties.put("mail.pop3.port", "995");
				properties.put("mail.pop3s.ssl.trust", "*"); // This is the most IMP property
				Session emailSession = Session.getInstance(properties);
				Store store = emailSession.getStore("pop3s"); // try imap or impas
				store.connect("outlook.office365.com", from, password);
				Folder emailFolder = store.getFolder("INBOX");
				emailFolder.open(Folder.READ_ONLY);
				Message[] messages = emailFolder.getMessages();
				pw = new PrintWriter(new File(
						"C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\mail_api\\emailsReitora.txt"));
				emailsReitor.clear();
				for (int i = 0; i < (messages.length + 60) - messages.length; i++) {
					Message message = messages[i];
					if (message.getFrom()[0].toString().contains("reitor@iscte-iul.pt")) {
						String result;
						result = getTextFromMessage(message);
						from1 = message.getFrom()[0];
						subj = message.getSubject();
						if (message != null) {

							emailsReitor.addElement(
									"FROM: " + from1 + "        " + "SUBJECT: " + subj + "Message: " + result);
							pw.println("FROM: " + from1 + "        " + "SUBJECT: " + subj);
						}
					}
				}
				pw.close();
				emailFolder.close(false);
				store.close();
			} catch (NoSuchProviderException nspe) {
				nspe.printStackTrace();
			} catch (MessagingException me) {
				me.printStackTrace();
			}
		}
	}

	/**
	 * obtem mails reitora offline
	 */
	private void getEmailsReitoraOffline() {
		emailsReitor.clear();
		try {
			Scanner scanner = new Scanner(new File(
					"C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\mail_api\\emailsReitora.txt"));
			while (scanner.hasNextLine()) {
				String aux = scanner.nextLine();
				emailsReitor.addElement(aux);
			}
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * obtem mails
	 * @throws Exception
	 */
	public void getEmail() throws Exception {
		mail = new MailAPI();
		from = mail.getUsername();
		pass = mail.getPass();
		if (!connectedToInternet()) {
			getEmailsOffline();
		} else {
			try {
				Properties properties = new Properties();
				properties.put("mail.pop3.host", "outlook.office365.com");
				properties.put("mail.pop3.port", "995");
				properties.put("mail.pop3s.ssl.trust", "*");
				Session emailSession = Session.getInstance(properties);
				Store store = emailSession.getStore("pop3s");
				store.connect("outlook.office365.com", from, pass);
				Folder emailFolder = store.getFolder("INBOX");
				emailFolder.open(Folder.READ_ONLY);
				messages = emailFolder.getMessages();
				pw = new PrintWriter(new File(
						"C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\mail_api\\emails.txt"));

				listaDeEmails.clear();
				for (int i = 0; i < (messages.length + 60) - messages.length; i++) {
					Message message = messages[i];
					if (message.getFrom()[0].toString().contains("iscte-iul.pt")) {
						String result;
						result = getTextFromMessage(message);
						from1 = message.getFrom()[0];
						subj = message.getSubject();
						Content = mail.getTextFromMessage(message);
						if (message != null) {
							listaDeEmails.addElement(
									"FROM: " + from1 + "        " + "SUBJECT: " + subj + "Message: " + result);
							pw.println("FROM: " + from1 + "        " + "SUBJECT: " + subj);
						}
					}
				}
				pw.close();
				emailFolder.close(false);
				store.close();
			} catch (NoSuchProviderException nspe) {
				nspe.printStackTrace();
			} catch (MessagingException me) {
				me.printStackTrace();
			}
		}
	}

	/**
	 * obtem mails offline
	 */
	private void getEmailsOffline() {
		listaDeEmails.clear();
		try {
			Scanner scanner = new Scanner(new File(
					"C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\mail_api\\emails.txt"));
			while (scanner.hasNextLine()) {
				String aux = scanner.nextLine();
				listaDeEmails.addElement(aux);
			}
			scanner.close();

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * envia mail
	 * @param address
	 * @param subject
	 * @param message
	 * @throws Exception
	 */
	public void sendEmail(String address, String subject, String message) throws Exception {
		mail = new MailAPI();
		from = mail.getUsername();
		pass = mail.getPass();
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

		Session session = Session.getInstance(prop);
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

	/**
	 * mostra mails da reitora
	 * @throws Exception
	 */
	public void showListMailsReitora() throws Exception {
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

	/**
	 * @return
	 */
	public List<Message> getMessage2() {
		return message2;
	}

	/**
	 * @param message2
	 */
	public void setMessage2(List<Message> message2) {
		this.message2 = message2;
	}

	/**
	 * verifica ligacao à net
	 * @return
	 */
	public boolean connectedToInternet() {
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

	/**
	 * filtra 24h
	 * @throws MessagingException
	 */
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
			post_24h.addElement("::N�o existe nenhum mail nas �ltimas 24h!::");
	}

	/**
	 * @return
	 */
	public String getPass() {
		return password;
	}

	/**
	 * @param pass
	 */
	public void setPass(String pass) {
		this.password = pass;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public static void setUsername(String username) {
		MailAPI.username = username;
	}

	/**
	 * @return
	 */
	public Message[] getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 */
	public void setMessages(Message[] messages) {
		this.messages = messages;
	}

	/**
	 * @return
	 */
	public DefaultListModel<String> getlistaDeEmails() {
		return listaDeEmails;
	}

	/**
	 * @param listaDeEmails
	 */
	public void setlistaDeEmails(DefaultListModel<String> listaDeEmails) {
		this.listaDeEmails = listaDeEmails;
	}

	/**
	 * @param user
	 * @param pass
	 */
	public void getMailCredentials(String user, String pass) {
		MailAPI.username = user;
		MailAPI.password = pass;
	}

	/**
	 * @return
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

	/**
	 * @return
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public static void setPassword(String password) {
		MailAPI.password = password;
	}

	/**
	 * @return
	 */
	public static String getFrom() {
		return from;
	}

	/**
	 * @param from
	 */
	public static void setFrom(String from) {
		MailAPI.from = from;
	}

	/**
	 * @return
	 */
	public MailAPI getMail() {
		return mail;
	}

	/**
	 * @param mail
	 */
	public void setMail(MailAPI mail) {
		this.mail = mail;
	}

	/**
	 * @return
	 */
	public DefaultListModel<String> getListaDeEmails() {
		return listaDeEmails;
	}

	/**
	 * @param listaDeEmails
	 */
	public void setListaDeEmails(DefaultListModel<String> listaDeEmails) {
		this.listaDeEmails = listaDeEmails;
	}

	/**
	 * @return
	 */
	public DefaultListModel<String> getListaDeProcuraDeEmails() {
		return listaDeProcuraDeEmails;
	}

	/**
	 * @param listaDeProcuraDeEmails
	 */
	public void setListaDeProcuraDeEmails(DefaultListModel<String> listaDeProcuraDeEmails) {
		this.listaDeProcuraDeEmails = listaDeProcuraDeEmails;
	}

	/**
	 * @return
	 */
	public DefaultListModel<String> getPost_24h() {
		return post_24h;
	}

	/**
	 * @param post_24h
	 */
	public void setPost_24h(DefaultListModel<String> post_24h) {
		this.post_24h = post_24h;
	}

	/**
	 * @return
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return
	 */
	public DefaultListModel<String> getEmailsReitor() {
		return emailsReitor;
	}

	/**
	 * @param emailsReitor
	 */
	public void setEmailsReitor(DefaultListModel<String> emailsReitor) {
		this.emailsReitor = emailsReitor;
	}

	/**
	 * @return
	 */
	public PrintWriter getPw() {
		return pw;
	}

	/**
	 * @param pw
	 */
	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}

}
