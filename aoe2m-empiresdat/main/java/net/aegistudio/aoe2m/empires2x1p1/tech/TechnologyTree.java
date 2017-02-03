package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;
import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class TechnologyTree {
	public final Wrapper<Integer> uk0 = Container.int0();
	
	public final Wrapper<Integer> id = Container.int0();
	
	public final Wrapper<Byte> uk1 = Container.byte0();
	
	public final ContentTable buildings = new ContentTable();
	
	public final ContentTable units = new ContentTable();
	
	public final ContentTable researches = new ContentTable();
	
	public final Wrapper<Integer> uk2 = Container.int0();
	
	public final Wrapper<Integer> secondAge = Container.int0();
	
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
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
