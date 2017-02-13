package net.aegistudio.aoe2m.drs;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.IntegerIndexed;
import net.aegistudio.aoe2m.Wrapper;

public class TableEntry implements IntegerIndexed {
	public final Wrapper<Integer> id = new Container<>(0);
	
	public final Wrapper<Long> offset = new Container<>(0L);
	
	public final Wrapper<Long> length = new Container<>(0L);
	
	public void translate(Translator translator) throws IOException {
		translator.signed32(id);
		translator.unsigned32(offset);
		translator.unsigned32(length);
	}

	@Override
	public int keyword() {
		return id.getValue();
	}
}
