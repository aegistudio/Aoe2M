package net.aegistudio.aoe2m.empires2x1p1.terrain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class MapData {
	public final Wrapper<Integer> mapPointer = new Container<>(0);
	
	public final Wrapper<Integer> uk0 = new Container<>(0);
	
	public final Wrapper<Integer> mapWidth = new Container<>(0);	
	
	public final Wrapper<Integer> mapHeight = new Container<>(0);	
	
	public final Wrapper<Integer> worldWidth = new Container<>(0);
	
	public final Wrapper<Integer> worldHeight = new Container<>(0);
	
	public final List<MapTileSize> tileSizes = new ArrayList<>();
	
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.signed32(mapPointer);
		translator.signed32(uk0);
		translator.signed32(mapWidth);
		translator.signed32(mapHeight);
		translator.signed32(worldWidth);
		translator.signed32(worldHeight);
		
		translator.array(19, tileSizes, MapTileSize::new, 
				tileSize -> tileSize.translate(translator));
	}
}
