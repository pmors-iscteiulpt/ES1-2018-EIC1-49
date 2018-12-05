package ES1_2018_EIC1_49.EIC1_49;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class CreateXML {
	private String filepath = "C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\XML\\";
	private File xmlFile = new File(filepath + "config.xml");
//	private bdaAPP bdaapp = new bdaAPP();

//	sFFFprivate String acess = app.usernameAcessGranted();

	int index = readFromFile();;

	private List<Utilizador> usersList;

	/**
	 * Construtor da classe
	 * 
	 * @author Pedro Almeida
	 * 
	 * 
	 */

	public CreateXML() {
		consultarUsers();
	}

	/**
	 * Fun�ao que consulta os utilizadores no ficheiro
	 * 
	 * @author Pedro Almeida
	 * 
	 */

	private void consultarUsers() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);

			// Read XML
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);
			usersList = users.getUtilizadores();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fun�ao que adiciona os utilizadores no ficheiro
	 * 
	 * @author Pedro Almeida
	 * 
	 */

	public void addUser(Utilizador newUser) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);

			// Read XML
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);

			Utilizador u = new Utilizador(newUser.getUsername(), newUser.getPw(), newUser.getCurso());

			List<Utilizador> usersList = users.getUtilizadores();
			usersList.add(u);

			users.setUtilizadores(usersList);

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(users, xmlFile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adiciona os acessos do email ao utilizador em sessao
	 * 
	 * @author Pedro Almeida
	 * 
	 */

	public void complementUserEmail(String email_username) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);
		// Read XML
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);
		usersList = users.getUtilizadores();
		List<Utilizador> usersList = users.getUtilizadores();
		usersList.get(index).setEmail_username(email_username);

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(users, xmlFile);

	}

	/**
	 * Adiciona os acessos do email ao utilizador em sessao
	 * 
	 * @author Pedro Almeida
	 * 
	 */

	public void complementUserTwitter(String twitter_username) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);
		// Read XML
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);
		usersList = users.getUtilizadores();
		List<Utilizador> usersList = users.getUtilizadores();
		usersList.get(index).setTw_username(twitter_username);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(users, xmlFile);

	}

	/**
	 * getter da lista de users no XML
	 * 
	 * @author Pedro Almeida
	 * 
	 */

	public List<Utilizador> getusersList() {
		return usersList;

	}

	/**
	 * Adiciona os acessos do email ao utilizador em sessao
	 * 
	 * @author Pedro Almeida
	 * 
	 */

	public void complementUserFacebook(String twitter_username) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);
		// Read XML
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);
		usersList = users.getUtilizadores();
		List<Utilizador> usersList = users.getUtilizadores();
		usersList.get(index).setFb_username(twitter_username);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(users, xmlFile);

	}

	public String acessFacebook() {
		String fb_acess = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);

			// Read XML
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);
			usersList = users.getUtilizadores();

			fb_acess = usersList.get(index).getFb_username();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return fb_acess;
	}

	public String acessTwitter() {
		String tw_acess = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);

			// Read XML
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);
			usersList = users.getUtilizadores();

			tw_acess = usersList.get(index).getTw_username();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return tw_acess;
	}

	public String acessEmail() {
		String email_acess = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);

			// Read XML
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);
			usersList = users.getUtilizadores();

			email_acess = usersList.get(index).getFb_username();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return email_acess;
	}

	/**
	 * Fun�ao auxiliar para conseguir associar um utilizador ao uma conta
	 * 
	 * @author Pedro Almeida
	 * 
	 */

	public String getFilepath() {
		return filepath;

	}

	public int readFromFile() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filepath + "acessos"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int index = 0;
		while (scanner.hasNextInt()) {
			index = scanner.nextInt();

		}
		return index;
	}

}
