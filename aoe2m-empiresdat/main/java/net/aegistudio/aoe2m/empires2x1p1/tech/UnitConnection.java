package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class UnitConnection {
	public final Wrapper<Integer> id = Container.int0();
	
	public final Wrapper<Byte> status = Container.byte0();
	
	public final Wrapper<Integer> upperBuilding = Container.int0();
	
	public final Wrapper<Integer> requiredResearches = Container.int0();
	
	public final Wrapper<Integer> age = Container.int0();
	
	public final Wrapper<Integer> unitOrResearch0 = Container.int0();
	
	public final Wrapper<Integer> unitOrResearch1 = Container.int0();

	public final List<Wrapper<Integer>> uk0 = new ArrayList<>();

	public final Wrapper<Integer> mode0 = Container.int0();	
	
	public final Wrapper<Integer> mode1 = Container.int0();	
	
	public final List<Wrapper<Integer>> uk1 = new ArrayList<>();
	
	public final Wrapper<Integer> verticalLines = Container.int0();		
	
	public final ContentTable units = new ContentTable();
	
	public final Wrapper<Integer> locationInAge = Container.int0();
	
	public final Wrapper<Integer> requiredResearch = Container.int0();
	
	public final Wrapper<Integer> lineMode = Container.int0();
	
	public final Wrapper<Integer> enablingResearch = Container.int0();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptionException {
		translator.signed32(id);
		translator.signed8(status);
		translator.signed32(upperBuilding);
		translator.signed32(requiredResearches);
		translator.signed32(age);
		translator.signed32(unitOrResearch0);
		translator.signed32(unitOrResearch1);
		
		translator.array(8, uk0, Container::int0, translator::signed32);
		translator.signed32(mode0);
		translator.signed32(mode1);
		translator.array(7, uk0, Container::int0, translator::signed32);
		
		translator.signed32(verticalLines);
		units.translate(translator);
		
		translator.signed32(locationInAge);
		translator.signed32(requiredResearch);
		translator.signed32(lineMode);
		translator.signed32(enablingResearch);
	}
}
