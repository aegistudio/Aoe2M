package net.aegistudio.aoe2m.empires2x1p1.terrain;

import java.io.IOException;

import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class BorderFrameData {
	public Wrapper<Short> frame = new Container<>((short)0);
	
	public Wrapper<Short> flagX = new Container<>((short)0);
	
	public Wrapper<Short> flagY = new Container<>((short)0);
	
	public void translate(Translator translator) throws IOException {
		translator.signed16(frame);
		translator.signed16(flagX);
		translator.signed16(flagY);
	}
}
