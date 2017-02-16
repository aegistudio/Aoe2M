package net.aegistudio.aoe2m.drs;

import java.io.IOException;

import net.aegistudio.aoe2m.IntegerIndexed;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class TableEntry implements IntegerIndexed {
	public final Wrapper<Integer> id = Container.int0();
	
	public final Wrapper<Long> offset = Container.long0();
	
	public final Wrapper<Long> length = Container.long0();
	
	public void translate(Translator translator) throws IOException {
		translator.signed32(id);
		translator.unsigned32(offset);
		translator.unsigned32(length);
	}

	@Override
	public int keyword() {
		return id.get();
	}
}
