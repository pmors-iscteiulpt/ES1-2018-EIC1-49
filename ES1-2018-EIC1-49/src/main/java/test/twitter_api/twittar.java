package test.twitter_api;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twittar {
	public void tweet(String twit, twitterSignIn signin) throws TwitterException {

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
}
