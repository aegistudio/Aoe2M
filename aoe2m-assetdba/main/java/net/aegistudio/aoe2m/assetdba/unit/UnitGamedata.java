package net.aegistudio.aoe2m.assetdba.unit;

import net.aegistudio.aoe2m.assetdba.SlpImage;
import net.aegistudo.aoe2m.unittype.EnumUnitType;

/**
 * Comprises unit type and info header.
 * 
 * @author aegistudio
 */

public abstract class UnitGamedata {
	public EnumUnitType type;
	
	public int id0, dllName, dllCreation;
	
	public int unitClass;
	
	public int graphicsStand0, graphicsStand1;
	
	public int graphicsDying0, graphicsDying1;
	
	public int deathMode, hitPoints, lineOfSights;
	
	public int garrisonCapacity;
	
	public float radiusX, radiusY, radiusZ;
	
	public int soundCreation0, soundCreation1;
	
	public int deadUnitId;
	
	public int placementMode, airMode;
	
	public SlpImage icon;
	
	public boolean hidden, enabled;
	
	public int placementTerrain0, placementTerrain1;
	
	public int placementSideTerrain0, placementSideTerrain1;
	
	public float clearanceX, clearanceY;
	
	public EnumBuildingMode buildingMode;
	
	public boolean visibleInFog;
	
	public int flyMode;
	
	public int resourceCapacity;
	
	public float resourceDecay;
	
	public EnumBlastDefence blastDefence;
	
	public int subType;
	
	public int minimapMode;
	
	public EnumInteractionMode interactionMode;
	
	public int commandAttribute;
	
	public int minimapColor;
	
	public int dllHelp;
	
	public final int hotKey[] = new int[4];
	
	public boolean unselectable;
	
	public int selectionMask;
	
	public int selectionShape;
	
	public int attribute;
	
	public int attributePiece;
	
	public int selectionEffect;
	
	public int selectionColor;
	
	public float selectionShapeX, selectionShapeY, selectionShapeZ;
	
	public abstract int storageLength();
	public abstract ResourceStorage storage(int index);
	
	public abstract int damageLength();
	public abstract DamageGraphics damageGraphics(int index);
	
	public int soundSelection, soundDying;
	
	public EnumAttackMode attackMode;
	
	public String name;
	
	public int id1, id2;
	
	public float speed;
	
	public WalkingData walking;
	
	public DiscoverData discover;
	
	public CombatData combat;
	
	public ProjectileData projectile;
	
	public ProductionData production;
	
	public BuildingData building;
}
