   package tests.ES1_2018_EIC1_49.EIC1_49;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ES1_2018_EIC1_49.EIC1_49.Utilizadores;

public class UtilizadoresTest {
	Utilizadores  u = new Utilizadores();

	@Before
	public void setUp() throws Exception {
	assertNotNull(u);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetUtilizadores() {
		assertNotNull(u);
	}

	@Test
	public void testGetUtilizadores() {
		assertNotNull(u);
	}


}
