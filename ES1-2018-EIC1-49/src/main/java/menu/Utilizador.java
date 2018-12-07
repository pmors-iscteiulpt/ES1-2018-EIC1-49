package menu;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Pedro Almeida
 *
 */
/**
 * @author Pedro
 *
 */
@XmlRootElement(name = "user")
public class Utilizador {

	private String username;
	private String pw;
	private String curso;
	private String fb_username;
	private String tw_username;
	private String email_username;

	public Utilizador() {
	}

	public Utilizador(String username, String pw, String curso) {
		super();
		this.username = username;
		this.pw = pw;
		this.curso = curso;
		this.fb_username = null;
		this.tw_username = null;
		this.email_username = null;
	}

	/**
	 * @return
	 */
	@XmlElement
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	@XmlElement
	public String getPw() {
		return pw;
	}

	/**
	 * @param pw
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}

	/**
	 * @return
	 */
	@XmlElement
	public String getCurso() {
		return curso;
	}

	/**
	 * @param curso
	 */
	public void setCurso(String curso) {
		this.curso = curso;
	}

	/**
	 * @return
	 */
	public String getFb_username() {
		return fb_username;
	}

	/**
	 * @param fb_username
	 */
	public void setFb_username(String fb_username) {
		this.fb_username = fb_username;
	}

	/**
	 * @return
	 */
	public String getTw_username() {
		return tw_username;
	}

	/**
	 * @param tw_username
	 */
	public void setTw_username(String tw_username) {
		this.tw_username = tw_username;
	}

	/**
	 * @return
	 */
	public String getEmail_username() {
		return email_username;
	}

	/**
	 * @param email_username
	 */
	public void setEmail_username(String email_username) {
		this.email_username = email_username;
	}

	@Override
	public String toString() {
		return username + "," + pw + ", " + curso + "," + "\n";
	}
}