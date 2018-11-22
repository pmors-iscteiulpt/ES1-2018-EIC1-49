package tests.ES1_2018_EIC1_49.EIC1_49;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ES1_2018_EIC1_49.EIC1_49.App;

public class AppTest {
App t = new App();
	

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
	public void testGetOAuthConsumerKey() {
		assertNotNull(t.getOAuthConsumerKey());
	}

	@Test
	public void testSetOAuthConsumerKey() {
		assertNotNull(t.getOAuthConsumerKey());	}

	@Test
	public void testGetOAuthConsumerSecret() {
		assertNotNull(t.getOAuthConsumerSecret());	
	}



	@Test
	public void testGetAccessToken() {
	assertNotNull(t.getAccessToken());
	}


	@Test
	public void testGetAccessTokenSecret() {
		assertNotNull(t.getAccessTokenSecret());
	}

	@Test
	public void testSetAccessTokenSecret() {
		t.setAccessTokenSecret("aaa");
		assertEquals("aaa", t.getAccessTokenSecret());
	}

}
