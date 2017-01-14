package net.aegistudio.aoe2m.drs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

/**
 * The content table that holds different file.
 * 
 * As the table is assume to be sequential, we try to perform a binary search
 * if we fail to find an entry in a position, and report corruption if not 
 * sequential on read/write.
 * 
 * @author aegistudio
 *
 */

public class Table {
	public final Wrapper<String> format = new Wrapper<>("anib");
	
	public final Wrapper<Long> entryOffset = new Wrapper<>(0L);
	
	private final Wrapper<Integer> entryCount = new Wrapper<Integer>(0);
	public final List<TableEntry> entries = new ArrayList<>();
	
	public void translateHeader(FieldTranslator fieldTranslator) throws IOException {
		fieldTranslator.constString(4, format);
		fieldTranslator.unsigned32(entryOffset);
		
		entryCount.setValue(entries.size());
		fieldTranslator.signed32(entryCount);
	}
	
	public void validate() throws CorruptionException {
		// Ensure the files are in order.
		if(entries.size() == 0) return;
		int previous = entries.get(0).id.getValue();
		for(int i = 0; i < entries.size(); i ++) {
			int current = entries.get(i).id.getValue();
			if(previous >= current) throw new CorruptionException(previous, current);
			previous = current;
		}
	}
	
	public void translateBody(FieldTranslator fieldTranslator) throws IOException, CorruptionException {
		fieldTranslator.array(entryCount.getValue(), entries, 
				TableEntry::new, entry -> entry.translate(fieldTranslator));
		validate();
	}
	
	private TableEntry find(int topIndex, int bottomIndex, int value) {
		if(topIndex >= bottomIndex) {
			TableEntry onlyOne = entries.get(bottomIndex);
			return onlyOne.id.getValue() == value? onlyOne : null;
		}
		
		int mid = (topIndex + bottomIndex) >> 1;
		TableEntry midOne = entries.get(mid);
		
		if(midOne.id.getValue() == value) return midOne;
		
		return midOne.id.getValue() > value? 
				find(topIndex, mid - 1, value) : find(mid + 1, bottomIndex, value);
	}
	
	public TableEntry look(int id) {
		if(entries.isEmpty()) return null;
		int upperBound = entries.size() - 1;
		
		int first = entries.get(0).id.getValue();
		if(first > id) return null;
		
		int last = entries.get(upperBound).id.getValue();
		if(last < id) return null;
		
		int test = id - first;
		if(test < entries.size()) {
			TableEntry testing = entries.get(test);
			if(testing.id.getValue() == id)
				return testing;
		}
		return find(0, upperBound, id);
	}
}