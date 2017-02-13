package net.aegistudio.aoe2m.empires2x1p1.tech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import static net.aegistudio.aoe2m.TranslateWrapper.*;
import net.aegistudio.aoe2m.Wrapper;

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
	public void translate(Translator translator) throws IOException, CorruptionException {
		translator.string(31, name);
		
		Wrapper<Integer> effectCount = new Container<>(effects.size());
		translator.unsigned16(effectCount);
		translator.array(effectCount.getValue(), effects, Effect::new, 
				wrap(translator, Effect::translate));
	}
}
