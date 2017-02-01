package net.aegistudio.aoe2m.empires2x1p1.sound;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class SoundItem {
	public Wrapper<String> filename = new Container<>("");
	
	public Wrapper<Integer> resource = new Container<>(0);
	
	public Wrapper<Short> probability = new Container<>((short)0);
	
	public Wrapper<Short> civilization = new Container<>((short)0);
	
	public Wrapper<Short> player = new Container<>((short)0);
	
	public void translate(FieldTranslator translator) throws IOException {
		translator.constString(13, filename);
		translator.signed32(resource);
		translator.signed16(probability);
		translator.signed16(civilization);
		translator.signed16(player);
	}
}
