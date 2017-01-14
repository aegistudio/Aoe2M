package net.aegistudio.aoe2m.drs;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import net.aegistudio.aoe2m.drs.Table;
import net.aegistudio.aoe2m.drs.TableEntry;

/**
 * As there're binary search algorithm there,
 * we will need to implement a test case.
 * 
 * @author aegistudio
 */

public class TestTableSearch {
	public Table table;
	public @Before void before() {
		table = new Table();
	}
	
	public void add(int id) {
		TableEntry entry = new TableEntry();
		entry.id.setValue(id);
		table.entries.add(entry);
	}
	
	public @Test void testNone() {
		assertNull(table.look(0));
	}
	
	public @Test void testOne() {
		add(1234);
		assertNotNull(table.look(1234));
		assertNull(table.look(1235));
	}
	
	public @Test void testTwo() {
		add(1234);	add(5678);
		assertNotNull(table.look(1234));
		assertNotNull(table.look(5678));
		assertNull(table.look(9012));
	}
	
	public @Test void testThree() {
		add(1234);	add(3456); add(5678);
		assertNotNull(table.look(1234));
		assertNotNull(table.look(3456));
		assertNotNull(table.look(5678));
		assertNull(table.look(9012));
	}
	
	public @Test void testMany() {
		for(int i = 0; i < 1000; i ++)
			add(1234 + i);
		assertNull(table.look(0));
		assertNotNull(table.look(1234));
		assertNotNull(table.look(1434));
		assertNull(table.look(9012));
	}
}
