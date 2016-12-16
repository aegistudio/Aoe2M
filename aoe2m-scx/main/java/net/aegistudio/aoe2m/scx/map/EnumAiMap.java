package net.aegistudio.aoe2m.scx.map;

public enum EnumAiMap {
	AI_MAP_00,
	AI_MAP_01,
	AI_MAP_02,
	AI_MAP_03,
	AI_MAP_04,
	AI_MAP_05,
	AI_MAP_06,
	AI_MAP_07,
	AI_MAP_08,
	ARABIA,
	ARCHIPELAGO, 
	BALTIC,
	BLACK_FOREST,
	COSTAL,
	CONTINENTIAL,
	CRATER_LAKE,
	FORTRESS,
	GOLD_RUSH,
	HIGHLAND,
	ISLANDS,
	MEDITERRANEAN,
	MIGRATION,
	RIVERS,
	TEAM_ISLANDS,
	AI_MAP_18,
	SCANDINAVIA,
	AI_MAP_1A,
	YUCATAN,
	SALT_MARSH,
	AI_MAP_1D,
	KING_OF_THE_HILL,
	OASIS,
	NOMAD;
	
	static {
		for(EnumAiMap map : values())
			AiMapWrapper.aiMapLookup.put(map.ordinal(), map);
	}
}
