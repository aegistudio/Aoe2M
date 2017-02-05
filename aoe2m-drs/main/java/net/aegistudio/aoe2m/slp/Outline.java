package net.aegistudio.aoe2m.slp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

import static net.aegistudio.aoe2m.TranslateWrapper.wrap;

public class Outline {
	public static class Padding {
		public final Wrapper<Short> left = Container.short0();
		
		public final Wrapper<Short> right = Container.short0();
		
		public void translate(FieldTranslator translator) throws IOException {
			translator.signed16(left);
			translator.signed16(right);
		}
	}
	
	public final List<Padding> padding = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(int height, FieldTranslator translator) 
			throws IOException, CorruptionException {
		
		translator.array(height, padding, Padding::new, 
				wrap(translator, Padding::translate));
	}
}
