package ES1_2018_EIC1_49.EIC1_49;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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


    @FXML
    void AuthUser(ActionEvent event) {
String domain = "http://radixcode.com/";
String appID = "1115442835290294";
String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appID+"&redirect_uri="+domain+"&scope=user_about_me,"
        + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
        + "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
        + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
        + "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";

System.setProperty("webdriver.gecko.driver", "C:\\Users\\Pedro\\Downloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
WebDriver driver = new FirefoxDriver();
driver.get("http://www.facebook.com");
driver.findElement(By.id("email")).sendKeys(username.getText());
driver.findElement(By.id("pass")).sendKeys(password.getText());
driver.findElement(By.id("u_0_2")).click();
System.out.println("dsd");

String accessToken = "EAAGRh7ZAfrgoBAGEh3IAh9xhXDteBgzgrxDLqELJ7F9h1oLO1JnZBLwTpAQX1qp91OP22W7impZCMCbLRBU1PGVFnSUXncph1pklvpf13Y267zmZC1R02Jg7huqoZCgp08KF9LWFGMn6hr3IN8f80TUidZAYcmkkg0gGSKhDXaf3XUZCMk0ckXcEK7wZCTc8pLzmAcuvI26qngZDZD";
FacebookClient fbClient = new DefaultFacebookClient(accessToken);
Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

int cont =0;
for(List<Post> page : result) {
	for(Post aPost : page) {
		aPostmew = aPost;
		System.out.println(aPost.getMessage());
		System.out.println("fb.com/" + aPost.getId());
		cont++;
	}
}

   System.out.println(cont);
   driver.quit();
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



}

