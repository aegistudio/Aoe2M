package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

import static net.aegistudio.aoe2m.TranslateWrapper.wrapAll;

public class RandomMapData {
	public final Wrapper<Integer> randomMapPointer = new Container<>(0);
	
	public final List<RandomMap> randomMaps = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptionException {
		Wrapper<Integer> randomMapCount = new Container<>(randomMaps.size());
		translator.signed32(randomMapCount);
		translator.signed32(randomMapPointer);
		
		translator.array(randomMapCount.getValue(), randomMaps, 
				RandomMap::new, wrapAll(translator,
				RandomMap::translateHeader,
				RandomMap::translateBody));
	}
}
