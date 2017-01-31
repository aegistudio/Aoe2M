package net.aegistudio.aoe2m.opnagedb.unit;

import net.aegistudio.aoe2m.assetdba.unit.BuildingData;

public class OpgBuildingData extends BuildingData {
	public OpgBuildingAnnex annex;
	public int annexLength() {
		return annex.annexLength();
	}

	public BuildingAnnex annex(int id) {
		return annex.annex(id);
	}
}
