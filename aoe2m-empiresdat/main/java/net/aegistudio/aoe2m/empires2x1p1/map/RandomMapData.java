package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class RandomMapData {
	public final Wrapper<Integer> randomMapPointer = new Container<>(0);
	
	public final List<RandomMap> randomMaps = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		Wrapper<Integer> randomMapCount = new Container<>(randomMaps.size());
		translator.signed32(randomMapCount);
		translator.signed32(randomMapPointer);
		
		translator.array(randomMapCount.get(), randomMaps, 
				RandomMap::new, 
				RandomMap::translateHeader,
				RandomMap::translateBody);
	}
}
