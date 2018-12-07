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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

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
	private String accessToken = "EAAEZBg2PIN94BAPcKDeVZCwEiphM72ifwMJOtlSeNji9q6HrFcIYB8Dwlt7pZBmZAyZAbq6CFryecv5FYoNtOYCMNNQYuzAZBWVMwqshw1iYm1gZC9Q3VnloFXIefk5198X9rYXq6ZBr5ZBp3DybZCPISNBCqoam97RaLnj6yYpNdAaxpqhv5OCi2eS3ULhuOBEgLHRjsTTSAJhQZDZD";
	private DefaultListModel<String> listaPostsFB = new DefaultListModel<String>();
	private DefaultListModel<String> listaForSearchPostsFB = new DefaultListModel<String>();
	private DefaultListModel<String> post_24h = new DefaultListModel<String>();

	private List<Post> posts = new ArrayList<Post>();
	private PrintWriter pw;

	public void AuthUser() throws FileNotFoundException {

		if (!connectedToInternet()) {
			getPostsOffline();
		} else {
			FacebookClient fbClient = new DefaultFacebookClient(accessToken);
			Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);
			listaPostsFB.clear();
			posts = new ArrayList<Post>();
			int cont = 0;
			pw = new PrintWriter(new File(
					"C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\mail_api\\emailsReitora.txt"));
			listaPostsFB.clear();
			for (List<Post> page : result) {
				for (Post aPost : page) {
					if (aPost.getMessage() != null) {
						posts.add(aPost);
						cont++;
						listaPostsFB.addElement(aPost.getCreatedTime() + " - " + aPost.getMessage());
						pw.println(aPost.getCreatedTime() + " - " + aPost.getMessage());
					}
				}
			}
			pw.close();
		}
	}

	public void searchForUserPosts(String tag) throws FileNotFoundException {
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

	public void getPostsOffline() {
		listaPostsFB.clear();
		try {
			Scanner scanner = new Scanner(new File(
					"C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\mail_api\\emailsReitora.txt"));
			while (scanner.hasNextLine()) {
				String aux = scanner.nextLine();
				listaPostsFB.addElement(aux);
			}
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void filtrarUltimas24horas() {
		Date today = new Date();
		Long dateInLong = today.getTime();
		for (int post = 0; post < posts.size(); post++) {
			String element = listaPostsFB.getElementAt(post);
			Long millie = posts.get(post).getCreatedTime().getTime();
			Long periodo_24 = dateInLong - 86400000;
			if (millie >= periodo_24)
				post_24h.addElement(element);
		}
		if (post_24h.isEmpty())
			post_24h.addElement("::N�o existe nenhum Post nas �ltimas 24h!::");
	}

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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Post getaPostmew() {
		return aPostmew;
	}

	public void setaPostmew(Post aPostmew) {
		this.aPostmew = aPostmew;
	}

	/**
	 * @return the listaPostsFB
	 */
	public DefaultListModel<String> getListaPostsFB() {
		return listaPostsFB;
	}

	/**
	 * @param listaPostsFB the listaPostsFB to set
	 */
	public void setListaPostsFB(DefaultListModel<String> listaPostsFB) {
		this.listaPostsFB = listaPostsFB;
	}

	/**
	 * @return the listaForSearchPostsFB
	 */
	public DefaultListModel<String> getListaForSearchPostsFB() {
		return listaForSearchPostsFB;
	}

	/**
	 * @param listaForSearchPostsFB the listaForSearchPostsFB to set
	 */
	public void setListaForSearchPostsFB(DefaultListModel<String> listaForSearchPostsFB) {
		this.listaForSearchPostsFB = listaForSearchPostsFB;
	}

	/**
	 * @return the post_24h
	 */
	public DefaultListModel<String> getPost_24h() {
		return post_24h;
	}

	/**
	 * @param post_24h the post_24h to set
	 */
	public void setPost_24h(DefaultListModel<String> post_24h) {
		this.post_24h = post_24h;
	}
}
