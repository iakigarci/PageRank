package laboratorio1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Grafo {
	
	HashMap<String, Integer> th;
	String[] keys;
	ArrayList<Integer>[] adjList;
	
	public Grafo() {
		
	}
	
	public void inicializarGrafo(Internet webs) {
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
		}
	}

}
