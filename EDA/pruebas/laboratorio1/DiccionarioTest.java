package laboratorio1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiccionarioTest {
	
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
	public void testGetDiccionario() {
		assertNotNull(pDiccionario);
	}

	@Test
	public void testLeerFicheroDiccionario() {
		assertNotNull(pDiccionario);
	}

	@Test
	public void testWord2web() {
		assertEquals(pDiccionario.word2web("owo").get(0),"owoManland.com");
		//assertNotEquals(pDiccionario.word2web("owo").get(0),"uwuManland.com");
		assertEquals(pDiccionario.word2web("kitipasa").size(),0);
		assertEquals(pDiccionario.word2web("noexisto").size(),0);
		assertEquals(pDiccionario.word2web("com").size(),9);
	}

	@Test
	public void testMasDeTres() {
		assertTrue(pDiccionario.masDeTres("owo"));
		assertTrue(pDiccionario.masDeTres("uwu"));
		assertFalse(pDiccionario.masDeTres("u"));
		assertTrue(pDiccionario.masDeTres("furries"));
		assertTrue(pDiccionario.masDeTres("com"));
		assertTrue(pDiccionario.masDeTres("galleta"));
		assertTrue(pDiccionario.masDeTres("galletas"));
		assertTrue(pDiccionario.masDeTres("gamiz"));
		assertTrue(pDiccionario.masDeTres("gam"));
		assertTrue(pDiccionario.masDeTres("land"));
		assertFalse(pDiccionario.masDeTres("no"));
		assertTrue(pDiccionario.masDeTres("hahahah"));
		assertTrue(pDiccionario.masDeTres("kitipasa"));
	}

	public void testEsta() {
		assertTrue(pDiccionario.esta("owo"));
		assertTrue(pDiccionario.esta("uwu"));
		assertTrue(pDiccionario.esta("u"));
		assertTrue(pDiccionario.esta("furries"));
		assertTrue(pDiccionario.esta("com"));
		assertTrue(pDiccionario.esta("galleta"));
		assertTrue(pDiccionario.esta("galletas"));
		assertTrue(pDiccionario.esta("gamiz"));
		assertTrue(pDiccionario.esta("gam"));
		assertTrue(pDiccionario.esta("land"));
		assertTrue(pDiccionario.esta("no"));
		assertTrue(pDiccionario.esta("hahahah"));
		assertTrue(pDiccionario.esta("kitipasa"));
		assertFalse(pDiccionario.esta("hahahahgdhdgfh"));
		assertFalse(pDiccionario.esta("thdfhfdghthfdghb"));
		
	}

}