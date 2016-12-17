package net.aegistudio.aoe2m.scx.map;

import net.aegistudio.aoe2m.scx.VariantList;
import net.aegistudio.aoe2m.scx.Wrapper;

public class MapPo {
	public Wrapper<Integer> weCameraX = new Wrapper<Integer>(0);
	public Wrapper<Integer> weCameraY = new Wrapper<Integer>(0);
	public AiMapWrapper aiMapType = new AiMapWrapper(-1);
	
	public Wrapper<Long> mapWidth = new Wrapper<Long>(0l);
	public Wrapper<Long> mapHeight = new Wrapper<Long>(0l);
	
	public TerrainPo terrainId = new TerrainPo(256, 256);
	public TerrainPo elevation = new TerrainPo(256, 256); {
		for(int i = 0; i < 256; i ++)
			for(int j = 0; j < 256; j ++) {
				terrainId.setCursor(i, j);
				elevation.setCursor(i, j);
				terrainId.setValue((byte) EnumTerrain.GRASS.terrainId);
				elevation.setValue((byte) EnumTerrain.GRASS.defaultElevation);
			}
	}
	
	public Wrapper<Long> playerCount = new Wrapper<Long>(9l);
	
	public VariantList<UnitPo> gaia = new VariantList<>(UnitPo::new, UnitPo::build);
	
	@SuppressWarnings("unchecked")
	public VariantList<UnitPo>[] units = new VariantList[8];
	{ for(int i = 0; i < 8; i ++) units[i] = new VariantList<>(UnitPo::new, UnitPo::build); }
}
