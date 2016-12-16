package net.aegistudio.aoe2m.scx.map;

import net.aegistudio.aoe2m.scx.Wrapper;

public class MapPo {
	public Wrapper<Integer> weCameraX = new Wrapper<Integer>(0);
	public Wrapper<Integer> weCameraY = new Wrapper<Integer>(0);
	public AiMapWrapper aiMapType = new AiMapWrapper(-1);
	
	public Wrapper<Long> mapWidth = new Wrapper<Long>(0l);
	public Wrapper<Long> mapHeight = new Wrapper<Long>(0l);
	
	public TerrianPo terrianId = new TerrianPo(256, 256);
	public TerrianPo elevation = new TerrianPo(256, 256);
	
	public Wrapper<Long> playerCount = new Wrapper<Long>(9l);
	
	public UnitList gaia = new UnitList(0l);
	public UnitList[] units = new UnitList[8];
	{ for(int i = 0; i < 8; i ++) units[i] = new UnitList(0l); }
}
