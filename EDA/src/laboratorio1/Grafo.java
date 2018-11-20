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
		
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		int act = pos1;
		boolean enc = false;
		boolean[] examinados = new boolean[th.size()];
		
		if( a1.equals(a2)) enc = true;
		else {
			porExaminar.add(pos1);
			examinados[pos1] = false;
			while(!enc && !porExaminar.isEmpty()) {
				act = porExaminar.poll(); 
				if(!examinados[act]) { //si el que esta por examinar no esta examinado
					examinados[act] = true;
					if(act==pos2) { //act es el ultimo
						enc = true;
					}else {
						for(int i=0; i<adjList[act].size(); i++) {
							porExaminar.add(adjList[act].get(i)); //anadimos todos los enlaces
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
		
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		int act = pos1;
		boolean enc = false;
		boolean[] examinados = new boolean[th.size()];
		
		if( a1.equals(a2)) enc = true;
		else {
			porExaminar.add(pos1);
			examinados[pos1] = false;
			while(!enc && !porExaminar.isEmpty()) {
				act = porExaminar.poll(); 
				if(!examinados[act]) { //si el que esta por examinar no esta examinado
					examinados[act] = true;
					camino.add(keys[act]); //falta caso de que no existe
					if(act==pos2) { //act es el ultimo
						enc = true;
					}else {
						for(int i=0; i<adjList[act].size(); i++) {
							porExaminar.add(adjList[act].get(i)); //anadimos todos los enlaces
						}
					}
				}
			}
		}
		
		return camino;
		
	}
	
	public void printCamino(ArrayList<String> pCamino) {
		for( int i=0; i<pCamino.size(); i++) {
			System.out.println(pCamino.get(i)+"->");
		}
	}
	
	public void pruebaGrafo() {
		this.inicializarGrafo();
		System.out.println("PRUEBA DEL GRAFO");
		System.out.println("Introduce un ID: ");
		int id1 = Teclado.leerInteger();
		String web1 = keys[id1];
		System.out.println("ID :"+id1+"...URL: "+web1);
		System.out.println("Introduce un ID: ");
		int id2 = Teclado.leerInteger();
		String web2 = keys[id2];
		System.out.println("ID :"+id2+"...URL: "+web2);
		
		boolean conectados = false;
		conectados = this.estanConectados(web1, web2);
		
		if (conectados) {
			System.out.println("EST�N CONECTADOS");
			this.printCamino(this.caminoConectados(web1, web2));
		}else {
			System.out.println("NO EST�N CONECTADOS");
		}
		
	}
	
	
}
