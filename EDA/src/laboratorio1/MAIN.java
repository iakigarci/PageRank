package laboratorio1;


import java.util.ArrayList;
import java.util.Iterator;

public class MAIN {

	public static void main(String[] args) {

		System.out.println("¿QUÉ QUIERES HACER?");
		System.out.println("1- Casos de prueba Grafo");
		System.out.println("2- Ejercicio Grafo");
		System.out.println("3- Menu");
        System.out.println("4- Page Rank");
		int accion = Teclado.leerInteger();
		String f1 = null;
		String f2 = null;
		String f3 = "words";
		switch (accion) {
			case 1:
				f1 = "index";
				f2 = "pld-arcs-1-N";
				leerFicheros(f1,f2,f3);
				Grafo.getMiGrafo().pruebaGrafo();
				break;
			case 2:
				f1 = "indexGrafo";
				f2 = "pldGrafo";
				f3 = "words";
				leerFicheros(f1,f2,f3);
				Grafo.getMiGrafo().inicializarGrafo();
				Grafo.getMiGrafo().printGrafo();
				break;
			case 3:
				boolean acabar = false;
				while (!acabar) {
					menu();
					System.out.println("¿Quieres continuar en nuestro buscador?");
					if (!Teclado.leerBoolean()) {
						acabar = true;
					}
				}
				break;
            case 4:
                f1 = "smallindex";
                f2 = "smallpld-arcs-1-N";
                leerFicheros(f1,f2,f3);
                Internet.getMiInternet().imprimirHash(Internet.getMiInternet().pageRank());


		}

		System.out.println("�Quieres guardar los ficheros nuevos?");
		if (Teclado.leerBoolean()) {
			System.out.println("Escribiendo webs...");
			Internet.getMiInternet().escribirFicheroWeb("index");
			System.out.println("Escribiendo enlaces...");
			Enlaces.getMiEnlaces().escribirFicheroEnlaces("pld-arcs-1-N");
		}
		System.out.println("�HASTA LA PR�XIMA!");
	}

	public static void leerFicheros(String f1, String f2,String f3){

		System.out.println("Leyendo los ficheros del programa...");
		Internet.getMiInternet().leerFicheroWeb(f1);
		System.out.println("HE LEIDO EL FICHERO WEB");
		Enlaces.getMiEnlaces().leerFicheroEnlaces(f2);
		System.out.println("HE LEIDO EL FICHERO ENLACES");
		Diccionario.getDiccionario().leerFicheroDiccionario(f3);
		System.out.println("HE LEIDO EL FICHERO DICCIONARIO");
	}

	public static void menu(){
			System.out.println("\n-----------------------------------------------------------------------------------\n ");
			System.out.println("                                BIENVENIDO A LEEX                                 ");
			System.out.println("\n----------------------------------------------------------------------------------\n ");
			System.out.println("�Qu� acci�n quieres realizar? Teclea un n�mero");
			System.out.println("1- Buscar una web con una palabra clave");
			System.out.println("2- Insercci�n de una nueva p�gina web");
			System.out.println("3- Buscar los enlaces de una web");
			System.out.println("4- Buscar las palabras de una web");
			System.out.println("5- Lista ordenada de las webs");
			System.out.println("6- Buscar una web con identificador/nombre web");
			String palabra;
			String nombre;
			int pId;
			int n = Teclado.leerInteger();
			switch (n) {
				case 1:
					System.out.println("Introcuce la palabra clave para realizar la busqueda");
					palabra = Teclado.leerString();
					ArrayList<String> listaWeb;
					listaWeb = Diccionario.getDiccionario().word2web(palabra);
					if (listaWeb.size() != 0) {
						System.out.println("Las p�ginas webs son: ");
						Iterator<String> itr = listaWeb.iterator();
						while (itr.hasNext()) {
							nombre = itr.next();
							System.out.println("- " + nombre);
						}
					}
					break;

				case 2:
					Internet.getMiInternet().anadirWeb();
					break;
				case 3:

					System.out.println("�De qué página web quieres buscar sus enlaces?");
					palabra = Teclado.leerString();
					ArrayList<String> array = Internet.getMiInternet().buscarEnlaces(palabra);
					if (array.size() != 0) {
						int i = 0;
						while (i < array.size()) {
							System.out.println("- " + array.get(i));
							i++;
						}
					} else {
						System.out.println(palabra + " no tiene ningún enlace");
					}
					break;
				case 4:
					String pPalabra = Teclado.leerString();
					System.out.println(Diccionario.getDiccionario().esta(pPalabra));
					System.out.println("�Qu� p�gina web quieres buscar?");
					String pNombre = Teclado.leerString();
					UnorderedCircularLinkedList<String> lista = null;
					lista = Internet.getMiInternet().web2words(pNombre);
					System.out.println(lista);
					break;
				case 5:
					ArrayList<String> ordenado = Internet.getMiInternet().ordenarHash();
					break;
				case 6:
					System.out.println("�De qu� manera quieres buscar la p�gina web?");
					System.out.println("1- Identificador            2- Nombre de la web");
					int num = Teclado.leerInteger();
					if (num == 1) {
						System.out.println("Introduce el identificador");
						pId = Teclado.leerInteger();
						nombre = Internet.getMiInternet().id2Web(pId);
						if (nombre != null) {
							System.out.println("El nombre de la web con identificador " + pId + " es " + nombre);
						}
					} else if (num == 2) {
						System.out.println("Introduce el nombre ");
						nombre = Teclado.leerString();
						pId = Internet.getMiInternet().web2Id(nombre);
					}
					break;
			}
		}

	public static void leerFicherosPruebas(String f1, String f2){

		Internet.getMiInternet().leerFicheroWeb(f1);
		Enlaces.getMiEnlaces().leerFicheroEnlaces(f2);
	}

}
