package ES1_2018_EIC1_49.EIC1_49;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class CreateXML {

	/**
	 * @author Pedro Almeida
	 * @description Criaçao de ficheiro XML para guardar e consultar dados de acesso
	 */

	private String filepath = "C:\\Users\\Pedro Almeida\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\XML\\config.xml";
	private File xmlFile = new File(filepath);

	private List<Utilizador> usersList;

	public CreateXML() {
		consultarUsers();

	}

	/**
	 * 
	 * @description Consulta de utilizadores ja registados no ficheiro
	 */

	private void consultarUsers() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Utilizadores.class);

			// Read XML
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Utilizadores users = (Utilizadores) unmarshaller.unmarshal(xmlFile);
			usersList = users.getUtilizadores();

			for (Utilizador user : usersList) {
				System.out.println("Utilizador: " + user.getUsername() + " pw: " + user.getPw());
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void addUser(Utilizador newUser) {
		/**
		 * 
		 * @description Inscricao de um utilizador novo no ficheiro xml
		 */

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

	public List<Utilizador> getusersList() {
		return usersList;

	}
}
