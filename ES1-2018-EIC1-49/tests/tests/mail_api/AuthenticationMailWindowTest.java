package tests.mail_api;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ES1_2018_EIC1_49.EIC1_49.Utilizador;
import mail_api.AuthenticationMailWindow;

public class AuthenticationMailWindowTest {
	AuthenticationMailWindow u = new AuthenticationMailWindow();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUser() {
		assertNotNull(u);
	}

	@Test
	public void testSetUser() {
		assertNotNull(u);
	}

	@Test
	public void testGetPass() {
		u.setPass("ole");
		assertEquals("ole", u.getPass());
	}

	@Test
	public void testSetPass() {
	u.setPass("ole");
	assertEquals("ole", u.getPass());
	}

	@Test
	public void testMain() {
		assertNotNull(u);
	}

	@Test
	public void testAuthenticationMailWindow() {
		assertNotNull(u);
	}

	@Test
	public void testGetFrame() {
		assertNotNull(u);
	}

	@Test
	public void testSetFrame() {
		JFrame f = new JFrame();
		u.setFrame(f);
		assertEquals(f, u.getFrame());
		
	
	}

}
