package net.aegistudio.aoe2m.scx.player;

public enum EnumDiplomacy {
	ALLIED(2),
	NEUTRAL(3),
	UNKNOWN(-1),
	ENEMY(4);
	
	public final int auxiliaryOrder;
	
	private EnumDiplomacy(int auxiliaryOrder) {
		this.auxiliaryOrder = auxiliaryOrder;
	}
}
