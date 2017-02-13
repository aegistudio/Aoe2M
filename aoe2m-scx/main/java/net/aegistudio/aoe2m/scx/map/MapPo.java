package net.aegistudio.aoe2m.scx.map;

import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Wrapper;

public class MapPo {
	public Wrapper<Integer> weCameraX = new Container<Integer>(0);
	public Wrapper<Integer> weCameraY = new Container<Integer>(0);
	public AiMapWrapper aiMapType = new AiMapWrapper(-1);
	
	public Wrapper<Long> mapWidth = new Container<Long>(0l);
	public Wrapper<Long> mapHeight = new Container<Long>(0l);
	
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
	
	public Wrapper<Long> playerCount = new Container<Long>(9l);
	
	public List<UnitPo> gaia = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public List<UnitPo>[] units = new ArrayList[8];
	{ for(int i = 0; i < 8; i ++) units[i] = new ArrayList<>(); }
}
