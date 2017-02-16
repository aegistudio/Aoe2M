package net.aegistudio.aoe2m.drs;

import java.io.IOException;

import net.aegistudio.aoe2m.SequentialList;
import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

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
	
	public final Wrapper<Long> entryOffset = Container.long0();
	
	private final Wrapper<Integer> entryCount = Container.int0();
	
	public final SequentialList<TableEntry> entries = new SequentialList<>();
	
	public void translateHeader(Translator fieldTranslator) throws IOException {
		fieldTranslator.string(4, format);
		fieldTranslator.unsigned32(entryOffset);
		
		entryCount.set(entries.size());
		fieldTranslator.signed32(entryCount);
	}
	
	public void translateBody(Translator fieldTranslator) throws IOException, CorruptException {
		entries.entranslate(fieldTranslator, entryCount.get(), 
				TableEntry::new, TableEntry::translate);
	}
}