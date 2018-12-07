package twitter_api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
	private static List<Status> status = new ArrayList<Status>();
	private static List<User> user;
	public static List<URLEntity> entity;
	public DefaultListModel<String> dlm = new DefaultListModel<String>();
	private DefaultListModel<String> searchTagList = new DefaultListModel<String>();
	private DefaultListModel<String> followersList = new DefaultListModel<String>();
	private DefaultListModel<String> followingList = new DefaultListModel<String>();
	public DefaultListModel<String> post_24h = new DefaultListModel<String>();
	private PrintWriter pw;
	private Scanner sc;

	private int num_followers;
	private int num_following;

	public void logIn() throws URISyntaxException, IOException, TwitterException {
		showFollowersList();
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

	public void getSavedTweets() throws FileNotFoundException, UnsupportedEncodingException {
		dlm.clear();
		try {
			sc = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\twitterDataBase.txt")));
			while (sc.hasNextLine()) {
				String aux = sc.nextLine();
				dlm.addElement(aux);
			}
			sc.close();

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
				System.out.println("naAPI" + getNum_followers());
				System.out.println("naAPI string" + getNumberFollowers());
			}
			pw.close();
		}
	}

	public String getNumberFollowers() {
		String nf = new Integer(num_followers).toString();
		return nf;

	}

	public void getSavedFollowers() throws FileNotFoundException, UnsupportedEncodingException {
		followersList.clear();
		String aux = null;
		try {
			sc = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\followers.txt")));
			while (sc.hasNextLine()) {
				aux = sc.nextLine();
				followersList.addElement(aux);

			}
			sc.close();

			sc = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_followers.txt")));
			while (sc.hasNextLine()) {
				aux = sc.nextLine();
				num_followers = new Integer(aux);

			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showFollowersList() {

		if (connectedToInternet() == false) {
			try {
				getSavedFollowers();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}
		} else {
			ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
			configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("cgfMyRg4OvgBHqqDLHWqlczI8")
					.setOAuthConsumerSecret("szP6FycfMw9qDbIwucfbBuIcsY6HCcsrkRpf3xjVAXBNBMCeZx")
					.setOAuthAccessToken("2262665663-5A9tScXexyBrFffm6wZ6BW4bIRtetP8BtWbLcxr")
					.setOAuthAccessTokenSecret("IONaPHDBBQ5g7B69056rtAhWHAUmpRZeWvSIO3HubnAVF");
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
						followersList.addElement(follower.getName() + " | " + follower.getScreenName());
						pw.println(follower.getName() + " | " + follower.getScreenName());
					}
					num_followers = numberFollowers;
					pw.close();

					pw = new PrintWriter(new File(
							"C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_followers.txt"));
					pw.println(num_followers);
					pw.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (TwitterException e2) {
				e2.printStackTrace();
			} catch (IllegalArgumentException e3) {
				e3.printStackTrace();
			}
		}
	}

	public int getNum_followers() {
		return num_followers;
	}

	public int getNum_following() {
		return num_following;
	}

	// public String getNumberFollowing() {
	// showFollowingList();
	// String nf = new Integer(num_following).toString();
	// return nf;
	// }
	//
	//
	// public void getNumberOfFollowingOffline() {
	// followingList.clear();
	// try {
	// Scanner scanner = new Scanner(new File(
	// ("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_following.txt")));
	// while (scanner.hasNextLine()) {
	// String aux = scanner.nextLine();
	// num_following = new Integer(aux);
	// }
	// scanner.close();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	public void getSavedFollowing() throws FileNotFoundException, UnsupportedEncodingException {
		followingList.clear();
		String aux = null;
		try {
			sc = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\following.txt")));
			while (sc.hasNextLine()) {
				aux = sc.nextLine();

				followingList.addElement(aux);
			}
			sc.close();

			sc = new Scanner(new File(
					("C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_following.txt")));
			while (sc.hasNextLine()) {
				aux = sc.nextLine();
				num_following = new Integer(aux);

			}
			sc.close();

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
					num_following = numberFollowing;
					pw.close();

					pw = new PrintWriter(new File(
							"C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\twitter_api\\number_following.txt"));
					pw.println(num_following);
					pw.close();

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

	public void filtrarUltimas24horas() {
		Date today = new Date();
		Long dateInLong = today.getTime();
		post_24h.clear();
		for (int i = 0; i < status.size(); i++) {
			String element = dlm.getElementAt(i);
			Long millie = status.get(i).getCreatedAt().getTime();
			Long periodo_24 = dateInLong - 86400000 * 9;
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
