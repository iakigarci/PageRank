package laboratorio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teclado {

	private static Scanner sc = new Scanner(System.in);
	
	public static String leerString() {
		boolean correcto = false;
		String texto = null;
		int cont = 0;
		while(!correcto & cont<5) {
			try {
				texto = sc.next(); 
				correcto = true;
			}
			catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("¡¡ERROR!! Introduce una palabra por favor");
				cont++;
			}
		}
		 
		return texto;
	} 
	
	public static int leerInteger() {
		boolean correcto = false;
		int numero = 0;
		int cont = 0;
 
		while(!correcto & cont<5) {
			try {
				numero = sc.nextInt();
				correcto = true;
			}
			catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("¡¡ERROR!! Introduce un número porfavor");
				cont++;
			}
		}
		
		return numero;
	}
	
	public static boolean leerBoolean() {
		boolean correcto = false;
		boolean resultado = false; 
		String texto = null;
		
		do {
			try {
				texto = sc.next();
				if( texto.equalsIgnoreCase("si")||texto.equals("yes")||texto.equalsIgnoreCase("ok")||texto.equalsIgnoreCase("siu")) {
					resultado = true;
					correcto = true;
				}else if(texto.equalsIgnoreCase("no")) {
					resultado = false;
					correcto = true;
				}else {
					throw(new Exception());
				}
			}	
			catch (Exception e) {
				sc.nextLine();
				System.out.println("¡¡ERROR!! Introduce 'si' o 'no' porfavor");
			}
		}while(!correcto);
		return resultado;
	}
}