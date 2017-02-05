package net.aegistudio.aoe2m.slp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

import static net.aegistudio.aoe2m.TranslateWrapper.wrap;

public class Picture {
	public final Wrapper<String> version = new Container<>("2.0N");
	
	public final List<Frame> frames = new ArrayList<>();
	
	public final Wrapper<String> comment = Container.string0();
	
	@SuppressWarnings("unchecked")
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.constString(4, version);
		
		Wrapper<Integer> frameCount = new Container<>(frames.size());
		translator.signed32(frameCount);
		
		translator.constString(24, comment);
		translator.array(frameCount.getValue(), frames, Frame::new, 
				wrap(translator, Frame::translate));
	}
}
