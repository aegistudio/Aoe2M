package net.aegistudio.aoe2m.assetdba.unit;

public class BuildingData {
	public int graphicConstruction;
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
	public BuildingAnnex[] buildingAnnex = new BuildingAnnex[4];
	
	public int headUnit;
	public int transformUnit;
	public int constructionSound;
	public int garrisonType;
	public float garrisonHealRate;
}
