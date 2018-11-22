package tests.facebook_api;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facebook_api.facebookWindow;

public class facebookWindowTest {
	facebookWindow f = new facebookWindow();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFrame() {

JFrame j = new JFrame();
f.setFrame(j);
assertEquals(j, f.getFrame());
	}

	@Test
	public void testSetFrame() {

JFrame j = new JFrame();
f.setFrame(j);
assertEquals(j, f.getFrame());
	}

	@Test
	public void testMain() {
		assertNotNull(f);
	}

	@Test
	public void testFacebookWindow() {
		assertNotNull(f);
	}

}
