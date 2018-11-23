package laboratorio1;

public class UnorderedCircularLinkedList<T> extends CircularLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {
		//pre: una lista enlazada
		//post: se añade a la lista un elemento al principio
		Node<T> nuevo = new Node<T>(elem);
		if(super.count!=0) {
			nuevo.next = super.last.next;
			last.next = nuevo;
			this.count++;
		}else {
			last = nuevo;
			last.next = nuevo;
			super.count++;
		}

	}

	public void addToRear(T elem) {
	// a�ade un elemento al final 
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		Node<T> nuevo = new Node<T>(elem);
		if(super.count!=0) {
			nuevo.next = last;
			last = nuevo;
			this.count++;
		}else {
			last = nuevo;
			last.next = nuevo;
			super.count++;
		}
	}
	
	public void addAfter(T elem, T target) {
	// A�ade elem detr�s de otro elemento concreto, target,  que ya se encuentra en la lista
		// �COMPLETAR OPCIONAL!
		Node<T> nuevo = new Node<T>(elem);
		Node<T> act = super.last.next;
		if(!isEmpty()) {
			boolean salir = false;
			while(!salir) {
				if( act.data.equals(target)) {
					nuevo.next = act.next;
					act.next = nuevo;
					super.count++;
					salir = true;
				}else if(act.next.equals(super.last) && !act.next.data.equals(target)) {
					salir = true;
				}else {
					act = act.next;
				}
			}
		}
	}
}
