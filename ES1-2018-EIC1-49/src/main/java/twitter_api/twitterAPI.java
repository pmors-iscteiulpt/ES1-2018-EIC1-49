package twitter_api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class twitterAPI {
	String token;
	String secretToken;
	public static List<Status> status;
	DefaultListModel<String> dlm = new DefaultListModel<String>();

	public void ISCTETimeLine(twitterAPI signin) {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("42kwK0tk5ewLK2hTCeWVl6ZlP")
				.setOAuthConsumerSecret("1GI0gnmoazfBM8B6IOtwy8X3e5HveKPBHj6aTPmmXYKiECj3vv")
				.setOAuthAccessToken("2262665663-xT78OcZzuGAWCZWc4P8B2SoKq0v8hJIiwMejGBD")
				.setOAuthAccessTokenSecret("As1GmoQKqptgEDGYM4SJ8C2nlThrBf3BmOjj2Yf1lgE2g");

		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		final Twitter twitterIt = tf.getInstance();
		try {
			status = twitterIt.getUserTimeline("ISCTEIUL");
		} catch (TwitterException e1) {
			e1.printStackTrace();
		}
		for (Status s : status) {
			dlm.addElement("[" + s.getUser().getName() + "] " + s.getText() + " " + s.getId());
		}

	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static List<Status> getStatus() {
		return status;
	}

	public static void setStatus(List<Status> status) {
		twitterAPI.status = status;
	}
	
	public void logIn() throws URISyntaxException, IOException, TwitterException {

		String testStatus = "Hello from twitter4j";

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("42kwK0tk5ewLK2hTCeWVl6ZlP")
				.setOAuthConsumerSecret("1GI0gnmoazfBM8B6IOtwy8X3e5HveKPBHj6aTPmmXYKiECj3vv");
		try {
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();

			try {
				System.out.println("-----");
				RequestToken requestToken = twitter.getOAuthRequestToken();

				System.out.println("Got request token.");
				System.out.println("Request token: " + requestToken.getToken());
				System.out.println("Request token secret: " + requestToken.getTokenSecret());

				System.out.println("|-----");

				AccessToken accessToken = null;

					System.out.println("Open the following URL and grant access to your account:");
					System.out.println(requestToken.getAuthorizationURL());
					System.out.print("Enter the PIN(if available) :");

					try {
						accessToken = twitter.getOAuthAccessToken(requestToken);
						twitter.setOAuthAccessToken(accessToken);
					} catch (TwitterException te) {
						if (401 == te.getStatusCode()) {
							System.out.println("Unable to get the access token.");
						} else {
							te.printStackTrace();
						}
					}
				System.out.println("Got access token.");
				System.out.println("Access token: " + accessToken.getToken());
				System.out.println("Access token secret: " + accessToken.getTokenSecret());

			} catch (IllegalStateException ie) {
				// access token is already available, or consumer key/secret is not set.
				if (!twitter.getAuthorization().isEnabled()) {
					System.out.println("OAuth consumer key/secret is not set.");
					System.exit(-1);
				}
			}

			System.out.println("ready exit");

			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}
	}
	
	public void tweet(String twit, twitterAPI signin) throws TwitterException {

		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("42kwK0tk5ewLK2hTCeWVl6ZlP")
				.setOAuthConsumerSecret("1GI0gnmoazfBM8B6IOtwy8X3e5HveKPBHj6aTPmmXYKiECj3vv")
				.setOAuthAccessToken("2262665663-xT78OcZzuGAWCZWc4P8B2SoKq0v8hJIiwMejGBD")
				.setOAuthAccessTokenSecret("As1GmoQKqptgEDGYM4SJ8C2nlThrBf3BmOjj2Yf1lgE2g");

		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitterIt = tf.getInstance();

		twitterIt.updateStatus(twit);
		System.out.println("tweet postado");
	}
	
	public void retweetIt(long statusId, twitterAPI signin) {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("42kwK0tk5ewLK2hTCeWVl6ZlP")
				.setOAuthConsumerSecret("1GI0gnmoazfBM8B6IOtwy8X3e5HveKPBHj6aTPmmXYKiECj3vv")
				.setOAuthAccessToken("2262665663-xT78OcZzuGAWCZWc4P8B2SoKq0v8hJIiwMejGBD")
				.setOAuthAccessTokenSecret("As1GmoQKqptgEDGYM4SJ8C2nlThrBf3BmOjj2Yf1lgE2g");

		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitterIt = tf.getInstance();
		try {
			twitterIt.retweetStatus(statusId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}