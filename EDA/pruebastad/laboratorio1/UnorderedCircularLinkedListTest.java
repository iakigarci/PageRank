package laboratorio1;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnorderedCircularLinkedListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddToFront() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		assertEquals(uwu.count,1);
		assertTrue(uwu.last.data.equals(1));
		
		uwu.addToFront(2);
		assertEquals(uwu.count,2);
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(2));
		
		uwu.addToFront(3);
		assertEquals(uwu.count,3);
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(3));
		assertTrue(uwu.last.next.next.data.equals(2));

	}

	@Test
	public void testAddToRear() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		
		uwu.addToRear(1);
		assertEquals(uwu.count,1);
		assertTrue(uwu.last.data.equals(1));
		
		uwu.addToRear(2);
		assertEquals(uwu.count,2);
		assertTrue(uwu.last.data.equals(2));
		assertTrue(uwu.last.next.data.equals(1));
		
		uwu.addToRear(3);
		assertEquals(uwu.count,3);
		assertTrue(uwu.last.data.equals(3));
		assertTrue(uwu.last.next.data.equals(2));
		assertTrue(uwu.last.next.next.data.equals(1));

	}

	@Test
	public void testAddAfter() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		
		uwu.addToRear(1);
		
		uwu.addAfter(2, 1);
		assertEquals(uwu.count, 2);
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(2));
		
		uwu.addAfter(3, 1);
		assertEquals(uwu.count,3);
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(3));
		assertTrue(uwu.last.next.next.data.equals(2));
		
		uwu.addAfter(3, 88);
		assertEquals(uwu.count,3);
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(3));
		assertTrue(uwu.last.next.next.data.equals(2));
	}

	@Test
	public void testRemoveFirst() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		UnorderedCircularLinkedList<Integer> uwu2 = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		uwu2.addToFront(1);
		
		uwu2.removeFirst();
		assertEquals(uwu2.count,0);
		
		uwu.removeFirst();
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(2));
		assertEquals(uwu.count,2);
		
	}

	@Test
	public void testRemoveLast() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		UnorderedCircularLinkedList<Integer> uwu2 = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		uwu2.addToFront(1);
		
		uwu2.removeLast();
		assertEquals(uwu2.count,0);
		
		uwu.removeLast();
		assertTrue(uwu.last.data.equals(2));
		assertTrue(uwu.last.next.data.equals(3));
		assertEquals(uwu.count,2);
	}

	@Test
	public void testRemove() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		UnorderedCircularLinkedList<Integer> uwu2 = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		uwu2.addToFront(1);
		uwu2.addToFront(2);
		uwu2.addToFront(3);
		
		uwu.remove(2);
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(3));
		assertTrue(uwu.last.next.next.data.equals(1));
		assertEquals(uwu.count,2);
		
		uwu.remove(3);
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(1));
		assertTrue(uwu.last.next.next.data.equals(1));
		assertEquals(uwu.count,1);
		
		uwu.remove(1);
		assertTrue(uwu.last.data.equals(1));
		assertTrue(uwu.last.next.data.equals(1));
		assertTrue(uwu.last.next.next.data.equals(1));
		assertEquals(uwu.count,0);
		
		uwu2.remove(1);
		assertTrue(uwu2.last.data.equals(2));
		assertTrue(uwu2.last.next.data.equals(3));
		assertTrue(uwu2.last.next.next.data.equals(2));
		assertEquals(uwu2.count,2);

		
		
	}

	@Test
	public void testFirst() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		assertTrue(uwu.first().equals(3));
	}

	@Test
	public void testLast() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		assertTrue(uwu.last().equals(1));
	}

	@Test
	public void testContains() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		assertTrue(uwu.contains(1));
		assertTrue(uwu.contains(2));
		assertTrue(uwu.contains(3));
	}

	@Test
	public void testFind() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		assertTrue(uwu.find(1).equals(1));
		assertTrue(uwu.find(2).equals(2));
		assertTrue(uwu.find(3).equals(3));
		assertNull(uwu.find(4));
	}

	@Test
	public void testIsEmpty() {
		
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		UnorderedCircularLinkedList<Integer> uwu2 = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		assertFalse(uwu.isEmpty());
		assertTrue(uwu2.isEmpty());
		
	}

	@Test
	public void testSize() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		UnorderedCircularLinkedList<Integer> uwu2 = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		assertEquals(uwu.size(),3);
		assertEquals(uwu2.size(),0);
		
	
	}

	@Test
	public void testIterator() {
		UnorderedCircularLinkedList<Integer> uwu = new UnorderedCircularLinkedList<>();
		UnorderedCircularLinkedList<Integer> uwu2 = new UnorderedCircularLinkedList<>();
		
		uwu.addToFront(1);
		uwu.addToFront(2);
		uwu.addToFront(3);
		
		Iterator<Integer> iterador = uwu.iterator();
		Iterator<Integer> iterador2 = uwu2.iterator();
		
		assertTrue(iterador.hasNext());
		assertFalse(iterador2.hasNext());
		
		assertTrue(iterador.next() == 3);
		assertTrue(iterador.next().equals(2));
		assertTrue(iterador.next().equals(1));
	
		
	}

}
