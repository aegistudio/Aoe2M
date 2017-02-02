package net.aegistudio.aoe2m.empires2x1p1.graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

import static net.aegistudio.aoe2m.TranslateWrapper.wrap;

public class Graphics {
	public final List<Wrapper<Integer>> offsets = new ArrayList<>();
	public final List<GraphicsItem> items = new ArrayList<>();
	public final Wrapper<String> renderingData = new Container<String>("");
	
	@SuppressWarnings("unchecked")
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		Wrapper<Integer> graphicsCount = new Container<>(offsets.size());
		translator.unsigned16(graphicsCount);
		
		translator.array(graphicsCount.getValue(), offsets, 
				Container::int0, translator::signed32);
		
		long actualLength = offsets.stream()
				.map(Wrapper::getValue)
				.filter(i -> i != 0).count();
		
		translator.array((int)actualLength, items, 
				GraphicsItem::new, wrap(translator, GraphicsItem::translate));
		
		// WTF? Version issue?
		//translator.constString(138, renderingData);
	}
}
