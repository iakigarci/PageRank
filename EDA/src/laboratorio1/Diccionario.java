package laboratorio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Diccionario {
	
	private HashSet<String> diccionarioMapa = new HashSet<String>();
	private static Diccionario miDiccionario = new Diccionario();
	
	public Diccionario() {
		
	}
	
	public static Diccionario getDiccionario() {
		return miDiccionario;
	}
	
	public void leerFicheroDiccionario(String pFichero) {
		
		File fichero = new File(System.getProperty("user.dir"),pFichero);
		if(!fichero.exists()) {
			System.out.println("�NO SE HA PODIDO CARGAR EL FICHERO DE PALABRAS!");
		}else{
			Scanner sc = null;
			try {
				sc = new Scanner(fichero);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String linea = null;
			while(sc.hasNextLine()) {				
				linea = sc.nextLine();
				if((linea != " " )&&(linea != null)) {
					diccionarioMapa.add(linea);
				}
			}
			sc.close();
		}
		System.out.println(diccionarioMapa.size());
	}
	
	
	public ArrayList<String> word2web(String nombre) {
		ArrayList<String> listaWeb = new ArrayList() ;
		if(diccionarioMapa.contains(nombre)) { 
			String palabra = nombre;
			if(!this.masDeTres(palabra)) {	
				System.out.println("Lo sentimos, pero m�nimo tiene que tener 3 caracteres");
			}else {
				listaWeb = Internet.getMiInternet().buscarWebs(palabra);
			}
		}else {
			System.out.println("No conocemos esa palabra");
		}
		return listaWeb;
	}

		
	public boolean masDeTres(String pNombre) {
		if (pNombre.length() >= 3) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean esta(String pPalabra) {
		boolean esta = this.diccionarioMapa.contains(pPalabra);
		return esta;
	}
	
	public void resetearHashmap() {
		diccionarioMapa.clear();
	}

}