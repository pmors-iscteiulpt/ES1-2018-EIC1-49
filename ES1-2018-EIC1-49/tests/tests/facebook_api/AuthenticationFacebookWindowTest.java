package tests.facebook_api;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ES1_2018_EIC1_49.EIC1_49.Utilizador;
import facebook_api.AuthenticationFacebookWindow;

public class AuthenticationFacebookWindowTest {
	AuthenticationFacebookWindow a = new AuthenticationFacebookWindow();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUser() {
		Utilizador u = new Utilizador();
	assertEquals(u, a.getUser());
	}

	@Test
	public void testSetUser() {
		
		a.setUser("pedro");
		assertEquals("pedro", a.getUser());
	}

	@Test
	public void testGetPass() {
	Utilizador u = new Utilizador();
	u.setPw("123");
	assertEquals("123", u.getPw());
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
	public void testAuthenticationFacebookWindow() {
		assertNotNull(a);
	}

	@Test
	public void testGetMail() {
		assertNotNull(a);
	}

	@Test
	public void testSetMail() {
		assertNotNull(a);
	}

	@Test
	public void testGetFrame() {
		JFrame f = new JFrame();
		a.setFrame(f);
		assertNotEquals(f, a.getFrame());
	}

	@Test
	public void testSetFrame() {
		JFrame f = new JFrame();
		a.setFrame(f);
		assertEquals(f, a.getFrame());
	
	}

	}


