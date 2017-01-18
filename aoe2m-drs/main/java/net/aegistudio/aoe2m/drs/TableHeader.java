package net.aegistudio.aoe2m.drs;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.SequentialList;
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

public class TableHeader {
	public final Wrapper<String> format = new Wrapper<>("anib");
	
	public final Wrapper<Long> entryOffset = new Wrapper<>(0L);
	
	private final Wrapper<Integer> entryCount = new Wrapper<Integer>(0);
	
	public final SequentialList<TableEntry> entries = new SequentialList<>();
	
	public void translateHeader(FieldTranslator fieldTranslator) throws IOException {
		fieldTranslator.constString(4, format);
		fieldTranslator.unsigned32(entryOffset);
		
		entryCount.setValue(entries.size());
		fieldTranslator.signed32(entryCount);
	}
	
	public void translateBody(FieldTranslator fieldTranslator) throws IOException, CorruptionException {
		entries.entranslate(fieldTranslator, entryCount.getValue(), 
				TableEntry::new, entry -> entry.translate(fieldTranslator));
	}
}