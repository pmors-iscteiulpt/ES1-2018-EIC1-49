package tests.mail_api;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mail_api.PresentationMailWindow;

public class PresentationMailWindowTest {
	PresentationMailWindow p = new PresentationMailWindow(null);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() {
		assertNotNull(p);
	}

	@Test
	public void testPresentationMailWindow() {
		assertNotNull(p);
		
	}

	@Test
	public void testGetFrame() {
		JFrame h = new JFrame();
		p.setFrame(h);
		assertEquals(h, p.getFrame());
	}

	@Test
	public void testSetFrame() {
		JFrame h = new JFrame();
		p.setFrame(h);
		
		
		assertEquals(h, p.getFrame());
	}

}
