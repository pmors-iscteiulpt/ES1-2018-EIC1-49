package tests.facebook_api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.restfb.types.Post;

import facebook_api.facebookAPI;

public class facebookAPITest {
	facebookAPI f = new facebookAPI();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAuthUser() {
	assertNotNull(f);
	}

	@Test
	public void testGetAccessToken() {
		f.setAccessToken("1111");
		assertEquals("1111", f.getAccessToken());
	}

	@Test
	public void testSetAccessToken() {
		f.setAccessToken("1111");
		assertEquals("1111", f.getAccessToken());
	}

	@Test
	public void testGetaPostmew() {
		assertNotNull(f);
	}

	@Test
	public void testSetaPostmew() {
		Post p = new Post();
		f.setaPostmew(p);
		assertEquals(p, f.getaPostmew());
	}

}
