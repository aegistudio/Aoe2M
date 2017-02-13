package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class MapTerrain {
	public final Wrapper<Integer> proportion = new Container<>(0);
	
	public final Wrapper<Integer> terrain = new Container<>(0);
	
	public final Wrapper<Integer> numberClumps = new Container<>(0);
	
	public final Wrapper<Integer> terrainSpacing = new Container<>(0);
	
	public final Wrapper<Integer> placementZone = new Container<>(0);
	
	public final Wrapper<Integer> uk0 = new Container<>(0);
	
	public void translate(Translator translator) throws IOException {
		translator.signed32(proportion);
		translator.signed32(terrain);
		translator.signed32(numberClumps);
		translator.signed32(terrainSpacing);
		translator.signed32(placementZone);
		translator.signed32(uk0);
	}
}
