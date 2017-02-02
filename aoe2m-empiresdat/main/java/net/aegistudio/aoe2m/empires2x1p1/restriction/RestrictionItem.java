package net.aegistudio.aoe2m.empires2x1p1.restriction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;

import static net.aegistudio.aoe2m.TranslateWrapper.wrapAll;

public class RestrictionItem {
	public final List<RestrictionCell> sprites = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translateItem(int terrain, FieldTranslator translator) throws IOException, CorruptionException {
		translator.array(terrain, sprites, 
				RestrictionCell::new, wrapAll(translator,
				RestrictionCell::accessible,
				RestrictionCell::sprite));
	}
}
