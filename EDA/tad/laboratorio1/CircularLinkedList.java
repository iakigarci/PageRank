package laboratorio1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements ListADT<T> {

	// Atributos
	protected Node<T> last; // apuntador al ultimo
	protected String descr;  // descripción
	protected int count;

	// Constructor
	public CircularLinkedList() {
	    last = null;
		descr = "";
		count = 0;
	}
	
	
	public void setDescr(String nom) {
	  descr = nom;
	}

	public String getDescr() {
	  return descr;
	}

	public T removeFirst() {
	// Elimina el primer elemento de la lista
        // Precondición: la lista tiene al menos un elemento
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		if(this.isEmpty()) {
			return null;
		}else {
			Node<T> act = this.last.next;
			T temp = act.data;
			act = act.next;
			last.next = act;
			count = count-1;
			return temp;
		}
	}

	public T removeLast() {
	// Elimina el �ltimo elemento de la lista
        // Precondici�n: la lista tiene al menos un elemento
			// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		Node<T> act = this.last.next;
		boolean acabar = false;
		T temp = null;
		while(!acabar) {
			if(act.next==last) {
				acabar = true;
				act.next = last.next;
				last = act;
				count--;
				temp = act.data;
			}
			act = act.next;
		}
		return temp;
	}

	public T remove(T elem) {
	//Elimina un elemento concreto de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		T temp = null;
		if(!this.isEmpty()) {
			if(elem != null) {
				Node<T> act = this.last;
				boolean acabar = false;
				while(!acabar) {
					if( act.next.data.equals(elem) && !(act.next == last)) {
						acabar = true;
						temp = act.data;
						act.next = act.next.next;
						count--;
					}else if( (act.next ==last) && last.data.equals(elem) ) {
						acabar = true;
						temp = act.next.data;
						act.next = this.last.next;
						this.last = act;
						count--;
					}
					act = act.next;
				}
			}
		}
		return temp;
	}

	public T first() {
	//Da acceso al primer elemento de la lista
	      if (isEmpty())
	          return null;
	      else return last.next.data;
	}

	public T last() {
	//Da acceso al �ltimo elemento de la lista
	      if (isEmpty())
	          return null;
	      else return last.data;
	}

	public boolean contains(T elem) {
		boolean esta = false;
		if(!this.isEmpty()) {
			Node<T> act = this.last;
			while(!esta) {
				if(act.data.equals(elem)) {
					esta = true;
				}
				act = act.next;
			}
		}
		return esta;
	}

	public T find(T elem) {
	//Determina si la lista contiene un elemento concreto, y develve su referencia, null en caso de que no est�
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		T temp = null;
		if(!this.isEmpty()) {
			Node<T> act = this.last;
			boolean esta = false;
			while(!esta) {
				if(act.data.equals(elem)) {
					esta = true;
					temp = act.data;
				}else if( act.next.equals(this.last)) {
					esta = true;
					System.out.println(elem+" NO SE ENCUENTRA EN LA LISTA");
				}
				act = act.next;
			}
		}
		return temp;
	}

	public boolean isEmpty() 
	//Determina si la lista est� vac�a
	{ return last == null;};
	
	public int size() 
	//Determina el n�mero de elementos de la lista
	{ return count;};
	
	/** Return an iterator to the stack that iterates through the items . */ 
	public Iterator<T> iterator() { return new ListIterator(); } 

	   // an iterator, doesn't implement remove() since it's optional 
	private class ListIterator implements Iterator<T> { 
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		Node<T> act = null;
		boolean acabar;
		
		public ListIterator() {
			if(!isEmpty()) {
				this.act = last.next;
				acabar = false;
			}
		}
		public boolean hasNext() {
			if(act == null) {
				return false;
			}else if( this.act.equals(last.next) & acabar) {
				return false;
			}else {
				return true;
			}
		}
		
		public T next() {
			if( !this.hasNext() ) {
				throw new NoSuchElementException("No hay mas elementos");
			}else {
				T tmp = act.data;
				act = act.next;
				if(act.equals(last.next)) {
					acabar = true;
				}
				return tmp;
			}
		}
		
		public void remove() {
			
		}
		
	 } // private class
		
		
     public void visualizarNodos() {
		System.out.println(this.toString());
     }

		@Override
	public String toString() {
		String result = new String();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T elem = it.next();
			result = result + "[" + elem.toString() + "] \n";
		}	
		return "SimpleLinkedList " + result + "]";
	}

}
