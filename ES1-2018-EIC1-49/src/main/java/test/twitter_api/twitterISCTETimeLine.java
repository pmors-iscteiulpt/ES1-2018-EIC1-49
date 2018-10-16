package test.twitter_api;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twitterISCTETimeLine {
	public static List<Status> status;
	DefaultListModel<String> dlm = new DefaultListModel<String>();

	public void ISCTETimeLine(twitterSignIn signin) {
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
		twitterISCTETimeLine.status = status;
	}
}
