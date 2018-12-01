package twitter_api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;

import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class twitterAPI {
	String token;
	String secretToken;
	public static List<Status> status;
	public static List<User> user;
	public static List<URLEntity> entity;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	DefaultListModel<String> searchTagList = new DefaultListModel<String>();
	DefaultListModel<String> followersList = new DefaultListModel<String>();
	DefaultListModel<String> followingList = new DefaultListModel<String>();
	DefaultListModel<String> post_24h = new DefaultListModel<String>();

	public int numero_followers;
	public int numero_following;

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
			dlm.addElement("[" + s.getUser().getName() + "] " + s.getText() + " " + s.getId() + " "
					+ s.getCreatedAt().getTime());
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

	public void showFollowersList() throws IllegalStateException, TwitterException {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("42kwK0tk5ewLK2hTCeWVl6ZlP")
				.setOAuthConsumerSecret("1GI0gnmoazfBM8B6IOtwy8X3e5HveKPBHj6aTPmmXYKiECj3vv")
				.setOAuthAccessToken("2262665663-xT78OcZzuGAWCZWc4P8B2SoKq0v8hJIiwMejGBD")
				.setOAuthAccessTokenSecret("As1GmoQKqptgEDGYM4SJ8C2nlThrBf3BmOjj2Yf1lgE2g");

		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitterIt = tf.getInstance();
		followersList.clear();
		PagableResponseList<User> prlFollow;
		int numberFollowers = 0;

		try {
			long cursor = -1;
			prlFollow = twitterIt.getFollowersList(twitterIt.getId(), cursor);
			for (int i = 0; i < prlFollow.size(); i++) {
				User user = prlFollow.get(i);
				numberFollowers = user.getFollowersCount();
				followersList.addElement(user.getName() + " | " + user.getScreenName());
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		this.numero_followers = numberFollowers;
	}

	public void showFollowingList() throws IllegalStateException, TwitterException {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("42kwK0tk5ewLK2hTCeWVl6ZlP")
				.setOAuthConsumerSecret("1GI0gnmoazfBM8B6IOtwy8X3e5HveKPBHj6aTPmmXYKiECj3vv")
				.setOAuthAccessToken("2262665663-xT78OcZzuGAWCZWc4P8B2SoKq0v8hJIiwMejGBD")
				.setOAuthAccessTokenSecret("As1GmoQKqptgEDGYM4SJ8C2nlThrBf3BmOjj2Yf1lgE2g");

		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitterIt = tf.getInstance();

		PagableResponseList<User> prlFollow;
		followingList.clear();
		int numberFollowing = 0;

		try {
			long cursor = -1;
			prlFollow = twitterIt.getFriendsList(twitterIt.getId(), cursor);
			for (int i = 0; i < prlFollow.size(); i++) {
				User user = prlFollow.get(i);
				numberFollowing = user.getFollowersCount();
				followingList.addElement(user.getName() + " | " + user.getScreenName());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		this.numero_following = numberFollowing;
	}

	public String getNumberFollowers() throws IllegalStateException, TwitterException {
		showFollowersList();
		String nf = new Integer(numero_followers).toString();
		return nf;
	}

	public String getNumberFollowing() throws IllegalStateException, TwitterException {
		showFollowingList();
		String nf = new Integer(numero_following).toString();
		return nf;
	}

	public void searchForTagInISCTETimeLine(String tag) {

		for (int tweet = 0; tweet < dlm.size(); tweet++) {
			String element = dlm.getElementAt(tweet);
			String[] partes = element.split(" ");
			for (int palavras_do_tweet = 0; palavras_do_tweet < partes.length; palavras_do_tweet++) {
				if (partes[palavras_do_tweet].equals(tag)) {
					searchTagList.addElement(element);
				}
			}
		}

	}

	public void filtrarUltimas24horas() {
		Date today = new Date();
		Long dateInLong = today.getTime();
		for (int tweet = 0; tweet < dlm.size(); tweet++) {
			String element = dlm.getElementAt(tweet);
			String[] partes = element.split(" ");
			int last_index = partes.length - 1;
			Long millie = Long.parseLong(partes[last_index]);
			Long periodo_24 = dateInLong - 86400000;
			// ultimas 24horas
			if (millie >= periodo_24)
				post_24h.addElement(element);
		}
	}
}
