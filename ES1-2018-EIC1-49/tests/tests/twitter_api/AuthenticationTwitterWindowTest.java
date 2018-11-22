package tests.twitter_api;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import twitter_api.AuthenticationTwitterWindow;

public class AuthenticationTwitterWindowTest {
	AuthenticationTwitterWindow a = new AuthenticationTwitterWindow();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUser() {
		
		a.setUser("pedro");
	assertEquals("pedro", a.getUser());
	}

	@Test
	public void testSetUser() {
		
		a.setUser("pedro");
	assertEquals("pedro", a.getUser());
	}

	@Test
	public void testGetPass() {
		
		a.setPass("pedro");
	assertEquals("pedro", a.getPass());
	}

	@Test
	public void testSetPass() {
		a.setPass("pedro");
		assertEquals("pedro", a.getPass());
	}

	@Test
	public void testMain() {
		assertNotNull(a);
	}

	@Test
	public void testAuthenticationTwitterWindow() {
		assertNotNull(a);
	}

	@Test
	public void testGetFrame() {
		JFrame f = new JFrame();
		a.setFrame(f);
		assertEquals(f, a.getFrame());
		
	}

	@Test
	public void testSetFrame() {
		JFrame f = new JFrame();
		a.setFrame(f);
		assertEquals(f, a.getFrame());
	}

}
