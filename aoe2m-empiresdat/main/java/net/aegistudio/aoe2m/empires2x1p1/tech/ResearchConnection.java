package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class ResearchConnection {
	public final Wrapper<Integer> id = Container.int0();
	
	public final Wrapper<Byte> status = Container.byte0();

	public final Wrapper<Integer> upperBuilding = Container.int0();
	
	public final ContentTable buildings = new ContentTable();
	
	public final ContentTable units = new ContentTable();
	
	public final ContentTable researches = new ContentTable();
	
	public final Wrapper<Integer> requiredResearch = Container.int0();
	
	public final Wrapper<Integer> age = Container.int0();
	
	public final Wrapper<Integer> upperResearch = Container.int0();

	public final List<Wrapper<Integer>> uk0 = new ArrayList<>();

	public final Wrapper<Integer> lineMode = Container.int0();	
	
	public final List<Wrapper<Integer>> uk1 = new ArrayList<>();
	
	public final Wrapper<Integer> verticalLines = Container.int0();		
	
	public final Wrapper<Integer> locationInAge = Container.int0();
	
	public final Wrapper<Integer> uk2 = Container.int0();
	
	@SuppressWarnings("unchecked")
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.signed32(id);
		translator.signed8(status);
		translator.signed32(upperBuilding);
		buildings.translate(translator);
		units.translate(translator);
		researches.translate(translator);
		
		translator.signed32(requiredResearch);
		translator.signed32(age);
		translator.signed32(upperResearch);
		
		translator.array(9, uk0, Container::int0, translator::signed32);
		translator.signed32(lineMode);
		translator.array(8, uk1, Container::int0, translator::signed32);
		
		translator.signed32(verticalLines);
		translator.signed32(locationInAge);
		translator.signed32(uk2);
	}
}
