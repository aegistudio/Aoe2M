package net.aegistudio.aoe2m.drs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.aegistudio.aoe2m.SequentialList;

/**
 * As there're binary search algorithm there,
 * we will need to implement a test case.
 * 
 * @author aegistudio
 */

public class TestTableSearch {
	public List<TableEntry> atom;
	public SequentialList<TableEntry> table;
	public @Before void before() {	atom = new ArrayList<>();	}
	public void finish() {	table = new SequentialList<>(atom);		}
	
	public void add(int id) {
		TableEntry entry = new TableEntry();
		entry.id.setValue(id);
		atom.add(entry);
	}
	
	public @Test void testNone() {
		finish();
		assertNull(table.find(0));
	}
	
	public @Test void testOne() {
		add(1234);	finish();
		assertNotNull(table.find(1234));
		assertNull(table.find(1235));
	}
	
	public @Test void testTwo() {
		add(1234);	add(5678);	finish();
		assertNotNull(table.find(1234));
		assertNotNull(table.find(5678));
		assertNull(table.find(9012));
	}
	
	public @Test void testThree() {
		add(1234);	add(3456); add(5678);
		finish();
		
		assertNotNull(table.find(1234));
		assertNotNull(table.find(3456));
		assertNotNull(table.find(5678));
		assertNull(table.find(9012));
	}
	
	public @Test void testMany() {
		for(int i = 0; i < 1000; i ++)
			add(1234 + i);
		finish();
		
		assertNull(table.find(0));
		assertNotNull(table.find(1234));
		assertNotNull(table.find(1434));
		assertNull(table.find(9012));
	}
}
