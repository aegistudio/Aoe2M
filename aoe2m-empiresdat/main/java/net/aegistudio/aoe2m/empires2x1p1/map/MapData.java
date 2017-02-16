package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class MapData {
	public final Wrapper<Integer> mapPointer = new Container<>(0);
	
	public final Wrapper<Integer> uk0 = new Container<>(0);
	
	public final Wrapper<Integer> mapWidth = new Container<>(0);	
	
	public final Wrapper<Integer> mapHeight = new Container<>(0);	
	
	public final Wrapper<Integer> worldWidth = new Container<>(0);
	
	public final Wrapper<Integer> worldHeight = new Container<>(0);
	
	public final List<MapTileSize> tileSizes = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translateMapData1(Translator translator) throws IOException, CorruptException {
		translator.signed32(mapPointer);
		translator.signed32(uk0);
		translator.signed32(mapWidth);
		translator.signed32(mapHeight);
		translator.signed32(worldWidth);
		translator.signed32(worldHeight);
		
		translator.array(19, tileSizes, MapTileSize::new, 
				MapTileSize::translate);
	}
	
	public final Wrapper<Integer> uk1 = new Container<>(0);
	
	public final Wrapper<Float> mapMinX = new Container<>(0f);
	
	public final Wrapper<Float> mapMinY = new Container<>(0f);
	
	public final Wrapper<Float> mapMaxX = new Container<>(0f);
	
	public final Wrapper<Float> mapMaxY = new Container<>(0f);
	
	public final Wrapper<Float> mapMaxXp1 = new Container<>(0f);
	
	public final Wrapper<Float> mapMaxYp1 = new Container<>(0f);
	
	public final Wrapper<Short> uk2 = new Container<>((short)0);
	
	public final Wrapper<Short> uk3 = new Container<>((short)0);
	
	public final Wrapper<Short> maxTerrain = new Container<>((short)0);
	
	public final Wrapper<Short> tileWidth = new Container<>((short)0);
	
	public final Wrapper<Short> tileHeight = new Container<>((short)0);
	
	public final Wrapper<Short> tileHalfHeight = new Container<>((short)0);
	
	public final Wrapper<Short> tileHalfWidth = new Container<>((short)0);
	
	public final Wrapper<Short> elevationHeight = new Container<>((short)0);
	
	public final Wrapper<Short> currentRow = new Container<>((short)0);
	
	public final Wrapper<Short> currentColumn = new Container<>((short)0);
	
	public final Wrapper<Short> blockBeginRow = new Container<>((short)0);
	
	public final Wrapper<Short> blockEndRow = new Container<>((short)0);
	
	public final Wrapper<Short> blockBeginColumn = new Container<>((short)0);
	
	public final Wrapper<Short> blockEndColumn = new Container<>((short)0);
	
	public final Wrapper<Integer> uk4 = new Container<>(0);
	
	public final Wrapper<Integer> uk5 = new Container<>(0);
	
	public final Wrapper<Byte> frameChange = new Container<>((byte)0);
	
	public final Wrapper<Byte> visible = new Container<>((byte)0);
	
	public final Wrapper<Byte> fog = new Container<>((byte)0);

	public void translateMapData2(Translator translator) throws IOException, CorruptException {
		translator.signed32(uk1);
		translator.float32(mapMinX);
		translator.float32(mapMinY);
		translator.float32(mapMaxX);
		translator.float32(mapMaxY);
		translator.float32(mapMaxXp1);
		translator.float32(mapMaxYp1);
		
		translator.signed16(uk2);
		translator.signed16(uk3);
		
		translator.signed16(maxTerrain);
		translator.signed16(tileWidth);	
		translator.signed16(tileHeight);
		translator.signed16(tileHalfHeight);
		translator.signed16(tileHalfWidth);	
	
		translator.signed16(elevationHeight);
		
		translator.signed16(currentRow);
		translator.signed16(currentColumn);

		translator.signed16(blockBeginRow);
		translator.signed16(blockEndRow);
		
		translator.signed16(blockBeginColumn);
		translator.signed16(blockEndColumn);
		
		translator.signed32(uk4);
		translator.signed32(uk5);
		
		translator.signed8(frameChange);
		translator.signed8(visible);
		translator.signed8(fog);
	}
	
}
