package laboratorio1;

public class OrderedCircularLinkedList<T> extends CircularLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		Node<T> nuevo = new Node<T>(elem);
		if(super.isEmpty()) {
			super.last = nuevo;
			super.last.next = nuevo;
			count++;
		}else if( elem.equals("")||elem.equals(null)) {
		}else if( elem.equals(0)){
			nuevo.next = super.last.next;
			super.last.next = nuevo;
			super.count++;
		}else {
			boolean salir = false;
			Node<T> act = super.last;
			int cont = 0;
			while(!salir && (cont<count)) {
				int comparacion = ((Comparable<T>) act.data).compareTo(nuevo.data); // SI ES <0 QUIERE DECIR QUE ACT ES MENOR QUE NUEVO
				if( comparacion<0 ) { 
					if( ((Comparable<T>) act.next.data).compareTo(nuevo.data)<0 && !act.equals(super.last)) { //COMPRUEBA QUE EL SIGUIENTE NO SEA MENOR
						act =act.next;
					}else {
						nuevo.next = act.next;
						act.next = nuevo;
						if(cont==0) {super.last = nuevo;} // EL DATA DEL PUNTERO LAST ES MENOR QUE NUEVO
						super.count++;
						salir = true;
					}
				}else if(act.next.equals(super.last) && cont!=1) { //CASO: PENÚLTIMO
					nuevo.next = super.last;
					act.next = nuevo;
					super.count++;
					salir = true;
				}else if(cont==1){ //CASO: PRIMERO
					nuevo.next = act;
					last.next = nuevo;
					salir = true;
					super.count++;
				}else { // NO ES MENOR QUE NADA
					act = act.next;
					cont++;
				}			
			}
		}
	}
}
