package net.aegistudio.aoe2m.assetdba.unit;

public abstract class CombatData {
	public int defaultArmor;
	
	public abstract int attackLength();
	public abstract int attackType(int index);
	public abstract int attackAmount(int index);
	
	public abstract int armorLength();
	public abstract int armorType(int index);
	public abstract int armorAmount(int index);
	
	public int interactionType;
	public float maxRange;
	public float blastWidth;
	public float reloadTime;
	public int projectileUnit;
	public int accuracyPercent;
	public int towerMode;
	public int delay;
	public float displacementLayer;
	public float displacementDistance;
	public float displacementHeight;
	public int blastAttackLevel;
	public float minRange;
	public float accuracyDispersion;
	public int graphicsAttack;
	
	public int displayedMeleeArmor;
	public int displayedAttack;
	public float displayedRange;
	public float displayedReloadTime;
}
