package net.aegistudio.aoe2m.empires2x1p1.unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;
import static net.aegistudio.aoe2m.TranslateWrapper.wrap;

public class Civilization {
	public final Wrapper<Byte> enabled = Container.byte0();
	
	public final Wrapper<String> name = Container.string0();
	
	public final List<Wrapper<Float>> resources = new ArrayList<>();
	
	public final Wrapper<Short> techTree = Container.short0();
	
	public final Wrapper<Short> teamBonus = Container.short0();
	
	public final Wrapper<Byte> iconSet = Container.byte0();
	
	public final List<Wrapper<Integer>> unitOffsets = new ArrayList<>();
	
	public final List<Unit> units = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.signed8(enabled);
		translator.constString(20, name);
		
		Wrapper<Integer> resourcesCount = new Container<>(resources.size());
		translator.unsigned16(resourcesCount);
		
		translator.signed16(techTree);
		translator.signed16(teamBonus);
		
		translator.array(resourcesCount.getValue(), resources, 
				Container::float0, translator::float32);
		
		translator.signed8(iconSet);
		
		Wrapper<Integer> unitOffsetsCount = new Container<>(unitOffsets.size());
		translator.unsigned16(unitOffsetsCount);
		translator.array(unitOffsetsCount.getValue(), unitOffsets, 
				Container::int0, translator::signed32);
		
		int actualCount = (int)unitOffsets.stream()
				.mapToInt(Wrapper::getValue).filter(i -> i != 0).count();
		translator.array(actualCount, units, Unit::new, 
				wrap(translator, Unit::translate));
	}
}
