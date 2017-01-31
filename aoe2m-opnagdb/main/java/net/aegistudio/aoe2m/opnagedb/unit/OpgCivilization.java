package net.aegistudio.aoe2m.opnagedb.unit;

import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;
import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.unit.Civilization;

public class OpgCivilization extends Civilization {
	public OpgCivilization(int index, AssetListener perfLog, String[] parameters) {
		perfLog.initAsset(CIV_NAME, CIV_CLASS, index);
		id = index;
		name = parameters[0];
		techTree = Integer.parseInt(parameters[1]);
		teamBonus = Integer.parseInt(parameters[2]);
		units = Integer.parseInt(parameters[3]);
		perfLog.readyAsset(CIV_NAME, CIV_CLASS, index);
	}
	
	public int compare(OpgCivilization another) {
		return this.id - another.id;
	}
}
