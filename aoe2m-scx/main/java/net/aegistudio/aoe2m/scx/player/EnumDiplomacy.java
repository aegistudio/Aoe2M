package net.aegistudio.aoe2m.scx.player;

public enum EnumDiplomacy {
	ALLIED(2),
	NEUTRAL(3),
	UNKNOWN(-1),
	ENEMY(4);
	
	public final int auxiliaryOrder;
	private static final EnumDiplomacy[] LOOKUP = new EnumDiplomacy[8];
	
	private EnumDiplomacy(int auxiliaryOrder) {
		this.auxiliaryOrder = auxiliaryOrder;
	}
	
	static {
		for(EnumDiplomacy entry : values())
			LOOKUP[entry.auxiliaryOrder + 1] = entry;
	}
	
	public static EnumDiplomacy getByAuxOrder(int auxiliaryOrder) {
		return LOOKUP[auxiliaryOrder + 1];
	}
}
