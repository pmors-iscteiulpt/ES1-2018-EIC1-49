package tests.mail_api;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mail_api.SentMailWindow;

public class SentMailWindowTest {
	SentMailWindow s = new SentMailWindow();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() {
		assertNotNull(s);
	}

	@Test
	public void testSentMailWindow() {
		assertNotNull(s);
	}

	@Test
	public void testGetFrame() {
		JFrame f = new JFrame();
		s.setFrame(f);
		assertEquals(f, s.getFrame());
	}

	@Test
	public void testSetFrame() {
		JFrame f = new JFrame();
		s.setFrame(f);
		
		assertEquals(f, s.getFrame());
	}

}
