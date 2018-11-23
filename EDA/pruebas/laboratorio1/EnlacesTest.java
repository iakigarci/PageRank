package laboratorio1;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EnlacesTest {
	
	private Diccionario pDiccionario;
	private Enlaces pEnlaces;
	private Internet pInternet;
	
	@Before
	public void setUp() throws Exception {
		pDiccionario = Diccionario.getDiccionario();
		pDiccionario.leerFicheroDiccionario("wordsPruebas.txt");
		pEnlaces = Enlaces.getMiEnlaces();
		pEnlaces.leerFicheroEnlaces("enlacesPruebas");
		pInternet = Internet.getMiInternet();
		pInternet.leerFicheroWeb("websPruebas");
	}

	@After
	public void tearDown() throws Exception {
		pDiccionario.resetearHashmap();
		pInternet.resetearHashmap();
		pEnlaces.resetearHashmap();
	}

	@Test
	public void testGetEnlaces() {
		assertNotNull(pEnlaces.getEnlaces());
	}

	public void testGetMiEnlaces() {
		assertNotNull(pEnlaces);
	}

	@Test
	public void testLeerFicheroEnlaces() {
		assertNotNull(pEnlaces);
	}

	@Test
	public void testId2Enlaces() {
		ArrayList<Integer> lista0 = new ArrayList<Integer>();
		ArrayList<Integer> lista1 = new ArrayList<Integer>();
		ArrayList<Integer> lista2 = new ArrayList<Integer>();
		ArrayList<Integer> lista3 = new ArrayList<Integer>();
		ArrayList<Integer> lista4 = new ArrayList<Integer>();
		ArrayList<Integer> lista5 = new ArrayList<Integer>();
		ArrayList<Integer> lista6 = new ArrayList<Integer>();
		ArrayList<Integer> lista7 = new ArrayList<Integer>();
		ArrayList<Integer> lista8 = new ArrayList<Integer>();
		lista0.add(0);
		lista0.add(1);
		lista1.add(0);
		lista1.add(1);
		lista2.add(6);
		lista2.add(7);
		lista3.add(1);
		lista3.add(2);
		lista4.add(5);
		lista4.add(2);
		lista5.add(4);
		lista5.add(6);
		lista6.add(6);
		lista6.add(5);
		lista6.add(7);
		lista7.add(8);
		lista8.add(0);
		lista8.add(1);
		lista8.add(2);
		lista8.add(3);
		lista8.add(4);
		lista8.add(5);
		lista8.add(6);
		lista8.add(7);
		
		assertEquals(pEnlaces.id2Enlaces(0),lista0);
		assertEquals(pEnlaces.id2Enlaces(1),lista1);
		assertEquals(pEnlaces.id2Enlaces(2),lista2);
		assertEquals(pEnlaces.id2Enlaces(3),lista3);
		assertEquals(pEnlaces.id2Enlaces(4),lista4);
		assertEquals(pEnlaces.id2Enlaces(5),lista5);
		assertEquals(pEnlaces.id2Enlaces(6),lista6);
		assertEquals(pEnlaces.id2Enlaces(7),lista7);
		assertEquals(pEnlaces.id2Enlaces(8),lista8);
		
	}

}