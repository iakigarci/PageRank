package laboratorio1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {
	
	Node<String> a;
	Node<Integer> b; 
	
	
	@Before
	public void setUp() throws Exception {
		
		a = new Node<String>("D");
		b = new Node<Integer>(1);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNode() {
		assertTrue(a.data.equals("D"));
		assertNull(a.next);
		assertTrue(b.data.equals(1));
		assertNull(b.next);
	}

}
