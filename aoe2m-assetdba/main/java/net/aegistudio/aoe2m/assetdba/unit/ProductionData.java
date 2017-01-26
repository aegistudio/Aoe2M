package net.aegistudio.aoe2m.assetdba.unit;

public class ProductionData {
	public final ResourceStorage[] costs = new ResourceStorage[3];
	
	public int creationTime;
	public int creationLocationId;
	public int creationButtonId;
	public int creatableType;
	public int heroMode;
	public int graphicGarrison;
	
	public static class AttackProjectile {
		public float count;
		public int maxCount;
		public float spawningAreaWidth;
		public float spawningAreaLength;
		public float spawningAreaRandomness;
		public int secondaryUnitId;
	}
	public AttackProjectile attackProjectile;
	
	public int graphicsSpecial;
	public int specialActivation;
	public int displayedPierceArmor;
}
