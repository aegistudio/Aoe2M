package net.aegistudio.aoe2m.empires2x1p1.graphics;

import java.io.IOException;

import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class GraphicsAttackSound {
	public Wrapper<Short> soundDelay0 = new Container<>((short)0);
	
	public Wrapper<Short> soundId0 = new Container<>((short)0);
	
	public Wrapper<Short> soundDelay1 = new Container<>((short)0);
	
	public Wrapper<Short> soundId1 = new Container<>((short)0);
	
	public Wrapper<Short> soundDelay2 = new Container<>((short)0);
	
	public Wrapper<Short> soundId2 = new Container<>((short)0);
	
	public void translate(Translator translator) throws IOException {
		translator.signed16(soundDelay0);
		translator.signed16(soundId0);
		translator.signed16(soundDelay1);
		translator.signed16(soundId1);
		translator.signed16(soundDelay2);
		translator.signed16(soundId2);
	}
}
