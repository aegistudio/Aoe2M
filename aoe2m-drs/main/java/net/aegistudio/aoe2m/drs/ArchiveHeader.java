package net.aegistudio.aoe2m.drs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

/**
 * A .drs archive is a composite of data number 
 * indexed files. 
 * 
 * Please notice we require a random access file
 * in order to perform read write operation, the
 * archive itself only manipulate the header and
 * table header(s).
 * 
 * @author aegistudio
 *
 */

public class ArchiveHeader {
	public static final String trademark, tribe; static {
		byte[] trademarkString = new byte[40];
		byte[] asciiPart = "Copyright (c) 1997 Ensemble Studios.".getBytes();
		System.arraycopy(asciiPart, 0, trademarkString, 0, asciiPart.length);
		trademarkString[asciiPart.length] = 0x1a;
		trademark = new String(trademarkString);
		
		byte[] tribeString = new byte[12];
		System.arraycopy("tribe".getBytes(), 0, 
				tribeString, 0, "tribe".getBytes().length);
		tribe = new String(tribeString);
	}
	
	public final Wrapper<String> signature = new Container<>(trademark);
	
	public final Wrapper<String> version = new Container<>("1.00");
	
	public final Wrapper<String> type = new Container<>(tribe);
	
	public final List<TableHeader> tableList = new ArrayList<>();
	
	public final Wrapper<Long> fileSectionOffset = new Container<>(1024L);
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws CorruptException, IOException {
		translator.string(40, signature);
		translator.string(4, version);
		translator.string(12, type);
		
		Wrapper<Integer> tableListLength = new Container<>(tableList.size());
		translator.signed32(tableListLength);
		
		translator.unsigned32(fileSectionOffset);
		
		translator.array(tableListLength.get(), tableList, 
				TableHeader::new, TableHeader::translateHeader);
	}
}
