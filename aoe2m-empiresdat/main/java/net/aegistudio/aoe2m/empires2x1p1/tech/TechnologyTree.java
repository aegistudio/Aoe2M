package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class TechnologyTree {
	public final Wrapper<Integer> uk0 = Container.int0();
	
	public final Wrapper<Integer> id = Container.int0();
	
	public final Wrapper<Byte> uk1 = Container.byte0();
	
	public final ContentTable buildings = new ContentTable();
	
	public final ContentTable units = new ContentTable();
	
	public final ContentTable researches = new ContentTable();
	
	public final Wrapper<Integer> uk2 = Container.int0();
	
	public final Wrapper<Integer> secondAge = Container.int0();
	
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.signed32(uk0);
		translator.signed32(id);
		translator.signed8(uk1);
		
		buildings.translate(translator);
		units.translate(translator);
		researches.translate(translator);
		
		translator.signed32(uk2);
		translator.signed32(secondAge);
		translator.skip(98);
	}
}
