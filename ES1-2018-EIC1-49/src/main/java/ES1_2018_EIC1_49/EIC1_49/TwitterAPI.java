package ES1_2018_EIC1_49.EIC1_49;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPI {
	private static List<Status>status;

	public static void main(String[] args) throws TwitterException {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("kVRX8HYyuuGfREHU52O7AUrWQ")
		.setOAuthConsumerSecret("XwGpzZUWsnTXwXQgSeCqAgDLBvelcOCkkX1RmYf4UwXZ60uoY9")
		.setOAuthAccessToken("1050057518121148419-d704OUJA2VWxqFhBHI2j1wkS0e4cpZ")
		.setOAuthAccessTokenSecret("JSlIzbw0hP0t1tsM7RhUABb0q1yD3ZVh96LYhw766CIn4");
		
		
	TwitterFactory tf = new TwitterFactory(configurationBuilder.build());	
	twitter4j.Twitter twitter = tf.getInstance();
    status = twitter.getUserTimeline("ISCTEIUL");
	
	for(Status s : status) {
		
		System.out.println("\n");
		System.out.println(s.getUser().getName()+ "   "+ s.getText());
	}
	}

	/**
	 * @return the status
	 */
	public static List<Status> getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public static void setStatus(List<Status> status) {
		TwitterAPI.status = status;
	}

}