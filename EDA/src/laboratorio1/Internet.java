package laboratorio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Internet {

	private static Internet miInternet = new Internet();
	private HashMap<Integer, String> mapa = new HashMap<>();

	
	public Internet() {
		
	}
	
	public static Internet getMiInternet() {
		return miInternet;
	}

	public Map<Integer, String> getMapa() {
		return mapa;
	}
	
	public int tamano() {
		return this.mapa.size();
	}
	
	public void leerFicheroWeb(String pFichero) {
		File fichero = new File(System.getProperty("user.dir"),pFichero);
		if(!fichero.exists()) {
			System.out.println("��NO SE HA PODIDO CARGAR EL FICHERO!!");
		}
		Scanner sc = null;
		try {
			sc = new Scanner(fichero);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String linea = null;
		
		while(sc.hasNextLine()) {
			linea = sc.nextLine();
			String[] sp = linea.split("\\s+?");
			String pNombre = sp[0];
			if(sp.length!=0) {
				int pId = Integer.parseInt(sp[1]);
				mapa.put(pId,pNombre);
				Grafo.getMiGrafo().getPageRank().put(pNombre,0.25);
			}else {
				mapa.put(null, null);
			}
		}
		sc.close();
        System.out.println(this.mapa.size());
	}
	
	public void escribirFicheroWeb(String pFichero) {
		File fichero = new File(System.getProperty("user.dir"),pFichero);
		PrintWriter escritor = null;
		try {
			escritor = new PrintWriter(fichero);
			escritor.flush();
			Set<Map.Entry<Integer,String>> mapaEntrada = mapa.entrySet();
			Iterator<Map.Entry<Integer, String>> itr = mapaEntrada.iterator();
			while(itr.hasNext()) {
				Map.Entry<Integer, String> entrada = itr.next();
				String nombre = entrada.getValue();
				Integer identificador = entrada.getKey();
				String frase = nombre+" "+identificador;
				escritor.println(frase);
			}
			escritor.close(); 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String id2Web(int pId) { 
		String web = null;
		if(mapa.containsKey(pId)) {
			web = mapa.get(pId);
			//System.out.println("La web es "+web);
		}else {
			System.out.println("No se ha encontrado una p�gina con ese identificador");
		}
		return web;
	}
	
	public Iterator<Map.Entry<Integer, String>> iterator(){
		Set<Map.Entry<Integer,String>> mapaEntrada = mapa.entrySet();
		return  mapaEntrada.iterator();
	}
	
	public int web2Id(String web) {
		Set<Map.Entry<Integer,String>> mapaEntrada = mapa.entrySet();
		Iterator<Map.Entry<Integer, String>> itr = mapaEntrada.iterator();
		Boolean encontrado = false;
		int id = 0;
		while(itr.hasNext()&&!encontrado) {
			Map.Entry<Integer, String> entrada = itr.next();
			if(entrada.getValue().equals(web)) {
				id = entrada.getKey();
				encontrado = true;
			}
		}
		if(!encontrado) {
			System.out.println("No se ha encontrado la pagina web");
			id = -1;
		}
		return id;
	}

	public ArrayList<String> buscarEnlaces(String pNombre) {
		int id = this.web2Id(pNombre);
		ArrayList<String> nombres = new ArrayList<String>();
		if(id!=-1) {
			ArrayList<Integer> enlaces = Enlaces.getMiEnlaces().id2Enlaces(id);
			if(enlaces!=null) {
				int i = 0;
				while(i<enlaces.size()) {
					nombres.add(mapa.get(enlaces.get(i)));
					i++; 
				}
			}
		}	
		return nombres;
	}
	
	public void anadirWeb() {
		boolean acabar = false;
		Scanner sc = new Scanner(System.in);
		while(!acabar) {
			System.out.println("Introduce el nombre de la web");
			String nombre = Teclado.leerString();
			if(!this.mapa.containsValue(nombre)) {
				int pId = mapa.size()+1;
				this.mapa.put(pId, nombre);
				System.out.println("�C�mo quieres introducir los enlaces?");
				System.out.println("1-Identificador                      2-Nombre de web                      3-No quiero introducir");
				int num = Teclado.leerInteger();
				if(num==1) { 
					System.out.println("Introduce los identificadores de los enlaces de la web, separados por espacios.");
					String numeros = sc.nextLine();
					if(numeros!=null&numeros!=" "&numeros!="") {
						String[] sp = numeros.split("\\s+?");
						ArrayList<Integer> enlaces = new ArrayList();
						int i = 0;
						while(i<sp.length) {
							enlaces.add(Integer.parseInt(sp[i]));
							i++;
						}
						Enlaces.getMiEnlaces().anadir(pId, enlaces);
					}	
				}else if(num==2) {
					System.out.println("Introduce los nombres de los las webs, separados por espacios");
					String nombres = sc.nextLine();
					if(nombres!=null&&nombres!=(" ")&&nombres!=("")) {
						String[] sp = nombres.split("\\s+?");
						ArrayList<Integer> enlaces = new ArrayList();
						int i = 0;
						while(i<sp.length) {
							enlaces.add(this.web2Id(sp[i]));
							i++;
						}
						Enlaces.getMiEnlaces().anadir(pId, enlaces);
					}
				}
				System.out.println("�Quieres continuar introduciendo nuevas p�ginas webs?");
				if(!Teclado.leerBoolean()) {acabar = true;}
			}else {
				System.out.println("La web introducida ya existe. �Quieres continuar?");
				if(!Teclado.leerBoolean()) {acabar = true;}
			}
		}
	}
	
	public ArrayList<String> buscarWebs(String nombre){
		Set<Map.Entry<Integer,String>> mapaEntrada = mapa.entrySet();
		Iterator<Map.Entry<Integer, String>> itr = mapaEntrada.iterator();
		ArrayList<String> listaWeb = new ArrayList();
		while(itr.hasNext()) {
			Map.Entry<Integer, String> entrada = itr.next();
			if(entrada.getValue().contains(nombre)) {
				listaWeb.add(entrada.getValue());
			}
		}
		if(listaWeb.size()==0) {
			System.out.println("No existe ninguna p�gina web que tenga ese nombre");
		}
		return listaWeb;
	}
	
	public UnorderedCircularLinkedList<String> web2words(String pNombre) {
		int max = pNombre.length();
		boolean existe = false;
		UnorderedCircularLinkedList<String> lista = new UnorderedCircularLinkedList();
		for(int i=0;i<=max-3;i++) {
			int i1= 0;
			int i2 = 3+i;
			boolean salir = false;
			while(!salir) {
				String cadena = pNombre.substring(i1, i2);
				if(Diccionario.getDiccionario().esta(cadena)) {
					lista.addToRear(cadena);
					existe = true;
				} 
				if(max<=i2) {
					salir = true;
				}
				i1++;
				i2++;
			}
		}
		if(!existe) {
			System.out.println("Lo sentimos, no existe ni una coincidencia con "+pNombre);
		}
		return lista;
	}
	
	public ArrayList<String> ordenarHash(){
		ArrayList<String> arrayHash = this.hash2Array();
		ArrayList<String> ordenado = this.mergeSort(arrayHash);
		return ordenado;
	}
	
	private ArrayList<String> hash2Array(){
		ArrayList<String> lista = new ArrayList<String>(this.mapa.values());
		return lista;
	}
	
	private ArrayList<String> mergeSort(ArrayList<String> entero){
		ArrayList<String> izq = new ArrayList<String>();
		ArrayList<String> der = new ArrayList<String>();
		int ind;
		if(entero.size() == 1) {
			return entero;
		}else {
			ind = entero.size()/2;
			for(int i=0; i<ind; i++) {
				izq.add(entero.get(i));
			}
			for(int i=ind;i<entero.size();i++) {
				der.add(entero.get(i));
			}
			izq = mergeSort(izq);
			der = mergeSort(der);
			mezcla(izq,der,entero);
		}
		return entero;
	}
	
	private void mezcla(ArrayList<String> izq,ArrayList<String> der, ArrayList<String> entero) {
		int izqIndex = 0;
		int derIndex = 0;
		int enteroIndex = 0;
		while(izqIndex<izq.size() && derIndex<der.size()) {
			if( (izq.get(izqIndex).compareTo(der.get(derIndex))) <0){
				entero.set(enteroIndex, izq.get(izqIndex));
				izqIndex++;
			}else{
				entero.set(enteroIndex, der.get(derIndex));
				derIndex++;
			}
			enteroIndex++;
		} 
		ArrayList<String> resto;
		int restoIndex = 0;
		if(izqIndex >= izq.size()) {
			resto = der;
			restoIndex = derIndex;
		}else {
			resto = izq;
			restoIndex = izqIndex;
		}
		
		for(int i=restoIndex; i<resto.size();i++) {
			entero.set(enteroIndex, resto.get(i));
			enteroIndex++;
		}
	}
	
	public void anadirWebTest(int opcion, String nombre, ArrayList<Integer> pEnlaces , ArrayList<String> pNombres) {
		boolean acabar = false;
		while(!acabar) {
			if(!this.mapa.containsValue(nombre)) {
				int pId = mapa.size()+1;
				this.mapa.put(pId, nombre);
				
				if (opcion == 1) {
					Enlaces.getMiEnlaces().anadir(pId, pEnlaces);
				}
				if (opcion == 2) {
					
					Iterator<String>it = pNombres.iterator();
					ArrayList<Integer> enlaces2 = new ArrayList();
					while (it.hasNext()) {
						String pNombre = it.next();
						enlaces2.add(this.web2Id(pNombre));
					}
					Enlaces.getMiEnlaces().anadir(pId, enlaces2);
				}
				acabar = true;
			}
				
			else {
				System.out.println("La web introducida ya existe.");
				 acabar = true;
				}
			}
		}
	public void resetearHashmap() {
		mapa.clear();
	}

}
