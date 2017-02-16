package net.aegistudio.aoe2m.slp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class Picture {
	public final Wrapper<String> version = new Container<>("2.0N");
	
	public final List<Frame> frames = new ArrayList<>();
	
	public final Wrapper<String> comment = Container.string0();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.string(4, version);
		
		Wrapper<Integer> frameCount = new Container<>(frames.size());
		translator.signed32(frameCount);
		
		translator.string(24, comment);
		translator.array(frameCount.get(), frames, Frame::new, 
				Frame::translate);
	}
}
