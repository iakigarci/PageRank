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

public class Enlaces {

	private Map<Integer, ArrayList<Integer>> enlaces = new HashMap<>();
	private static Enlaces miEnlaces = new Enlaces();
	
	public Enlaces() {
		
	}
	
	public Map<Integer, ArrayList<Integer>> getEnlaces() {
		return enlaces;
	}

	public static Enlaces getMiEnlaces() {
		return miEnlaces;
	}
	
	public int tamano() {
		return this.enlaces.size();
	}
	
	public void leerFicheroEnlaces(String pFichero) {
		File fichero = new File(System.getProperty("user.dir"),pFichero);
		if(!fichero.exists()) {
			System.out.println("¡¡NO SE HA PODIDO CARGAR EL FICHERO!!");
		} 
		Scanner sc = null;
		try {
			sc = new Scanner(fichero);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int j = 0;
		String linea = null;
		while(sc.hasNextLine()) {
			linea = sc.nextLine();
			String[] sp = linea.split("\\s+-->+\\s?");
			int pId = Integer.parseInt(sp[0]);
			if(sp.length!=1) {
				ArrayList<Integer> pEnlaces = new ArrayList<Integer>();
				String[] numeros = sp[1].split("\\s+");
				int i = 0;
				while(i<numeros.length) {
					pEnlaces.add(Integer.parseInt(numeros[i]));
					i++;
				}
				enlaces.put(pId, pEnlaces);
			}else {enlaces.put(pId, null);}
			//j++;
            //System.out.println(j);
		}
		sc.close();
        System.out.println(this.enlaces.size());
	}
	
	public void escribirFicheroEnlaces(String pFichero) {
		File fichero = new File(System.getProperty("user.dir"),pFichero);
		PrintWriter escritor = null;
		try {
			escritor = new PrintWriter(fichero);
			escritor.flush();
			Set<Map.Entry<Integer,ArrayList<Integer>>> mapaEntrada = enlaces.entrySet();
			Iterator<Map.Entry<Integer, ArrayList<Integer>>> itr = mapaEntrada.iterator();
			while(itr.hasNext()) {
				Map.Entry<Integer, ArrayList<Integer>> entrada = itr.next();
				Integer num = entrada.getKey();
				ArrayList<Integer> enlaces = entrada.getValue();
				StringBuilder str = new StringBuilder();
				for( int i = 0;i<enlaces.size();i++) {
					int enlacesInt = enlaces.get(i);
					str.append(enlacesInt+" ");
				}
				String frase = num+" --> "+str.toString();
				escritor.println(frase);
			}
			escritor.close();
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Integer> id2Enlaces(int pId){
		return enlaces.get(pId);
	}
	
	public void anadir(Integer pId,ArrayList<Integer> pEnlaces) {
		this.enlaces.put(pId, pEnlaces);
	}
	
	public void resetearHashmap() {
		enlaces.clear();
	}

	public ArrayList<Integer> referenciados(int ID){
	    ArrayList<Integer> resultado = new ArrayList<>();
        Set<Map.Entry<Integer,ArrayList<Integer>>> mapaEntrada = enlaces.entrySet();
        Iterator<Map.Entry<Integer, ArrayList<Integer>>> itr = mapaEntrada.iterator();
        while(itr.hasNext()) {
            Map.Entry<Integer, ArrayList<Integer>> entrada = itr.next();
            if (entrada.getValue()!=null) {
                if (entrada.getValue().contains(ID)) {
                    resultado.add(entrada.getKey());
                }
            }
        }
        return resultado;
    }
}	
