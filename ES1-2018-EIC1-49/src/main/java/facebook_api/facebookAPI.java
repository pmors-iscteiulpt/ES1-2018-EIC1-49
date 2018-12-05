package facebook_api;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.DefaultListModel;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class facebookAPI {
	@FXML
	private TextField username;

	@FXML
	private Post aPostmew;
	private PasswordField password;
	private String accessToken = "EAAEZBg2PIN94BAOTKJ82e8TXzfA5PqzgnfyZAqE53YW1OSHHnXrMFZAvkyf5eqWFjdEZB8LMZC1JiXgxnLS2w4RUEuCBbNSoyWcZBSCnJPrRIR7cCfXKITShIkjszoB2uBHTaN8pDmZAWrpgoD4CQBFHh3xhDhXhSYZD";
	DefaultListModel<String> listaPostsFB = new DefaultListModel<String>();

	DefaultListModel<String> listaForSearchPostsFB = new DefaultListModel<String>();
	DefaultListModel<String> post_24h = new DefaultListModel<String>();
	private ArrayList<Post> posts;
	private Connection<Post> result;

	/**
	 * @return the result
	 */
	public Connection<Post> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Connection<Post> result) {
		this.result = result;
	}

	public void AuthUser() {

		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		result = fbClient.fetchConnection("me/feed", Post.class);

		posts = new ArrayList<Post>();
		int cont = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				if (aPost.getMessage() != null) {

					posts.add(aPost);
					cont++;
					listaPostsFB.addElement(aPost.getCreatedTime() + " - " + aPost.getMessage());
				}
			}
		}

		System.out.println(cont);
		// driver.quit();
	}

	/**
	 * @return the posts
	 */
	public ArrayList<Post> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the aPostmew
	 */
	public Post getaPostmew() {
		return aPostmew;
	}

	/**
	 * @param aPostmew the aPostmew to set
	 */
	public void setaPostmew(Post aPostmew) {
		this.aPostmew = aPostmew;
	}

	public void searchForUserPosts(String tag) {
		AuthUser();
		for (int tweet = 0; tweet < listaPostsFB.size(); tweet++) {
			String element = listaPostsFB.getElementAt(tweet);
			String[] partes = element.split(" ");
			for (int palavras_do_tweet = 0; palavras_do_tweet < partes.length; palavras_do_tweet++) {
				if (partes[palavras_do_tweet].contains(tag)) {
					listaForSearchPostsFB.addElement(element);
				}
			}
		}

	}

	public void post(String text_to_post) {
		try {
			@SuppressWarnings("deprecation")
			FacebookClient fbClient = new DefaultFacebookClient(accessToken);
			fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", text_to_post));
		} catch (FacebookException ex) { // So that you can see what went wrong
			ex.printStackTrace(System.err); // in case you did anything incorrectly
		}
	}

	public void filtrarUltimas24horas() {
		Date today = new Date();
		Long dateInLong = today.getTime();
		for (int post = 0; post < posts.size(); post++) {
			Post element = posts.get(post);

			Long millie = element.getCreatedTime().getTime();
			Long periodo_24 = dateInLong - 86400000;
			// ultimas 24horas
			if (millie >= periodo_24) {
				listaPostsFB.addElement(element.getCreatedTime() + " - " + element.getMessage());
			}
			if (listaPostsFB.isEmpty())
				listaPostsFB.addElement("::Não existe nenhum Post nas últimas 24h!::");
		}
	}

}
