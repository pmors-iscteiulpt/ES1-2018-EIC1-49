package menu;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "user")
public class Utilizador {

<<<<<<< HEAD
	/**
	 * Classe que articula o primeiro login na App
	 * 
	 * @author Pedro Almeida
	 */

	private String username;
	private String pw;
	private String curso;
	private String fb_username;
	private String tw_username;
	private String email_username;

	public Utilizador() {
	}

	/**
	 * Construtor da classe
	 * 
	 * @author Pedro Almeida
	 */

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
	 * Getters e setters da classe Utilizador
	 * 
	 * @author Pedro Almeida
	 */
=======
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
>>>>>>> refs/remotes/origin/master

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

	public String getFb_username() {
		return fb_username;
	}

	public void setFb_username(String fb_username) {
		this.fb_username = fb_username;
	}

	public String getTw_username() {
		return tw_username;
	}

	public void setTw_username(String tw_username) {
		this.tw_username = tw_username;
	}

	public String getEmail_username() {
		return email_username;
	}

	public void setEmail_username(String email_username) {
		this.email_username = email_username;
	}

	@Override
	public String toString() {
		return username + "," + pw + ", " + curso + "," + "\n";
	}
}