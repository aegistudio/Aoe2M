package net.aegistudio.aoe2m.empires2x1p1.restriction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class RestrictionItem {
	public final List<Wrapper<Float>> accessible = new ArrayList<>();
	public final List<RestrictionSprites> sprites = new ArrayList<>();
	
	public void translateItem(int terrain, FieldTranslator translator) throws IOException, CorruptionException {
		translator.array(terrain, accessible, 
				Container::float0, translator::float32);
		
		translator.array(terrain, sprites, 
				RestrictionSprites::new, 
				item -> item.translate(translator));
	}
	
	public Wrapper<Float> accessible(int terrain) {
		return accessible.get(terrain);
	}
	
	public RestrictionSprites sprites(int terrain) {
		return sprites.get(terrain);
	}
}
