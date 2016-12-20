package net.aegistudio.aoe2m.impcall;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiConsumer;

import org.junit.Before;
import org.junit.Test;

public class TestCollectionObservable {
	private int state;
	private BiConsumer<Integer, String> reactor = (i, s) -> state ++;
	
	public @Before void before() {
		state = 0;
	}
	
	public @Test void testFlow() {
		ListObserveable<String> observable 
			= new ListObserveable<>(new ArrayList<>());
		observable.addInsert(reactor);
		
		assertEquals(state, 0);
		
		// Succ
		assertTrue(observable.add("str1"));
		assertEquals(state, 1);
		
		// Succ
		assertTrue(observable.add("str2"));
		assertEquals(state, 2);
		
		// Fail
		assertFalse(observable.insert(-1, "str3"));
		assertEquals(state, 2);
		
		// Fail
		assertFalse(observable.insert(2, "str4"));
		assertEquals(state, 2);
		
		// Succ
		assertTrue(observable.insert(0, "str5"));
		assertEquals(state, 3);
		
		// Succ
		assertEquals(observable.remove(1), "str1");
		assertEquals(state, 3);	// no listen.
		
		assertTrue(Arrays.deepEquals(
				new Object[] {"str5", "str2"}, 
				observable.list().toArray()));
	}
}
