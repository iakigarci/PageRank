package laboratorio1;

public class Node<T> {
	public T data; 			// dato del nodo
	public Node<T> next; 	// puntero al siguiente nodo de la lista
	// -------------------------------------------------------------

	public Node(T dd){
		data = dd;
		next = null;
	}
}


