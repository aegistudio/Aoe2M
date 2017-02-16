package net.aegistudio.aoe2m.slp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class Outline {
	public static class Padding {
		public final Wrapper<Short> left = Container.short0();
		
		public final Wrapper<Short> right = Container.short0();
		
		public void translate(Translator translator) throws IOException {
			translator.signed16(left);
			translator.signed16(right);
		}
	}
	
	public final List<Padding> padding = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(int height, Translator translator) 
			throws IOException, CorruptException {
		
		translator.array(height, padding, Padding::new, 
				Padding::translate);
	}
}
