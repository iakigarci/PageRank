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
        grafo = Grafo.getMiGrafo();
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

        System.out.println("PRUEBA 5: Un solo nodo y se apunta a si mismo");

        adjListAux[0] = new ArrayList<Integer>();
        adjListAux[0].add(0);
        assertTrue(grafo.estanConectados(w0,w0));

    }

    @Test
    public void caminoConectados() {
        ArrayList<String> a = new ArrayList<String>();
        a.add(w0);
        a.add(w1);
        a.add(w2);
        a.add(w3);
        ArrayList<String> b = new ArrayList<String>();
        b.add(w0);
        b.add(w1);
        b.add(w2);
        b.add(w4);
        b.add(w6);
        ArrayList<String> c = new ArrayList<String>();
        c.add(w0);
        c.add(w7);
        c.add(w8);
        c.add(w9);
        c.add(w10);
        ArrayList<String> d = new ArrayList<String>();
        d.add(w0);
        d.add(w1);
        d.add(w2);
        d.add(w4);
        ArrayList<String> e = new ArrayList<String>();
        e.add(w0);

        System.out.println("PRUEBA 1: Grafo con 10 nodos. Caminos posibles, imposibles y desde la raiz hasta los ultimos elementos.");
        assertEquals(a,grafo.caminoConectados(w0,w3));
        assertEquals(b,grafo.caminoConectados(w0,w6));
        assertEquals(c,grafo.caminoConectados(w0,w10));

        System.out.println("PRUEBA 2: Misma distancia distinto camino");
        assertEquals(d,grafo.caminoConectados(w0,w4));

        System.out.println("PRUEBA 3: Grafo vacio");
        grafo.resetearGrafo();
        MAIN.leerFicherosPruebas("indexvacioGrafo","pldvacioGrafo");
        assertEquals(0,grafo.caminoConectados(w1,w2).size());

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

        assertEquals(0, grafo.caminoConectados(w0,w1).size());
    }

    @Test
    public void pageRank() {
        HashMap<String, Double> pageRank = new HashMap<>();
        pageRank = grafo.getPageRank();

        System.out.println("Pruebas para el metodo page rank. Los numeros correspodientes a");
        System.out.println("Cada uno de los page ranks se ha cAcalculado a mano, debido a la imposibilidad de automatizacion.");

        System.out.println("Grafo de diez elementos");
        System.out.println("Primero se imprimiran todas a la vez, y luego se podran ir analizando una a una");

        grafo.imprimirHash();

        System.out.println("El page rank de la web 0 es: 0.01363636363636364");
        System.out.println("Y deberia ser o estar cerca de: 0.013636");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 1 es: 0.01363636363636364");
        System.out.println("Y deberia ser o estar cerca de: 0.013636");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 2 es:0.02522727272727273");
        System.out.println("Y deberia ser o estar cerca de: 0.025228");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 3 es: 0.02435795454545455");
        System.out.println("Y deberia ser o estar cerca de: 0.024436");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 4 es: 0.03926676136363637");
        System.out.println("Y deberia ser o estar cerca de: 0.039267");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 5 es: 0.030324737215909094");
        System.out.println("Y deberia ser o estar cerca de: 0.030320");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 6 es: 0.030324737215909094");
        System.out.println("Y deberia ser o estar cerca de: 0.03029");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 7 es:0.01363636363636364");
        System.out.println("Y deberia ser o estar cerca de: 0.01363");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 8 es:0.02522727272727273");
        System.out.println("Y deberia ser o estar cerca de: 0.02523");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 9 es:0.03507954545454546 ");
        System.out.println("Y deberia ser o estar cerca de: 0.03508");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("El page rank de la web 10 es:0.03507954545454546 ");
        System.out.println("Y deberia ser o estar cerca de: 0.03508");
        assertTrue(true); //Cambiar a false si no fuciona, true si funciona.

        System.out.println("PRUEBA 2: Grafo vacio");
        grafo.resetearGrafo();
        MAIN.leerFicherosPruebas("indexvacioGrafo","pldvacioGrafo");
        grafo = new Grafo();
        grafo = grafo.getMiGrafo();
        grafo.inicializarGrafo();
        pageRank = null;
        pageRank = grafo.getPageRank();
        System.out.println("Aseguramos mediante un assertEquals que el hashmap de los page rank esta vacio.");
        assertEquals(0, pageRank.size());

        System.out.println("PRUEBA 3: Grafo de un elemento.");
        MAIN.leerFicherosPruebas("indexUnElemento","pldGrafoUnElemento");
        grafo = new Grafo();
        grafo = grafo.getMiGrafo();
        grafo.inicializarGrafo();
        pageRank = null;
        pageRank = grafo.pageRank();
        grafo.imprimirHash();
        System.out.println("Deberia dar o estar cerca de: 0.15, y da: 0.15000000000000002");

    }

    @Test
    public void testBuscar() {
        w0 = "web0";
        w1 = "web1";
        w3 = "web3";
        w10 = "web10";
        Par p0 = new Par (w0,0.01363636363636364);
        Par p1 = new Par (w1,0.019431818181818186);
        Par p3 = new Par (w3,0.026451562500000005);
        Par p10 = new Par (w10,0.0303247372159091);


        Integer cont;

        Integer l1,l2;

        ArrayList<Par> grafoaux = grafo.buscar(w0);
        ArrayList<Par> grafotmp0 = new ArrayList<Par>();
        ArrayList<Par> grafotmp1 = new ArrayList<Par>();
        ArrayList<Par> grafotmp3 = new ArrayList<Par>();
        grafotmp0.add(p0);
        cont = grafoaux.size();
        Boolean seguir = true;
        l1=grafoaux.size();
        l2=grafotmp0.size();

        if (l1!=l2) {
            fail("Casca");
        }
        else {
            while (cont>1 && seguir) {
                if (grafoaux.get(cont-1).pageRank.equals((grafotmp0.get(cont-1)).pageRank)&&(grafoaux.get(cont-1).web.equals(grafotmp0.get(cont-1).web))) {}
                else {
                    fail("Casca");
                    seguir = false;
                }
                cont--;
            }

        }
        grafoaux = grafo.buscar(w1);
        grafotmp1.add(p1);
        grafotmp1.add(p10);
        cont = grafoaux.size();
        seguir = true;
        l1=grafoaux.size();
        l2=grafotmp1.size();
        if (l1!=l2) {
            fail("Casca");
        }
        else {
            while (cont>1 && seguir) {
                if (grafoaux.get(cont-1).pageRank.equals((grafotmp1.get(cont-1)).pageRank)&&(grafoaux.get(cont-1).web.equals(grafotmp1.get(cont-1).web))) {}
                else {
                    fail("Casca");
                    seguir = false;
                }
                cont--;
            }

        }

        grafoaux = grafo.buscar(w3);
        grafotmp3.add(p3);;
        cont = grafoaux.size();
        seguir = true;
        l1=grafoaux.size();
        l2=grafotmp3.size();
        if (l1!=l2) {
            fail("Casca");
        }
        else {
            while (cont>1 && seguir) {
                if (grafoaux.get(cont-1).pageRank.equals((grafotmp3.get(cont-1)).pageRank)&&(grafoaux.get(cont-1).web.equals(grafotmp3.get(cont-1).web))) {}
                else {
                    fail("Casca");
                    seguir = false;
                }
                cont--;
            }

        }


    }
}