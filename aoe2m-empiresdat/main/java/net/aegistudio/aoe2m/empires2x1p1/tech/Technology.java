package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class Technology {
	public final Wrapper<String> name = Container.string0();
	
	public static class Effect {
		public Wrapper<Byte> type = Container.byte0();
		
		public Wrapper<Short> unit = Container.short0();
		
		public Wrapper<Short> unitClass = Container.short0();
		
		public Wrapper<Short> attribute = Container.short0();
		
		public Wrapper<Float> delta = Container.float0();
		
		public void translate(Translator translator) throws IOException {
			translator.signed8(type);
			translator.signed16(unit);
			translator.signed16(unitClass);
			translator.signed16(attribute);
			translator.float32(delta);
		}
	}
	
	public final List<Effect> effects = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.string(31, name);
		
		Wrapper<Integer> effectCount = new Container<>(effects.size());
		translator.unsigned16(effectCount);
		translator.array(effectCount.get(), effects, 
				Effect::new, Effect::translate);
	}
}
