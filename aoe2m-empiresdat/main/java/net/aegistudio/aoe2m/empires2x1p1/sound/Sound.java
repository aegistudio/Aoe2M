package net.aegistudio.aoe2m.empires2x1p1.sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class Sound {
	public Wrapper<Integer> id = new Container<>(0);
	
	public Wrapper<Short> playAtUpdate = new Container<>((short)0);
	
	public Wrapper<Integer> cacheTime = new Container<>(0);
	
	public List<SoundItem> items = new ArrayList<>();
	
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.signed32(id);
		//translator.signed16(playAtUpdate);	// WTF?
		Wrapper<Integer> itemsLength = new Container<>(items.size());
		translator.unsigned16(itemsLength);
		translator.signed32(cacheTime);
		
		translator.array(itemsLength.getValue(), items, SoundItem::new, 
				item -> item.translate(translator));
	}
}
