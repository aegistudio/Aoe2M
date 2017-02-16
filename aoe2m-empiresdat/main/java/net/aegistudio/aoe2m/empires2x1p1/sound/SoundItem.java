package net.aegistudio.aoe2m.empires2x1p1.sound;

import java.io.IOException;

import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class SoundItem {
	public Wrapper<String> filename = Container.string0();
	
	public Wrapper<Integer> resource = Container.int0();
	
	public Wrapper<Short> probability = Container.short0();
	
	public Wrapper<Short> civilization = Container.short0();
	
	public Wrapper<Short> player = Container.short0();
	
	public void translate(Translator translator) throws IOException {
		translator.string(13, filename);
		translator.signed32(resource);
		translator.signed16(probability);
		translator.signed16(civilization);
		translator.signed16(player);
	}
}
