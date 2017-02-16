package net.aegistudio.aoe2m.scx.map;

import java.io.IOException;
import java.util.List;

import net.aegistudio.uio.*;
import net.aegistudio.uio.wrap.*;
import net.aegistudio.aoe2m.scx.ScxConstants;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;

public class MapBuilder {
	private final MapPo map;
	public MapBuilder(MapPo map) {
		this.map = map;
	}
	
	public void buildTerrian(MetadataPo metadata, Translator translator) 
			throws CorruptException, IOException {
		ScxConstants.division(translator);
		translator.signed32(map.weCameraY);
		translator.signed32(map.weCameraX);
		if(metadata.version.getVersionFloat() >= 1.21)
			translator.signed32(map.aiMapType);
		
		translator.unsigned32(map.mapWidth);
		translator.unsigned32(map.mapHeight);
		
		for(int i = 0; i < map.mapWidth.get(); i ++)
			for(int j = 0; j < map.mapHeight.get(); j ++) {
				map.terrainId.setCursor(i, j);
				translator.signed8(map.terrainId);
				map.elevation.setCursor(i, j);
				translator.signed8(map.elevation);
				translator.constByte((byte)0);
			}
		
		translator.unsigned32(map.playerCount);
	}
	
	@SuppressWarnings("unchecked")
	private void buildUnit(List<UnitPo> unitList, Translator translator) 
			throws IOException, CorruptException {
		Wrapper<Integer> listLength = new Container<>(unitList.size());
		translator.signed32(listLength);
		translator.array(listLength.get(), unitList, UnitPo::new, UnitPo::build);
	}
	
	public void buildUnits(MetadataPo metadata, Translator translator) 
			throws IOException, CorruptException {
		buildUnit(map.gaia, translator);
		
		for(int i = 0; i < map.playerCount.get() - 1; i ++) 
			buildUnit(map.units[i], translator);
	}
	
	public MapPo getMap() {
		return this.map;
	}
}
