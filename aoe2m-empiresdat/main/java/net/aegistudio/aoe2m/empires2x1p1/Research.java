package net.aegistudio.aoe2m.empires2x1p1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

import static net.aegistudio.aoe2m.TranslateWrapper.wrap;

public class Research {
	public final List<Wrapper<Short>> requiredTech = new ArrayList<Wrapper<Short>>();
	
	public final List<Resource> researchCost = new ArrayList<>();
	
	public final Wrapper<Short> minRequired = Container.short0();
	
	public final Wrapper<Short> civilSpecific = Container.short0();
	
	public final Wrapper<Short> fullTechMode = Container.short0();
	
	public final Wrapper<Short> researchLocation = Container.short0();
	
	public final Wrapper<Short> dllName = Container.short0();
	
	public final Wrapper<Short> dllDescription = Container.short0();
	
	public final Wrapper<Short> researchTime = Container.short0();	
	
	public final Wrapper<Short> techEffect = Container.short0();	

	public final Wrapper<Short> techType = Container.short0();	
	
	public final Wrapper<Short> icon = Container.short0();
	
	public final Wrapper<Byte> button = Container.byte0();
	
	public final Wrapper<Integer> dllHelp = Container.int0();
	
	public final Wrapper<Integer> dllTechTree = Container.int0();

	public final Wrapper<Integer> uk0 = Container.int0();
	
	public final Wrapper<String> name = Container.string0();
	
	@SuppressWarnings("unchecked")
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.array(6, requiredTech, Container::short1m, 
				translator::signed16);
		requiredTech.removeIf(value -> value.getValue() <= 0);
		
		translator.array(3, researchCost, Resource::new, 
				wrap(translator, Resource::translateResearch));
		
		translator.signed16(minRequired);
		translator.signed16(civilSpecific);
		translator.signed16(fullTechMode);
		translator.signed16(researchLocation);
		
		translator.signed16(dllName);
		translator.signed16(dllDescription);
		
		translator.signed16(researchTime);
		translator.signed16(techEffect);
		translator.signed16(techType);
		
		translator.signed16(icon);
		translator.signed8(button);
		
		translator.signed32(dllHelp);
		translator.signed32(dllTechTree);
		
		translator.signed32(uk0);
		
		Wrapper<Integer> nameLength = new Container<>(
				name.getValue().getBytes().length);
		translator.unsigned16(nameLength);
		translator.constString(nameLength.getValue(), name);
	}
}
