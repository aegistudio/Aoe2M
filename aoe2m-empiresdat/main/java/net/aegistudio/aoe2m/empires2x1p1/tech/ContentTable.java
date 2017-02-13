package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class ContentTable {
	public final List<Wrapper<Integer>> entries = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator)
			throws IOException, CorruptionException{
		
		Wrapper<Byte> entryCount = new Container<>((byte)entries.size());
		translator.signed8(entryCount);
		translator.array(entryCount.getValue(), 
				entries, Container::int0, translator::signed32);
	}
}
