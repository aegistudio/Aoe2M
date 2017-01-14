package net.aegistudio.aoe2m.drs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

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

public class Archive {
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
	
	public final Wrapper<String> signature = new Wrapper<>(trademark);
	
	public final Wrapper<String> version = new Wrapper<>("1.00");
	
	public final Wrapper<String> type = new Wrapper<>(tribe);
	
	public final List<Table> tableList = new ArrayList<>();
	
	public final Wrapper<Long> fileSectionOffset = new Wrapper<>(1024L);
	
	public void translate(FieldTranslator translator) throws CorruptionException, IOException {
		translator.constString(40, signature);
		translator.constString(4, version);
		translator.constString(12, type);
		
		Wrapper<Integer> tableListLength = new Wrapper<>(tableList.size());
		translator.signed32(tableListLength);
		
		translator.unsigned32(fileSectionOffset);
		
		translator.array(tableListLength.getValue(), tableList, 
				Table::new, table -> table.translateHeader(translator));
	}
}
