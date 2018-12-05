package ES1_2018_EIC1_49.EIC1_49;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pedro Almeida
 *
 */
@XmlRootElement(name = "users")
public class Utilizadores {

	private List<Utilizador> users;

	
	
	@XmlElement(name = "user")
	public void setUtilizadores(List<Utilizador> usersList) {
		this.users = usersList;

	}

	public List<Utilizador> getUtilizadores() {
		return users;
	}

	@Override
	public String toString() {
		return users.toString();
	}

}
