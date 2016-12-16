package net.aegistudio.aoe2m.scx.map;

public enum EnumTerrain {
	GRASS(0, 2, 0x000, 0x0ff, 0x00),
	WATER_SHALLOW(1, 1, 0x077, 0x077, 0x0ff),
	BEACH(2, 1, 0x0ee, 0x0ee, 0x000),
	SHALLOW(4, 1, 0x0cc, 0x0cc, 0x0aa),
	WITHERED_LEAVES(5, 1, 0x0cc, 0x0ff, 0x00),
	MUD(6, 1, 0x0aa, 0x088, 0x000),
	FARM(7, 1, 0x0aa, 0x0ff, 0x011),
	GRASS_3(9, 1, 0x055, 0x0ee, 0x00),
	GRASS_2(12, 1, 0x088, 0x0ff, 0x00),
	DESSERT(14, 1, 0x0ff, 0x0ff, 0x000),
	WATER_DEEP(22, 1, 0x022, 0x022, 0x0ff),
	WATER_MEDIUM(23, 1, 0x055, 0x055, 0x0ff),
	ROAD(24, 1, 0x0ff, 0x0ff, 0x0cc),
	BAD_ROAD(25, 1, 0x077, 0x077, 0x066),
	SNOW(32, 1, 0x0ff, 0x0ff, 0x0ff),
	SNOWY_MUD(33, 1, 0x0ee, 0x0dd, 0x077),
	SNOWY_GRASS(34, 1, 0x0aa, 0x0dd, 0x0aa),
	ICE(35, 1, 0xaa, 0xaa, 0xff),
	SNOWY_ROAD(38, 1, 0xff, 0xff, 0xee),
	MUSHROOM_ROAD(39, 1, 0x55, 0xff, 0x55);
	
	public final int terrainId;
	public final int defaultElevation;
	public final int r, g, b;
	
	private EnumTerrain(int terrianId, int defaultElevation, int r, int g, int b) {
		this.terrainId = terrianId;
		this.defaultElevation = defaultElevation;
		this.r = r; this.g = g; this.b = b;
	}
	
	public static final EnumTerrain[] terrianLookup = new EnumTerrain[256];
	static {
		for(EnumTerrain terrain : values())
			terrianLookup[terrain.terrainId] = terrain;
	}
	
	public static EnumTerrain getTerrian(byte id) {
		if(id < 0) return terrianLookup[256 + ((int)id)];
		else return terrianLookup[id];
	}
}
