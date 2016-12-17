package net.aegistudio.aoe2m.scx.map;

import java.io.IOException;

import net.aegistudio.aoe2m.scx.CorruptionException;
import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class MapBuilder {
	private final MapPo map;
	public MapBuilder(MapPo map) {
		this.map = map;
	}
	
	public void buildTerrian(MetadataPo metadata, FieldTranslator translator) 
			throws CorruptionException, IOException {
		translator.division();
		translator.signed32(map.weCameraY);
		translator.signed32(map.weCameraX);
		if(metadata.version.getVersionFloat() >= 1.21)
			translator.signed32(map.aiMapType);
		
		translator.unsigned32(map.mapWidth);
		translator.unsigned32(map.mapHeight);
		
		for(int i = 0; i < map.mapWidth.getValue(); i ++)
			for(int j = 0; j < map.mapHeight.getValue(); j ++) {
				map.terrianId.setCursor(i, j);
				translator.signed8(map.terrianId);
				map.elevation.setCursor(i, j);
				translator.signed8(map.elevation);
				translator.constByte(0);
			}
		
		translator.unsigned32(map.playerCount);
	}
	
	public void buildUnits(MetadataPo metadata, FieldTranslator translator) 
			throws Exception {
		map.gaia.build(translator);
		
		for(int i = 0; i < map.playerCount.getValue() - 1; i ++) 
			map.units[i].build(translator);
	}
	
	public MapPo getMap() {
		return this.map;
	}
}
