package laboratorio1;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderedCircularLinkedListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		OrderedCircularLinkedList<Integer> uwu = new OrderedCircularLinkedList<>();
		
		uwu.add(3);
		uwu.add(2);
		uwu.add(1);
		
		OrderedCircularLinkedList<Integer> uwu2 = new OrderedCircularLinkedList<>();
		
		OrderedCircularLinkedList<String> uwu3 = new OrderedCircularLinkedList<>();
		
		uwu3.add("Leticia");
		uwu3.add("Amanda");
		uwu3.add("Zapatea");
		
		assertTrue(uwu.size() == 3);
		assertTrue(uwu.last.data == 3);
		assertTrue(uwu.last.next.data == 1);
		assertTrue(uwu.last.next.next.data == 2);
		
		assertTrue(uwu2.size() == 0);
		assertNull(uwu2.last);
		
		assertTrue(uwu3.size() == 3);
		assertTrue(uwu3.last.data.equals("Zapatea"));
		assertTrue(uwu3.last.next.data.equals("Amanda"));
		assertTrue(uwu3.last.next.next.data.equals("Leticia"));
		
	}

}
