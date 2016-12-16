package net.aegistudio.aoe2m.scx.trigger;

public enum EnumResourceType {
	NONE(-1),					
	FOOD(1),					// 1
	WOOD(2),					// 2
	STONE(3),					// 3
	GOLD(4),					// 4
	RELICS(7),					// 7
	POPULATION(11),				// 11
	KILLS(20),					// 20
	TECHNOLOGIES(21),			// 21
	VILLAGER_POPULATION(37),	// 37
	MILITARY_POPULATION(40),	// 40
	CONVERSIONS(41),			// 41
	RAZINGS(43),				// 43
	KILL_RATIO(44);				// 44
	
	public final int auxilliaryOrder;
	private EnumResourceType(int auxilliaryOrder) {
		this.auxilliaryOrder = auxilliaryOrder;
	}
	
	private static final EnumResourceType[] LOOKUP = new EnumResourceType[50];
	static {
		for(EnumResourceType type : values())
			LOOKUP[type.auxilliaryOrder + 1] = type;
	}
	
	public static EnumResourceType getByAuxOrder(int auxilliaryOrder) {
		int realIndex = auxilliaryOrder + 1;
		if(realIndex >= 0 && realIndex < LOOKUP.length) 
			return LOOKUP[realIndex];
		return null;
	}
}
