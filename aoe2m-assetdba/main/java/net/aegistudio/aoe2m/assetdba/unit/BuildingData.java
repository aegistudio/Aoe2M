package net.aegistudio.aoe2m.assetdba.unit;

public abstract class BuildingData {
	public int graphicsConstruction;
	public int graphicsSnow;
	public int modeAdjacent;
	public int iconDisabler;
	public int disappearsWhenBuilt;
	public int stackUnitId;
	public int terrainId;
	public int resourceId;
	public int researchId;
	
	public static class BuildingAnnex {
		public int unit;
		public float misplacedX;
		public float misplacedY;
	}
	
	public abstract int annexLength();
	public abstract BuildingAnnex annex(int id);
	
	public int headUnit;
	public int transformUnit;
	public int constructionSound;
	public EnumGarrisonType garrisonType;
	public float garrisonHealRate;
}
