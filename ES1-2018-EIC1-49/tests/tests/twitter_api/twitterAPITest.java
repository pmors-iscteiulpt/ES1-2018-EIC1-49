package tests.twitter_api;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import twitter_api.twitterAPI;

public class twitterAPITest {
	twitterAPI t = new twitterAPI();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testISCTETimeLine() {
	assertNotNull(t);
	}

	@Test
	public void testToString() {
	String s=t.toString();
	assertEquals(s, t.toString());
	}

	@Test
	public void testGetStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testTweet() {
		assertNotNull(t);
	}

	@Test
	public void testRetweetIt() {
		assertNotNull(t);
	}

}
