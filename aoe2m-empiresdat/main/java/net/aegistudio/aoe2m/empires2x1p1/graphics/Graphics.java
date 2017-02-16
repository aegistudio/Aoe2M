package net.aegistudio.aoe2m.empires2x1p1.graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class Graphics {
	public final List<Wrapper<Integer>> offsets = new ArrayList<>();
	public final List<GraphicsItem> items = new ArrayList<>();
	public final Wrapper<String> renderingData = Container.string0();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		Wrapper<Integer> graphicsCount = new Container<>(offsets.size());
		translator.unsigned16(graphicsCount);
		
		translator.array(graphicsCount.get(), offsets, 
				Container::int0, Translator.squeech(translator::signed32));
		
		long actualLength = offsets.stream()
				.map(Wrapper::get)
				.filter(i -> i != 0).count();
		
		translator.array((int)actualLength, items, 
				GraphicsItem::new, GraphicsItem::translate);
		
		// WTF? Version issue?
		//translator.constString(138, renderingData);
	}
}
