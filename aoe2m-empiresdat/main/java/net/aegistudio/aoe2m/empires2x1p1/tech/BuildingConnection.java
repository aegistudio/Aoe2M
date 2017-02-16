package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class BuildingConnection {
	public final Wrapper<Integer> id = Container.int0();
	
	public final Wrapper<Byte> status = Container.byte0();
	
	public final ContentTable buildings = new ContentTable();
	
	public final ContentTable units = new ContentTable();
	
	public final ContentTable researches = new ContentTable();
	
	public final Wrapper<Integer> age = Container.int0();
	
	public final Wrapper<Integer> unitOrResearch0 = Container.int0();
	
	public final Wrapper<Integer> unitOrResearch1 = Container.int0();
	
	public final List<Wrapper<Integer>> uk0 = new ArrayList<>();
	
	public final Wrapper<Integer> mode0 = Container.int0();	
	
	public final Wrapper<Integer> mode1 = Container.int0();	

	public final List<Wrapper<Integer>> uk1 = new ArrayList<>();
	
	public final List<Wrapper<Byte>> uk2 = new ArrayList<>();
	
	public final Wrapper<Integer> lineMode = Container.int0();		
	
	public final Wrapper<Integer> requiredResearch = Container.int0();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.signed32(id);
		translator.signed8(status);
		buildings.translate(translator);
		units.translate(translator);
		researches.translate(translator);
		translator.signed32(age);
		translator.signed32(unitOrResearch0);
		translator.signed32(unitOrResearch1);
		
		translator.array(8, uk0, Container::int0, 
				Translator.reverse(Translator::signed32));
		translator.signed32(mode0);
		translator.signed32(mode1);
		translator.array(8, uk1, Container::int0, 
				Translator.reverse(Translator::signed32));
		translator.array(11, uk2, Container::byte0, 
				Translator.reverse(Translator::signed8));
		
		translator.signed32(lineMode);
		translator.signed32(requiredResearch);
	}
}
