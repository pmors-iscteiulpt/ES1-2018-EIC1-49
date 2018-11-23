package tests.ES1_2018_EIC1_49.EIC1_49;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ES1_2018_EIC1_49.EIC1_49.Utilizador;

public class UtilizadorTest {
	Utilizador u = new Utilizador("pedro", "123", "lei");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUtilizador() {
		  assertNotNull(u);
		
	}


	@Test
	public void testGetUsername() {
		assertEquals("pedro", u.getUsername());
		
	}

	@Test
	public void testSetUsername() {
		Utilizador f = new Utilizador();
		f.setUsername("pedro");
		assertEquals("pedro", f.getUsername());
		
	}

	@Test
	public void testGetPw() {
		assertEquals("123", u.getPw());
		
	}

	@Test
	public void testSetPw() {
		Utilizador f = new Utilizador();
		f.setPw("345");
		
		assertEquals("345", f.getPw());
		
		
	}

	@Test
	public void testGetCurso() {
		assertEquals("lei", u.getCurso());
		
	}

	@Test
	public void testSetCurso() {
		Utilizador f = new Utilizador();
		f.setCurso("leo");
		
		assertEquals("leo", f.getCurso());
		
	}

	

}
