package menu;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class Utilizadores {

<<<<<<< HEAD
	/**
	 * @author Pedro Almeida
	 * 
	 */

=======
>>>>>>> refs/remotes/origin/master
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
