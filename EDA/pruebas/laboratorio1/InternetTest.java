package laboratorio1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InternetTest {

	private Diccionario pDiccionario;
	private Enlaces pEnlaces;
	private Internet pInternet;
	ArrayList<Integer> enlaces0 = new ArrayList();
	ArrayList<Integer> enlaces1 = new ArrayList();
	ArrayList<String> nombres0 = new ArrayList();
	ArrayList<String> nombres1 = new ArrayList();
	ArrayList<String> nombres2 = new ArrayList();
	
	@Before
	public void setUp() throws Exception {
		pDiccionario = Diccionario.getDiccionario();
		pDiccionario.leerFicheroDiccionario("wordsPruebas.txt");
		pEnlaces = Enlaces.getMiEnlaces();
		pEnlaces.leerFicheroEnlaces("enlacesPruebas");
		pInternet = Internet.getMiInternet();
		pInternet.leerFicheroWeb("websPruebas"); 
		
		enlaces1.add(1);
		
		
		nombres1.add("owoManland.com");
		nombres2.add("owo");
		
	}

	@After
	public void tearDown() throws Exception {
		pDiccionario.resetearHashmap();
		pInternet.resetearHashmap();
		pEnlaces.resetearHashmap();
	}

	@Test
	public void testGetMiInternet() {
		assertNotNull(pInternet);
	}

	@Test
	public void testGetMapa() {
		assertNotNull(pInternet.getMapa());
	}

	@Test
	public void testId2Web() {
		assertEquals("owoManland.com",pInternet.id2Web(0));
		assertEquals("uwuManland.com",pInternet.id2Web(1));
		assertEquals("killfurries.com",pInternet.id2Web(2));
		assertEquals("mimujersellevoalosniños.com",pInternet.id2Web(3));
		assertEquals("elpeluquerodethanos.com",pInternet.id2Web(4));
		assertEquals("87en3.com",pInternet.id2Web(5));
		assertEquals("lekagalletas.com",pInternet.id2Web(6));
		assertEquals("gamizVenAClase.com",pInternet.id2Web(7));
		assertEquals("viniciusAlCastilla.com",pInternet.id2Web(8));
	}

	@Test
	public void testWeb2Id() {
		assertEquals(0,pInternet.web2Id("owoManland.com"));
		assertEquals(1,pInternet.web2Id("uwuManland.com"));
		assertEquals(2,pInternet.web2Id("killfurries.com"));
		assertEquals(3,pInternet.web2Id("mimujersellevoalosniños.com"));
		assertEquals(4,pInternet.web2Id("elpeluquerodethanos.com"));
		assertEquals(5,pInternet.web2Id("87en3.com"));
		assertEquals(6,pInternet.web2Id("lekagalletas.com"));
		assertEquals(7,pInternet.web2Id("gamizVenAClase.com"));
		assertEquals(8,pInternet.web2Id("viniciusAlCastilla.com"));
	}
	

	@Test
	public void testBuscarEnlaces() {
		ArrayList<String> lista0 = new ArrayList<String>();
		ArrayList<String> lista1 = new ArrayList<String>();
		ArrayList<String> lista2 = new ArrayList<String>();
		ArrayList<String> lista3 = new ArrayList<String>();
		ArrayList<String> lista4 = new ArrayList<String>();
		ArrayList<String> lista5 = new ArrayList<String>();
		ArrayList<String> lista6 = new ArrayList<String>();
		ArrayList<String> lista7 = new ArrayList<String>();
		ArrayList<String> lista8 = new ArrayList<String>();
		
		lista0.add("owoManland.com");
		lista0.add("uwuManland.com");
		
		lista1.add("owoManland.com");
		lista1.add("uwuManland.com");
		
		lista2.add("lekagalletas.com");
		lista2.add("gamizVenAClase.com");
		
		lista3.add("uwuManland.com");
		lista3.add("killfurries.com");
		
		lista4.add("87en3.com");
		lista4.add("killfurries.com");
		
		lista5.add("elpeluquerodethanos.com");
		lista5.add("lekagalletas.com");
		
		lista6.add("lekagalletas.com");
		lista6.add("87en3.com");
		lista6.add("gamizVenAClase.com");
		
		lista7.add("viniciusAlCastilla.com");
		
		lista8.add("owoManland.com");
		lista8.add("uwuManland.com");
		lista8.add("killfurries.com");
		lista8.add("mimujersellevoalosniños.com");
		lista8.add("elpeluquerodethanos.com");
		lista8.add("87en3.com");
		lista8.add("lekagalletas.com");
		lista8.add("gamizVenAClase.com");
		
		assertEquals(lista0,pInternet.buscarEnlaces("owoManland.com"));
		assertEquals(lista1,pInternet.buscarEnlaces("uwuManland.com"));
		assertEquals(lista2,pInternet.buscarEnlaces("killfurries.com"));
		assertEquals(lista3,pInternet.buscarEnlaces("mimujersellevoalosniños.com"));
		assertEquals(lista4,pInternet.buscarEnlaces("elpeluquerodethanos.com"));
		assertEquals(lista5,pInternet.buscarEnlaces("87en3.com"));
		assertEquals(lista6,pInternet.buscarEnlaces("lekagalletas.com"));
		assertEquals(lista7,pInternet.buscarEnlaces("gamizVenAClase.com"));
		assertEquals(lista8,pInternet.buscarEnlaces("viniciusAlCastilla.com"));
	}

	@Test
	public void testBuscarWebs() {
		ArrayList<String> lista0 = new ArrayList<String>();
		ArrayList<String> lista1 = new ArrayList<String>();
		ArrayList<String> lista2 = new ArrayList<String>();
		ArrayList<String> lista3 = new ArrayList<String>();
		ArrayList<String> lista4 = new ArrayList<String>();
		ArrayList<String> lista5 = new ArrayList<String>();
		ArrayList<String> lista6 = new ArrayList<String>();
		ArrayList<String> lista7 = new ArrayList<String>();
		ArrayList<String> lista8 = new ArrayList<String>();
		
		lista0.add("owoManland.com");
		
		lista1.add("uwuManland.com");
		
		lista2.add("killfurries.com");
	
		lista3.add("mimujersellevoalosniños.com");
		
		lista4.add("elpeluquerodethanos.com");
		
		lista5.add("87en3.com");
		
		lista6.add("lekagalletas.com");
		
		lista7.add("gamizVenAClase.com");
	
		lista8.add("viniciusAlCastilla.com");
		
		
		assertEquals(lista0,pInternet.buscarWebs("owoManland.com"));
		assertEquals(lista1,pInternet.buscarWebs("uwuManland.com"));
		assertEquals(lista2,pInternet.buscarWebs("killfurries.com"));
		assertEquals(lista3,pInternet.buscarWebs("mimujersellevoalosniños.com"));
		assertEquals(lista4,pInternet.buscarWebs("elpeluquerodethanos.com"));
		assertEquals(lista5,pInternet.buscarWebs("87en3.com"));
		assertEquals(lista6,pInternet.buscarWebs("lekagalletas.com"));
		assertEquals(lista7,pInternet.buscarWebs("gamizVenAClase.com"));
		assertEquals(lista8,pInternet.buscarWebs("viniciusAlCastilla.com"));
	}

	@Test
	public void testWeb2words() {
		ArrayList<String> lista0 = new ArrayList<String>();
		ArrayList<String> lista1 = new ArrayList<String>();
		ArrayList<String> lista2 = new ArrayList<String>();
		ArrayList<String> lista3 = new ArrayList<String>();
		ArrayList<String> lista4 = new ArrayList<String>();
		ArrayList<String> lista5 = new ArrayList<String>();
		ArrayList<String> lista6 = new ArrayList<String>();
		ArrayList<String> lista7 = new ArrayList<String>();
		ArrayList<String> lista8 = new ArrayList<String>();
		lista0.add("owo");
		//lista0.add("u");
		lista0.add("com");
		lista0.add("land");
		lista1.add("uwu");
		//lista1.add("u");
		lista1.add("com");
		lista1.add("land");
		//lista2.add("u");
		lista2.add("com");
		lista2.add("furries");
		//lista3.add("u");
		lista3.add("com");
		//lista4.add("u");
		lista4.add("com");
		lista5.add("com");
		lista6.add("com");
		lista6.add("galleta");
		lista6.add("galletas");
		lista7.add("gam");
		lista7.add("com");
		lista7.add("gamiz");
		//lista8.add("u");
		lista8.add("com");
		
		assertEquals(lista0,pInternet.web2words("owoManland.com"));
		assertEquals(lista1,pInternet.web2words("uwuManland.com"));
		assertEquals(lista2,pInternet.web2words("killfurries.com"));
		assertEquals(lista3,pInternet.web2words("mimujersellevoalosniños.com"));
		assertEquals(lista4,pInternet.web2words("elpeluquerodethanos.com"));
		assertEquals(lista5,pInternet.web2words("87en3.com"));
		assertEquals(lista6,pInternet.web2words("lekagalletas.com"));
		assertEquals(lista7,pInternet.web2words("gamizVenAClase.com"));
		assertEquals(lista8,pInternet.web2words("viniciusAlCastilla.com"));
	}

	@Test
	public void testOrdenarHash() {

		ArrayList<String> lista0 = new ArrayList<String>();
		ArrayList<String> lista1 = pInternet.ordenarHash();
		
		lista0.add("87en3.com");
		lista0.add("elpeluquerodethanos.com");
		lista0.add("gamizVenAClase.com");
		lista0.add("killfurries.com");
		lista0.add("lekagalletas.com");
		lista0.add("mimujersellevoalosniños.com");
		lista0.add("owoManland.com");
		lista0.add("uwuManland.com");
		lista0.add("viniciusAlCastilla.com");
		
		assertEquals(lista0,lista1);
		
	}
	
	@Test
	public void testAnadirWeb() {
		pInternet.anadirWebTest(1, "owoManland.com", null, null);
		assertEquals(9,pInternet.ordenarHash().size());
		pInternet.anadirWebTest(1, "owo", enlaces0, null);
		assertEquals(10,pInternet.ordenarHash().size());
		pInternet.anadirWebTest(1, "uwu", enlaces1, null);
		assertEquals(11,pInternet.ordenarHash().size());
		pInternet.anadirWebTest(2, "lo", null, nombres0);
		assertEquals(12,pInternet.ordenarHash().size());
		pInternet.anadirWebTest(2, "kek", null, nombres1);
		assertEquals(13,pInternet.ordenarHash().size());
		pInternet.anadirWebTest(2, ";_;", null, nombres2);
		assertEquals(14,pInternet.ordenarHash().size());
	}

}