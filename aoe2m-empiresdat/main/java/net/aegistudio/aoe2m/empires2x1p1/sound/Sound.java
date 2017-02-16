package net.aegistudio.aoe2m.empires2x1p1.sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class Sound {
	public Wrapper<Integer> id = Container.int0();
	
	public Wrapper<Short> playAtUpdate = Container.short0();
	
	public Wrapper<Integer> cacheTime = Container.int0();
	
	public List<SoundItem> items = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.signed32(id);
		//translator.signed16(playAtUpdate);	// WTF?
		Wrapper<Integer> itemsLength = new Container<>(items.size());
		translator.unsigned16(itemsLength);
		translator.signed32(cacheTime);
		
		translator.array(itemsLength.get(), items, SoundItem::new, 
				SoundItem::translate);
	}
}
