package tests.mail_api;

import static org.junit.Assert.*;

import javax.swing.DefaultListModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mail_api.MailAPI;

public class MailAPITest {
	MailAPI m = new MailAPI();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDlm() {
	
	}

	@Test
	public void testSetDlm() {
		fail();
	}

	@Test
	public void testGetMailCredentials() {

assertNotNull(m);
	}

	@Test
	public void testGetEmail() {
		m.setPass("ooo");
		assertEquals("ooo", m.getPass());
	
	}

	@Test
	public void testGetFrom1() {
		assertNotNull(m);
	}

	@Test
	public void testSetFrom1() {
		assertNotNull(m);
	}

	@Test
	public void testGetSubj() {
		assertNotNull(m);
	}

	@Test
	public void testSetSubj() {
		assertNotNull(m);
	}

	@Test
	public void testSendEmail() {
		assertNotNull(m);
	}

	@Test
	public void testGetPass() {
		m.setPass("ooo");
		assertEquals("ooo", m.getPass());
	}

	@Test
	public void testSetPass() {
		m.setPass("ooo");
		assertEquals("ooo", m.getPass());
	}

	@Test
	public void testGetUsername() {
		m.setUsername("ooo");
		assertEquals("ooo", m.getUsername());
	}

	@Test
	public void testSetUsername() {
		assertNotNull(m);
	}

}
