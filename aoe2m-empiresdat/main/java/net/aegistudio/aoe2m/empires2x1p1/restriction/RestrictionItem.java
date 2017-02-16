package net.aegistudio.aoe2m.empires2x1p1.restriction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Translator.ArrayTranslation;

public class RestrictionItem {
	public final List<RestrictionCell> sprites = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translateItem(int terrain, Translator translator) throws IOException, CorruptException {
		translator.array(terrain, sprites, 
				RestrictionCell::new,
				RestrictionCell::accessible,
				RestrictionCell::sprite);
	}
	
	public static ArrayTranslation<RestrictionItem> terrain(int terrain) {
		return (item, translator) -> item.translateItem(terrain, translator);
	}
}
