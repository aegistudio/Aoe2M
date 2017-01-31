package net.aegistudio.aoe2m.assetdba.unit;

public abstract class ProductionData {
	public abstract int costLength();
	public abstract ResourceStorage cost(int index);
	
	public int creationTime;
	public int creationLocationId;
	public int creationButtonId;
	public EnumCreatable creatableType;
	public int heroMode;
	public int graphicsGarrison;
	
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
