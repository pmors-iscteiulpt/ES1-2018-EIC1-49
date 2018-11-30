package facebook_api;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class facebookAPI {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	private Post aPostmew;
	private String accessToken = "EAAEZBg2PIN94BAMPDvOtQBHFQtWmK8MmhA0AfxpLDeXmoUKYe3rcQRMZCAxW3sI1R7o5RHbZCYB7fRcZChVTvVJMvrjcxYxUP0L9qgN0ZChJyAZBV1cftbTBxcvSoUyMy66ZAMrklkw0pZC6L8EdDZCxmjHuBGK51UNwnd2JnqR8cvGGTILgrDLf9sjO7K2OZBZBnf7Pp2OLsfvhQZDZD";
	DefaultListModel<String> listaPostsFB = new DefaultListModel<String>();
	DefaultListModel<String> listaForSearchPostsFB = new DefaultListModel<String>();
	public void AuthUser() {
		String domain = "http://radixcode.com/";
		String appID = "1115442835290294";
		String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appID
				+ "&redirect_uri=" + domain + "&scope=user_about_me,"
				+ "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
				+ "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
				+ "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
				+ "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";

		/*
		 * System.setProperty("webdriver.gecko.driver",
		 * "C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\geckodriver.exe");
		 * WebDriver driver = new FirefoxDriver();
		 * driver.get("http://www.facebook.com");
		 * driver.findElement(By.id("email")).sendKeys(username.getText());
		 * driver.findElement(By.id("pass")).sendKeys(password.getText());
		 * driver.findElement(By.id("u_0_2")).click();
		 * System.out.println("dsd");
		 */

		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

		int cont = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				if (aPost.getMessage() != null) {
					aPostmew = aPost;
					cont++;
					listaPostsFB.addElement(aPost.getCreatedTime() + " - " + aPost.getMessage());
				}
			}
		}

		System.out.println(cont);
		// driver.quit();
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken
	 *            the accessToken to set
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
	 * @param aPostmew
	 *            the aPostmew to set
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
				if (partes[palavras_do_tweet].equals(tag)) {
					listaForSearchPostsFB.addElement(element);
				}
			}
		}
		listaForSearchPostsFB.clear();
	}

	@SuppressWarnings("deprecation")
	public void post(String message) {
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", message));
	}

}
