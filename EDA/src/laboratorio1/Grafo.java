package laboratorio1;

import java.lang.reflect.Array;
import java.util.*;

public class Grafo {

    private HashMap<String, Integer> th = new HashMap<String, Integer>();
    private String[] keys;
    private ArrayList<Integer>[] adjList;
    private static Grafo miGrafo = new Grafo();
    private HashMap<String, Double> pageRank = new HashMap<>();

    public Grafo() {

    }

    public static Grafo getMiGrafo() {
        return miGrafo;
    }

    public void inicializarGrafo() {
        //Iniciliazamos las estructuras de datos.

        Enlaces enlaces = Enlaces.getMiEnlaces();
        adjList = new ArrayList[enlaces.tamano()];
        for(int i=0; i<adjList.length; i++) {
            adjList[i] = new ArrayList<Integer>();
            adjList[i] = enlaces.id2Enlaces(i);
        }

        Internet mapaWeb = Internet.getMiInternet();
        Iterator<Map.Entry<Integer, String>> itr = mapaWeb.iterator();
        String web = null;
        int id = 0;
        while(itr.hasNext()) {
            Map.Entry<Integer, String> entrada = itr.next();
            web = entrada.getValue();
            id = entrada.getKey();
            th.put(web, id);

        }

        keys = new String[th.size()];
        for(String k: th.keySet()) keys[th.get(k)] = k;
    }

    public boolean estanConectados(String a1, String a2) {
        Queue<Integer> porExaminar = new LinkedList<Integer>();
        boolean enc = false;
        if (th==null || keys==null || adjList == null){
            System.out.println("Grafo vacio");
        }else if ((th.size()==1 || keys.length==1 || adjList.length == 1) && a1==a2){
            enc = true;
        }else if ((th.size()==1 || keys.length==1 || adjList.length == 1) && a1!=a2){
            System.out.println("Grafo de un elemento");
        }else {
            int pos1 = th.get(a1);
            int pos2 = th.get(a2);
            int act = pos1;

            boolean[] examinados = new boolean[th.size()];

            if (a1.equals(a2)) enc = true;
            else {
                porExaminar.add(pos1);
                examinados[pos1] = false;
                while (!enc && !porExaminar.isEmpty()) {
                    act = porExaminar.poll();
                    if (!examinados[act]) { //si el que esta por examinar no esta examinado
                        examinados[act] = true;
                        if (act == pos2) { //act es el ultimo
                            enc = true;
                        } else if (adjList[act] == null) {
                            continue;
                        } else {
                            for (int i = 0; i < adjList[act].size(); i++) {
                                porExaminar.add(adjList[act].get(i)); //anadimos todos los enlaces
                            }
                        }

                    }
                }
            }
        }
        return enc;

    }

    public ArrayList<String> caminoConectados(String a1, String a2){
        ArrayList<String> camino = new ArrayList<String>();
        Queue<Integer> porExaminar = new LinkedList<Integer>();
        boolean enc = false;

        if (th==null || keys==null || adjList == null){
            System.out.println("Grafo vacio");
        }else if ((th.size()==1 || keys.length==1 || adjList.length == 1) && a1==a2){
            enc = true;
        }else if ((th.size()==1 || keys.length==1 || adjList.length == 1) && a1!=a2){
            System.out.println("Grafo de un elemento");
        }else if (this.estanConectados(a1,a2)) {
            int pos1 = th.get(a1);
            int pos2 = th.get(a2);
            int act = pos1;

            boolean[] examinados = new boolean[th.size()];

            if (a1.equals(a2)) enc = true;
            else {
                porExaminar.add(pos1);
                examinados[pos1] = false;
                while (!enc && !porExaminar.isEmpty()) {
                    act = porExaminar.poll();
                    if (!examinados[act]) { //si el que esta por examinar no esta examinado
                        examinados[act] = true;
                        camino.add(keys[act]); //falta caso de que no existe
                        if (act == pos2) { //act es el ultimo
                            enc = true;
                        } else if (adjList[act] == null) {
                            continue;
                        } else {
                            for (int i = 0; i < adjList[act].size(); i++) {
                                porExaminar.add(adjList[act].get(i)); //anadimos todos los enlaces
                            }
                        }
                    }
                }
            }
        }
        int anterior = 0;
        int actual = 0;
        int tamano = camino.size()-1;
        for (int i=tamano; i>0; i--){
            anterior = Internet.getMiInternet().web2Id(camino.get(i-1));
            actual = Internet.getMiInternet().web2Id(camino.get(i));
            if (adjList[anterior]==null) { //anterior no esta en adjList
                camino.remove(camino.get(i-1));
            }else if (adjList[anterior]!=null && !adjList[anterior].contains(actual)) {
                camino.remove(camino.get(i-1));
            }
        }
        return camino;

    }

    public void printGrafo(){
        System.out.println(this.th);
        for (int i = 0; i < adjList.length; i++) {
            System.out.println(i+" => "+adjList[i]);

        }
    }

    public void printCamino(ArrayList<String> pCamino) {
        System.out.print("[ ");
        for( int i=0; i<pCamino.size(); i++) {
            System.out.print(pCamino.get(i)+" --> ");
        }
        System.out.print(" ]");
    }

    public void pruebaGrafo() {

        System.out.println("PRUEBA DEL GRAFO");
        System.out.println("Introduce un ID: ");
        this.inicializarGrafo();
        int id1 = Teclado.leerInteger();
        String web1 = keys[id1];
        System.out.println("ID :"+id1+"...URL: "+web1);
        System.out.println("Introduce un ID: ");
        int id2 = Teclado.leerInteger();
        String web2 = keys[id2];
        System.out.println("ID :"+id2+"...URL: "+web2);
        Stopwatch clock = new Stopwatch();
        ArrayList<String> array = this.caminoConectados(web1, web2);

        if (array.size()>0) {
            System.out.println("ESTÁN CONECTADOS");
            this.printCamino(array);
        }else {
            System.out.println("NO ESTÁN CONECTADOS");
        }
        System.out.println();
        System.out.println("Tiempo transcurrido: "+clock.elapsedTime());
    }

    public void resetearGrafo(){
        this.th.clear();
        this.keys = null;
        this.adjList = null;
        this.pageRank = new HashMap<>();
        //System.out.println("SE HA RESETEADO: "+th.size());
    }

    public HashMap<String, Double> getPageRank(){
        return this.pageRank;
    }

    public void setTh(HashMap<String, Integer> th) {
        this.th = th;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public void setAdjList(ArrayList<Integer>[] adjList) {
        this.adjList = adjList;
    }


    public ArrayList<Par> buscar(String palabraClave) {
        ArrayList<Par> resultado = new ArrayList<>();
        this.pageRank();
        System.out.println("SE HA REALIZADO EL PAGE RANK");
        Set<Map.Entry<String, Double>> mapaEntrada = this.pageRank.entrySet();
        Iterator<Map.Entry<String, Double>> itr = mapaEntrada.iterator();
        int i = 0;
        while(itr.hasNext()) {  //Asignamos la cantidad que va a dividir cada web
            Map.Entry<String, Double> entrada = itr.next();
            if (entrada.getKey().contains(palabraClave)) {
                //Introducimos la palabra de manera ordenada en el ArrayList
                resultado.add(new Par(entrada.getKey(), entrada.getValue()));
            }
        }

        //Ordenamos el ArrayList de Par
        Collections.sort(resultado,Par.Comparators.PR);
        for (int j = 0; j < resultado.size(); j++) {
            System.out.println(resultado.get(j).web+" -> "+resultado.get(j).pageRank);
        }

        return resultado;

    }

    public HashMap<String, Double> pageRank(){
        // Post: el resultado es el valor del algoritmo PageRank para cada web
        //de la lista de webs

        //Ya estan inicializados loos pageRank de las webs.

        boolean acabar = false;
        Double PR;
        Double division = 0.0;
        Double suma = 0.0;
        Double[] puntuacion = new Double[this.pageRank.size()];
        Double diff = 0.0;
        Double diffAct;
        ArrayList<Integer> enlacesEntrantes = new ArrayList<>();
        ArrayList<Integer> enlaces = new ArrayList<>();
        int d2;
        int id;
        Double d1;
        while (!acabar) {
            //Recorremos el HashMap para generar la parte de la formula PR(i)/C(i)
            Set<Map.Entry<String, Double>> mapaEntrada = this.pageRank.entrySet();
            Iterator<Map.Entry<String, Double>> itr = mapaEntrada.iterator();
            while(itr.hasNext()) {  //Asignamos la cantidad que va a dividir cada web
                Map.Entry<String, Double> entrada = itr.next();
                id = th.get(entrada.getKey());
                enlaces = Enlaces.getMiEnlaces().id2Enlaces(id); //obtenemos los enlaces
                if (enlaces==null || enlaces.size()==0 ){
                    puntuacion[id] = 0.0;
                }else{
                    puntuacion[id] = (double) entrada.getValue()/enlaces.size();
                }
            }

            //Recorremos el HashMap de pageRank y obtenemos los enlaces referenciados
            Set<Map.Entry<String,Double>> mapaEntradaPR = pageRank.entrySet();
            Iterator<Map.Entry<String, Double>> itrPR = mapaEntradaPR.iterator();
            while(itrPR.hasNext()) {
                Map.Entry<String, Double> entradaPR = itrPR.next();
                id = th.get(entradaPR.getKey());
                //Buscamos sus enlaces y obtenemos la suma de dividendo
                enlacesEntrantes = Enlaces.getMiEnlaces().referenciados(id);
                if (enlacesEntrantes!=null) {
                    for (int i = 0; i < enlacesEntrantes.size(); i++) {
                        suma = suma + (puntuacion[enlacesEntrantes.get(i)]); //obtenemos el sumatorio PR(i)/C(i)
                    }
                }else{
                    suma = 0.0;
                }
                PR = ((1-0.85)/this.pageRank.size())+(0.85*(suma)); //Calculamos el PR del actual
                diffAct = PR-this.pageRank.get(entradaPR.getKey());
                if (diffAct<0){ //valor absoluto
                    diffAct = diffAct*(-1);
                }
                diff = diff + diffAct; //introducimos la diferencia
                pageRank.put(entradaPR.getKey(),PR);
                suma = 0.0;
            }

            if ((diff < 0.0001)) {
                acabar = true;
            }else{
                diff = 0.0;
            }

        }

        return pageRank;
    }

    public void imprimirHash() {
        this.pageRank();
        Set<Map.Entry<String,Double>> mapaEntrada = this.pageRank.entrySet();
        Iterator<Map.Entry<String,Double>> itr = mapaEntrada.iterator();
        int i = 0;
        while(itr.hasNext()) {  //Asignamos la cantidad que va a dividir cada web
            Map.Entry<String,Double> entrada = itr.next();
            System.out.println(i);
            System.out.print(entrada.getValue());
            System.out.print(" -> ");
            System.out.println(entrada.getKey());
        }
    }
}
