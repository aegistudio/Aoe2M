package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;

import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class MapUnit {
	public final Wrapper<Integer> unit = new Container<>(0);
	
	public final Wrapper<Integer> hostTerrain = new Container<>(0);
	
	public final Wrapper<Integer> uk0 = new Container<>(0);
	
	public final Wrapper<Integer> objectsPerGroup = new Container<>(0);
	
	public final Wrapper<Integer> fluctuation = new Container<>(0);
	
	public final Wrapper<Integer> groupsPerPlayer = new Container<>(0);
	
	public final Wrapper<Integer> groupsRadius = new Container<>(0);
	
	public final Wrapper<Integer> ownAtStart = new Container<>(0);
	
	public final Wrapper<Integer> setPlaceForAllPlayer = new Container<>(0);
	
	public final Wrapper<Integer> minDistance = new Container<>(0);
	
	public final Wrapper<Integer> maxDistance = new Container<>(0);
	
	public void translate(Translator translator) throws IOException {
		translator.signed32(unit);
		translator.signed32(hostTerrain);
		translator.signed32(uk0);
		translator.signed32(objectsPerGroup);
		translator.signed32(fluctuation);
		translator.signed32(groupsPerPlayer);
		translator.signed32(groupsRadius);
		translator.signed32(ownAtStart);
		translator.signed32(setPlaceForAllPlayer);
		translator.signed32(minDistance);
		translator.signed32(maxDistance);
	}
}
