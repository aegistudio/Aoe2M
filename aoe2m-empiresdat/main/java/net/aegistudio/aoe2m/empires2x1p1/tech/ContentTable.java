package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class ContentTable {
	public final List<Wrapper<Integer>> entries = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator)
			throws IOException, CorruptException{
		
		Wrapper<Byte> entryCount = new Container<>((byte)entries.size());
		translator.signed8(entryCount);
		translator.array(entryCount.get(), 
				entries, Container::int0, 
				Translator.squeech(translator::signed32));
	}
}
