package tests.twitter_api;

import static org.junit.Assert.*;

import java.awt.Frame;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import twitter_api.TwitterWindow;

public class TwitterWindowTest {
	TwitterWindow t = new TwitterWindow();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetFrame() {
		
		
		assertNotNull(t.getFrame());
	}

	@Test
	public void testSetFrame() {
		JFrame fra = new JFrame();
		assertNotEquals(t.getFrame(), fra);
		
	}

	@Test
	public void testTwitterWindow() {
	assertNotNull(t);
	}

}
