package laboratorio1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Grafo {
	
	private HashMap<String, Integer> th = new HashMap<String, Integer>();
	private String[] keys;
	private ArrayList<Integer>[] adjList;
	private static Grafo miGrafo = new Grafo();

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
		//System.out.println("SE HA RESETEADO: "+th.size());
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
}
