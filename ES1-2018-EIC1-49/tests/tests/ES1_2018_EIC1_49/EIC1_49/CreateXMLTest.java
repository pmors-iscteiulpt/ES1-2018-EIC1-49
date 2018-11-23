package tests.ES1_2018_EIC1_49.EIC1_49;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ES1_2018_EIC1_49.EIC1_49.CreateXML;
import ES1_2018_EIC1_49.EIC1_49.Utilizador;

public class CreateXMLTest {
	CreateXML c = new CreateXML();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateXML() {
	
		assertNotNull(c);
		
	}

	@Test
	public void testAddUser() {
		ArrayList<Utilizador> u = new ArrayList<Utilizador>();
		Utilizador  u1 = new Utilizador("pedro", "123", "lei");
		u.add(u1);
		assertEquals(u1, u.get(0));
		
		
	}

	@Test
	public void testGetusersList() {
		
		assertEquals(5, c.getusersList().size());
		
		
		
	}

}
