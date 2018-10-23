package ES1_2018_EIC1_49.EIC1_49;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "user")
public class Utilizador {

	private String username;
	private String pw;
	private String curso;

	public Utilizador() {
	}

	public Utilizador(String username, String pw, String curso) {
		super();
		this.username = username;
		this.pw = pw;
		this.curso = curso;
	}

	@XmlElement
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@XmlElement
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return username + "," + pw + ", " + curso + "," + "\n";
	}
}