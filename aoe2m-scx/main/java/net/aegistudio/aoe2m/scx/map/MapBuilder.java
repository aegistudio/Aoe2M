package net.aegistudio.aoe2m.scx.map;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.scx.ScxConstants;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class MapBuilder {
	private final MapPo map;
	public MapBuilder(MapPo map) {
		this.map = map;
	}
	
	public void buildTerrian(MetadataPo metadata, Translator translator) 
			throws CorruptionException, IOException {
		ScxConstants.division(translator);
		translator.signed32(map.weCameraY);
		translator.signed32(map.weCameraX);
		if(metadata.version.getVersionFloat() >= 1.21)
			translator.signed32(map.aiMapType);
		
		translator.unsigned32(map.mapWidth);
		translator.unsigned32(map.mapHeight);
		
		for(int i = 0; i < map.mapWidth.getValue(); i ++)
			for(int j = 0; j < map.mapHeight.getValue(); j ++) {
				map.terrainId.setCursor(i, j);
				translator.signed8(map.terrainId);
				map.elevation.setCursor(i, j);
				translator.signed8(map.elevation);
				translator.constByte(0);
			}
		
		translator.unsigned32(map.playerCount);
	}
	
	public void buildUnits(MetadataPo metadata, Translator translator) 
			throws IOException, CorruptionException {
		map.gaia.build(translator);
		
		for(int i = 0; i < map.playerCount.getValue() - 1; i ++) 
			map.units[i].build(translator);
	}
	
	public MapPo getMap() {
		return this.map;
	}
}
