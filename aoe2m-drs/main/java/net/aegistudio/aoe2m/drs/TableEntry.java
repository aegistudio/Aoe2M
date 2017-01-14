package net.aegistudio.aoe2m.drs;

import java.io.IOException;

import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class TableEntry {
	public final Wrapper<Integer> id = new Wrapper<>(0);
	
	public final Wrapper<Long> offset = new Wrapper<>(0L);
	
	public final Wrapper<Long> length = new Wrapper<>(0L);
	
	public void translate(FieldTranslator translator) throws IOException {
		translator.signed32(id);
		translator.unsigned32(offset);
		translator.unsigned32(length);
	}
}
