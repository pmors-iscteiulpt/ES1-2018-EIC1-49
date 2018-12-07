package facebook_api;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
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
import mail_api.MailAPI;

public class facebookAPI {

	/**
	 * Classe de API do facebook
	 * 
	 * @author Pedro Ramos
	 * 
	 * 
	 */
	@FXML
	private TextField username;
	@FXML
	private Post aPostmew;
	private PasswordField password;
	private String accessToken = "EAAEZBg2PIN94BAKJQdPQVWP4VHXOkoypR8UYaayZADxqzNsBhosZB8f1ZCwZCUAmG8bU7XXw0npXYXIqTP7K9S5irGukZBc9FNoDkiYysMav56SJvjDfsNQjbPI55IYggfz3S4GTKEa3pvMrxvXpzice4ZA9b2JIFuVFWHuJTHZAx4ZBajyzMk2IQ5C7DGS8vh0tRdfOCTmQxewZDZD";
	DefaultListModel<String> listaPostsFB = new DefaultListModel<String>();
	DefaultListModel<String> listaForSearchPostsFB = new DefaultListModel<String>();
	DefaultListModel<String> post_24h = new DefaultListModel<String>();

	/**
	 * Funcao que da autorizaçao do utilizador á API Funcão onde sao colocados os
	 * tokens do facebook
	 * 
	 * @author Pedro Ramos
	 * 
	 * 
	 */

	public void AuthUser() {
		String domain = "http://radixcode.com/";
		String appID = "1115442835290294";
		String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appID
				+ "&redirect_uri=" + domain + "&scope=user_about_me,"
				+ "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
				+ "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
				+ "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
				+ "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";

		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

		int cont = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				if (aPost.getMessage() != null) {
					cont++;
					listaPostsFB.addElement(aPost.getCreatedTime() + " - " + aPost.getMessage() + " "
							+ aPost.getCreatedTime().getTime());
				}
			}
		}
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

	/**
	 * Procura de posts do utilizador em sessao por uma palavra chave
	 * 
	 * @author Pedro Ramos
	 * 
	 * 
	 */

	public void searchForUserPosts(String tag) {
		AuthUser();
		for (int tweet = 0; tweet < listaPostsFB.size(); tweet++) {
			String element = listaPostsFB.getElementAt(tweet);
			String[] partes = element.split(" ");
			for (int palavras_do_tweet = 0; palavras_do_tweet < partes.length; palavras_do_tweet++) {
				if (partes[palavras_do_tweet].equals(tag)) {
					listaForSearchPostsFB.addElement(element);
				}
			}
		}
		listaForSearchPostsFB.clear();
	}

	/**
	 * Funçao que intercala a API e a window e faz a "postagem" de mensagens no feed
	 * do perfil logado
	 * 
	 * @author Pedro Ramos
	 * 
	 * 
	 */

	public void post1(String text_to_post) {
		try {
			@SuppressWarnings("deprecation")
			FacebookClient fbClient = new DefaultFacebookClient(accessToken);
			fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", text_to_post));
		} catch (FacebookException ex) { // So that you can see what went wrong
			ex.printStackTrace(System.err); // in case you did anything incorrectly
		}
	}

	/**
	 * Funcao que devolve posts feitos pelo utilizador em sessao nas utlimas 24h
	 * 
	 * @author Pedro Ramos
	 * 
	 * 
	 */

	public void filtrarUltimas24horas() {
		Date today = new Date();
		Long dateInLong = today.getTime();
		for (int post = 0; post < listaPostsFB.size(); post++) {
			String element = listaPostsFB.getElementAt(post);
			String[] partes = element.split(" ");
			int last_index = partes.length - 1;
			Long millie = Long.parseLong(partes[last_index]);
			Long periodo_24 = dateInLong - 86400000;
			// ultimas 24horas
			if (millie >= periodo_24)
				post_24h.addElement(element);
		}
		if (post_24h.isEmpty())
			post_24h.addElement("::Não existe nenhum Tweet nas últimas 24h!::");
	}

//	@SuppressWarnings("deprecation")
//	public void post(String message) {
//		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//		fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", message));
//	}

}
