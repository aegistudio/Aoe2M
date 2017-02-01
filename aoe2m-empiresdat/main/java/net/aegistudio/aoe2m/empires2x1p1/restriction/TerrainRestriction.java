package net.aegistudio.aoe2m.empires2x1p1.restriction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
//import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class TerrainRestriction {
	public List<RestrictionItem> restrictions = new ArrayList<>();
	public List<Wrapper<Integer>> offset0 = new ArrayList<>();
	public List<Wrapper<Integer>> offset1 = new ArrayList<>();
	
	public void translate(int restriction, int terrain, FieldTranslator translator) 
			throws IOException, CorruptionException {
		
		translator.array(restriction, offset0, 
				() -> new Container<Integer>(0), 
				item -> translator.signed32(item));

		translator.array(restriction, offset1, 
				() -> new Container<Integer>(0), 
				item -> translator.signed32(item));
		
		translator.array(restriction, restrictions, 
				RestrictionItem::new, 
				item -> item.translateItem(terrain, translator));
	}
}
