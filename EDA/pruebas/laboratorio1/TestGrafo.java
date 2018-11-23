package laboratorio1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestGrafo {

    Grafo grafo;
    String w0,w1,w2,w3,w4,w5,w6,w7,w8,w9,w10;
    HashMap<String, Integer> thAux;
    String[] keysAux;
    ArrayList<Integer>[] adjListAux;
    @Before
    public void setUp() throws Exception {
        MAIN.leerFicherosPruebas("indexGrafo","pldGrafo");
        grafo = new Grafo();
        grafo.inicializarGrafo();
        //grafo.printGrafo();
        w0 = "web0";
        w1 = "web1";
        w2 = "web2";
        w3 = "web3";
        w4 = "web4";
        w5 = "web5";
        w6 = "web6";
        w7 = "web7";
        w8 = "web8";
        w9 = "web9";
        w10 = "web10";
    }

    @After
    public void tearDown() throws Exception {
        grafo.resetearGrafo();
    }



    @Test
    public void estanConectados() {
        System.out.println("PRUEBA 1: Grafo con 10 nodos. Caminos posibles, imposibles y desde la raiz hasta los ultimos elementos.");
        assertTrue(grafo.estanConectados(w0,w7));
        assertTrue(grafo.estanConectados(w7,w4));
        assertTrue(grafo.estanConectados(w1,w4));
        assertTrue(grafo.estanConectados(w0,w3));
        assertTrue(grafo.estanConectados(w0,w6));
        assertTrue(grafo.estanConectados(w0,w5));

        assertFalse(grafo.estanConectados(w1,w7));
        assertFalse(grafo.estanConectados(w7,w3));
        assertFalse(grafo.estanConectados(w6,w5));

        System.out.println("PRUEBA 2: Misma distancia distinto camino");
        assertTrue(grafo.estanConectados(w0,w4));

        System.out.println("PRUEBA 3: Grafo vacio");
        grafo.resetearGrafo();
        MAIN.leerFicherosPruebas("indexvacioGrafo","pldvacioGrafo");
        assertFalse(grafo.estanConectados(w1,w2));

        System.out.println("PRUEBA 4: un elemento");
        grafo.resetearGrafo();
        thAux = new HashMap<String, Integer>();
        thAux.put(w0,0);
        keysAux = new String[1];
        keysAux[0] = "web0";
        adjListAux = new ArrayList[1];
        adjListAux[0] = null;
        grafo.setAdjList(adjListAux);
        grafo.setKeys(keysAux);
        grafo.setTh(thAux);

        assertFalse(grafo.estanConectados(w0,w1));
    }

    @Test
    public void caminoConectados() {

    }

    @Test
    public void printGrafo() {
    }

    @Test
    public void printCamino() {
    }

    @Test
    public void pruebaGrafo() {
    }
}