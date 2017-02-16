package net.aegistudio.aoe2m.empires2x1p1.restriction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class TerrainRestriction {
	public List<RestrictionItem> restrictions = new ArrayList<>();
	public List<Wrapper<Integer>> offset0 = new ArrayList<>();
	public List<Wrapper<Integer>> offset1 = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(int restriction, int terrain, Translator translator) 
			throws IOException, CorruptException {
		
		translator.array(restriction, offset0, 
				Container::int0, Translator.squeech(translator::signed32));
		
		translator.array(restriction, offset1, 
				Container::int0, Translator.squeech(translator::signed32));
		
		translator.array(restriction, restrictions, 
				RestrictionItem::new, RestrictionItem.terrain(terrain));
	}
}
