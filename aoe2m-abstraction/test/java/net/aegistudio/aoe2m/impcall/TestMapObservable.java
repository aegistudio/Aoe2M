package net.aegistudio.aoe2m.impcall;

import java.util.TreeMap;
import java.util.function.BiConsumer;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.impcall.MapObservable;

public class TestMapObservable {
	private MapObservable<String, Long> observable;
	
	private String key1, key2;
	private long value1, value2;
	private int state = 0;
	
	private BiConsumer<String, Long> notObserving = (k, v) -> state ++;
	private BiConsumer<String, String> notObserving2 = (k, v) -> state ++;
	
	private BiConsumer<String, Long> observingK1V1 = (k, v) -> {
		assertEquals(k, key1);	assertEquals(v, value1);	state ++;
	};
	
	private BiConsumer<String, Long> observingK1V2 = (k, v) -> {
		assertEquals(k, key1);	assertEquals(v, value2);	state ++;
	};
	
	private BiConsumer<String, String> observingK1K2 = (k1, k2) -> {
		assertEquals(k1, key1);		assertEquals(k2, key2);
		assertNull(observable.map().get(key1));
		assertEquals(observable.map().get(key2), value1);	state ++;
	};
	
	public @Before void before() {
		state = 0;
		observable = new MapObservable<>(new TreeMap<>());
		key1 = "insertValue1";		value1 = System.currentTimeMillis();
		key2 = "insertValue2";		value2 = System.nanoTime();
	}
	
	public @Test void testInsert() {
		observable.addInsert(observingK1V1);
		observable.addRemove(notObserving);
		observable.addReplace(notObserving);
		observable.addRename(notObserving2);
		
		// Cause key1 not exists, put it and call K1V1.
		observable.put(key1, value1);
		assertEquals(state, 1);
	}
	
	public @Test void testReplace() {
		observable.put(key1, value1);
		observable.addInsert(notObserving);
		observable.addRemove(notObserving);
		observable.addReplace(observingK1V2);
		observable.addRename(notObserving2);
		assertEquals(state, 0);
		
		// Cause key1 exists, and value2 != value1.
		assertEquals(observable.put(key1, value2), value1);
		assertEquals(state, 1);
		
		// Cause key1 exists, and value2 == value2.
		assertEquals(observable.put(key1, value2), value2);
		assertEquals(state, 1);
	}
	
	public @Test void testRename() {
		observable.put(key1, value1);
		observable.addInsert(notObserving);
		observable.addRemove(notObserving);
		observable.addReplace(notObserving);
		observable.addRename(observingK1K2);
		
		try {
			observable.rename(key1, key2);
		}
		catch(Aoe2mException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertEquals(state, 1);
	}
	
	public @Test void testRemove() {
		observable.put(key1, value1);
		observable.addInsert(notObserving);
		observable.addRemove(observingK1V1);
		observable.addReplace(notObserving);
		observable.addRename(notObserving2);
		
		// Cause key2 not exists, no call counting.
		assertEquals(observable.remove(key2), null);
		assertEquals(state, 0);
		
		// Cause key1 exists, call K1V1 observer.
		assertEquals(observable.remove(key1), value1);
		assertEquals(state, 1);
		
		// Cause key1 removed, no more calling to K1V1.
		assertEquals(observable.remove(key1), null);
		assertEquals(state, 1);
	}
	
	public @Test void testFlow() {
		observable.addInsert(notObserving);
		observable.addRemove(notObserving);
		observable.addReplace(notObserving);
		observable.addRename(notObserving2);
		
		// Cause key1 not exits, now insert.
		assertEquals(observable.put(key1, value1), null);
		assertEquals(state, 1);
		
		// No replacing.
		assertEquals(observable.put(key1, value1), value1);
		assertEquals(state, 1);
		
		// Replacing.
		assertEquals(observable.put(key1, value2), value1);
		assertEquals(state, 2);
		
		// No renaming.
		try {
			observable.rename(key1, key1);
			assertTrue(false);
		}
		catch(Aoe2mException e) {
			assertEquals(state, 2);
		}
		
		// Renaming.
		try {
			observable.rename(key1, key2);
			assertEquals(state, 3);
		}
		catch(Aoe2mException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		// Remove.
		assertEquals(observable.remove(key1), null);
		assertEquals(state, 3);
		
		assertEquals(observable.remove(key2), value2);
		assertEquals(state, 4);
	}
}
