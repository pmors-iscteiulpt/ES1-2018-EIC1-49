package twitter_api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
	private static List<Status> status;
	private static List<User> user;
	public static List<URLEntity> entity;
	public DefaultListModel<String> dlm = new DefaultListModel<String>();
	private DefaultListModel<String> searchTagList = new DefaultListModel<String>();
	private DefaultListModel<String> followersList = new DefaultListModel<String>();
	private DefaultListModel<String> followingList = new DefaultListModel<String>();
	public DefaultListModel<String> post_24h = new DefaultListModel<String>();
	private PrintWriter pw;

<<<<<<< HEAD
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
=======
	private int numero_followers;
	private int numero_following;
>>>>>>> refs/remotes/origin/master

	/**
	 * Funcao que faz login no Twitter atravez dos tokens da API do twitter
	 * 
	 * @author Daniel Leal
	 */

	public void logIn() throws URISyntaxException, IOException, TwitterException {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("cgfMyRg4OvgBHqqDLHWqlczI8")
				.setOAuthConsumerSecret("szP6FycfMw9qDbIwucfbBuIcsY6HCcsrkRpf3xjVAXBNBMCeZx");
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

<<<<<<< HEAD
	/**
	 * Funçao que cria um novo tweet na aplicaçao e publica na feed do utilizador em
	 * sessao
	 * 
	 * @author Daniel Leal
	 */

	public void tweet(String twit, twitterAPI signin) throws TwitterException {
=======
	public void getSavedTweets() throws FileNotFoundException, UnsupportedEncodingException {
		dlm.clear();
		try {
			Scanner scanner = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\twitterDataBase.txt")));
			while (scanner.hasNextLine()) {
				String aux = scanner.nextLine();
				dlm.addElement(aux);
			}
			scanner.close();
>>>>>>> refs/remotes/origin/master

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ISCTETimeLine(twitterAPI signin) throws FileNotFoundException, UnsupportedEncodingException {

		if (connectedToInternet() == false) {
			getSavedTweets();
		} else {
			ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
			configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("cgfMyRg4OvgBHqqDLHWqlczI8")
					.setOAuthConsumerSecret("szP6FycfMw9qDbIwucfbBuIcsY6HCcsrkRpf3xjVAXBNBMCeZx")
					.setOAuthAccessToken("2262665663-5A9tScXexyBrFffm6wZ6BW4bIRtetP8BtWbLcxr")
					.setOAuthAccessTokenSecret("IONaPHDBBQ5g7B69056rtAhWHAUmpRZeWvSIO3HubnAVF");

			TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
			final Twitter twitterIt = tf.getInstance();
			try {

				status = twitterIt.getUserTimeline("ISCTEIUL");
			} catch (TwitterException e1) {
				e1.printStackTrace();
			}
			pw = new PrintWriter(new File(
					"C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\twitterDataBase.txt"));
			for (Status s : status) {

				String response = "[" + s.getUser().getName() + "]";
				String text = s.getText();

				dlm.addElement(response + "  " + text);

				text = text.replaceAll("\t", " , ");
				text = text.replaceAll("\n", " , ");
				pw.println(response + "  " + text);

			}
			String followers = "" + numero_followers;
			String following = "" + numero_following;
			pw.println("*");
			pw.println(followers);
			pw.println(following);
			pw.close();
		}
	}

	public String getNumberFollowers() {
		showFollowersList();
		String nf = new Integer(numero_followers).toString();
		return nf;

	}

	public void getSavedFollowers() throws FileNotFoundException, UnsupportedEncodingException {
		followersList.clear();
		try {
			Scanner scanner = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\followers.txt")));
			while (scanner.hasNextLine()) {
				String aux = scanner.nextLine();
				followersList.addElement(aux);
			}
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showFollowersList() {

		if (connectedToInternet() == false) {
			try {
				getSavedFollowers();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
			configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("cgfMyRg4OvgBHqqDLHWqlczI8")
					.setOAuthConsumerSecret("szP6FycfMw9qDbIwucfbBuIcsY6HCcsrkRpf3xjVAXBNBMCeZx")
					.setOAuthAccessToken("2262665663-5A9tScXexyBrFffm6wZ6BW4bIRtetP8BtWbLcxr")
					.setOAuthAccessTokenSecret("IONaPHDBBQ5g7B69056rtAhWHAUmpRZeWvSIO3HubnAVF");
			System.out.println("0");
			TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
			Twitter twitterIt = tf.getInstance();
			getFollowersList().clear();
			PagableResponseList<User> followers;
			int numberFollowers = 0;
			try {
				long cursor = -1;
				followers = twitterIt.getFollowersList(twitterIt.getId(), cursor);
				try {
					pw = new PrintWriter(new File(
							"C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\followers.txt"));
					for (User follower : followers) {
						numberFollowers = follower.getFollowersCount();
						System.out.println("ISto" + numero_followers);
						followersList.addElement(follower.getName() + " | " + follower.getScreenName());
						pw.println(follower.getName() + " | " + follower.getScreenName());
					}
					pw.close();
					
					pw = new PrintWriter(new File(
							"C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_followers.txt"));
					pw.println(getNumberFollowers());
					pw.close();
					this.numero_followers = numberFollowers;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}

	public String getNumberFollowing() {
		showFollowingList();
		String nf = new Integer(numero_following).toString();
		return nf;
	}

	public void getNumberOfFollowersOffline() {
		followersList.clear();
		try {
			Scanner scanner = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_followers.txt")));
			while (scanner.hasNextLine()) {
				String aux = scanner.nextLine();
				numero_followers = new Integer(aux);
			}
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getNumberOfFollowingOffline() {
		followingList.clear();
		try {
			Scanner scanner = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_following.txt")));
			while (scanner.hasNextLine()) {
				String aux = scanner.nextLine();
				numero_following = new Integer(aux);
			}
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSavedFollowing() throws FileNotFoundException, UnsupportedEncodingException {
		followingList.clear();
		try {
			Scanner scanner = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\followers.txt")));
			while (scanner.hasNextLine()) {
				String aux = scanner.nextLine();
				followingList.addElement(aux);
			}
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showFollowingList() {
		if (connectedToInternet() == false) {
			try {
				getSavedFollowing();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
			configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("cgfMyRg4OvgBHqqDLHWqlczI8")
					.setOAuthConsumerSecret("szP6FycfMw9qDbIwucfbBuIcsY6HCcsrkRpf3xjVAXBNBMCeZx")
					.setOAuthAccessToken("2262665663-5A9tScXexyBrFffm6wZ6BW4bIRtetP8BtWbLcxr")
					.setOAuthAccessTokenSecret("IONaPHDBBQ5g7B69056rtAhWHAUmpRZeWvSIO3HubnAVF");

			TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
			Twitter twitterIt = tf.getInstance();

			PagableResponseList<User> prlFollow;
			getFollowingList().clear();
			int numberFollowing = 0;
			try {
				long cursor = -1;
				prlFollow = twitterIt.getFriendsList(twitterIt.getId(), cursor);
				try {
					pw = new PrintWriter(new File(
							"C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\following.txt"));
					for (int i = 0; i < prlFollow.size(); i++) {
						User user = prlFollow.get(i);
						numberFollowing = user.getFollowersCount();
						followingList.addElement(user.getName() + " | " + user.getScreenName());
						pw.println(user.getName() + " | " + user.getScreenName());
					}
					pw.close();

					pw = new PrintWriter(new File(
							"C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_following.txt"));
					pw.println(getNumberFollowing());
					pw.close();
					this.numero_following = numberFollowing;
					System.out.println(numberFollowing);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (TwitterException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}

	public void tweet(String twit, twitterAPI signin) throws TwitterException {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("cgfMyRg4OvgBHqqDLHWqlczI8")
				.setOAuthConsumerSecret("szP6FycfMw9qDbIwucfbBuIcsY6HCcsrkRpf3xjVAXBNBMCeZx")
				.setOAuthAccessToken("2262665663-5A9tScXexyBrFffm6wZ6BW4bIRtetP8BtWbLcxr")
				.setOAuthAccessTokenSecret("IONaPHDBBQ5g7B69056rtAhWHAUmpRZeWvSIO3HubnAVF");

		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitterIt = tf.getInstance();

		twitterIt.updateStatus(twit);
		System.out.println("tweet postado");
	}

	/**
	 * Funçao que retweeta na aplicaçao e publica na feed do utilizador em sessao
	 * 
	 * @author Daniel Leal
	 */

	public void retweetIt(long statusId, twitterAPI signin) {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("cgfMyRg4OvgBHqqDLHWqlczI8")
				.setOAuthConsumerSecret("szP6FycfMw9qDbIwucfbBuIcsY6HCcsrkRpf3xjVAXBNBMCeZx")
				.setOAuthAccessToken("2262665663-5A9tScXexyBrFffm6wZ6BW4bIRtetP8BtWbLcxr")
				.setOAuthAccessTokenSecret("IONaPHDBBQ5g7B69056rtAhWHAUmpRZeWvSIO3HubnAVF");

		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitterIt = tf.getInstance();

		try {
			twitterIt.retweetStatus(statusId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

<<<<<<< HEAD
	/**
	 * Funçao que lista os utilizadores que seguem o utilizador em sessao
	 * 
	 * @author Daniel Leal
	 */

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

	/**
	 * Funçao que lista os utilizadores que o utilizador em sessao segue
	 * 
	 * @author Daniel Leal
	 */

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

	/**
	 * Funçao que conta o numero de followers do utilizador em sessao
	 * 
	 * @author Daniel Leal
	 */

	public String getNumberFollowers() throws IllegalStateException, TwitterException {
		showFollowersList();
		String nf = new Integer(numero_followers).toString();
		return nf;
	}

	/**
	 * Funçao que conta o numero de utilizadores que o utilizador em sessao segue
	 * 
	 * @author Daniel Leal
	 */

	public String getNumberFollowing() throws IllegalStateException, TwitterException {
		showFollowingList();
		String nf = new Integer(numero_following).toString();
		return nf;
	}

=======
>>>>>>> refs/remotes/origin/master
	public void searchForTagInISCTETimeLine(String tag) {
		for (int tweet = 0; tweet < dlm.size(); tweet++) {
			String element = dlm.getElementAt(tweet);
			String[] partes = element.split(" ");
			for (int palavras_do_tweet = 0; palavras_do_tweet < partes.length; palavras_do_tweet++) {
				if (partes[palavras_do_tweet].equals(tag)) {
					getSearchTagList().addElement(element);
				}
			}
		}

	}

<<<<<<< HEAD
	/**
	 * Funçao que filtra os tweets das ultimas 24h
	 * 
	 * @author Daniel Leal
	 */
=======
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
>>>>>>> refs/remotes/origin/master

	public void filtrarUltimas24horas() {
		Date today = new Date();
		Long dateInLong = today.getTime();
		for (int i = 0; i < status.size(); i++) {
			String element = dlm.getElementAt(i);
			Long millie = status.get(i).getCreatedAt().getTime();
			Long periodo_24 = dateInLong - 86400000 * 7;
			// ultimas 24horas
			if (millie >= periodo_24)
				post_24h.addElement(element);
		}
		if (post_24h.isEmpty())
			post_24h.addElement("::Não existe nenhum Tweet nas últimas 24h!::");
	}

	public static List<Status> getStatus() {
		return status;
	}

	public static void setStatus(List<Status> status) {
		twitterAPI.status = status;
	}

	public DefaultListModel<String> getSearchTagList() {
		return searchTagList;
	}

	public void setSearchTagList(DefaultListModel<String> searchTagList) {
		this.searchTagList = searchTagList;
	}

	public DefaultListModel<String> getFollowingList() {
		return followingList;
	}

	public void setFollowingList(DefaultListModel<String> followingList) {
		this.followingList = followingList;
	}

	public DefaultListModel<String> getFollowersList() {
		return followersList;
	}

	public void setFollowersList(DefaultListModel<String> followersList) {
		this.followersList = followersList;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
