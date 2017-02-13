package net.aegistudio.aoe2m.drs;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
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
	public final Wrapper<String> format = new Container<>("anib");
	
	public final Wrapper<Long> entryOffset = new Container<>(0L);
	
	private final Wrapper<Integer> entryCount = new Container<Integer>(0);
	
	public final SequentialList<TableEntry> entries = new SequentialList<>();
	
	public void translateHeader(Translator fieldTranslator) throws IOException {
		fieldTranslator.string(4, format);
		fieldTranslator.unsigned32(entryOffset);
		
		entryCount.setValue(entries.size());
		fieldTranslator.signed32(entryCount);
	}
	
	public void translateBody(Translator fieldTranslator) throws IOException, CorruptionException {
		entries.entranslate(fieldTranslator, entryCount.getValue(), 
				TableEntry::new, entry -> entry.translate(fieldTranslator));
	}
}