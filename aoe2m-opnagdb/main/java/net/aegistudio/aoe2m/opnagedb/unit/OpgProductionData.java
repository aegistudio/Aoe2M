package net.aegistudio.aoe2m.opnagedb.unit;

import net.aegistudio.aoe2m.assetdba.unit.ProductionData;
import net.aegistudio.aoe2m.assetdba.unit.ResourceStorage;

public class OpgProductionData extends ProductionData {
	public OpgResourceStorage costs;
	public int costLength() {
		return costs.count();
	}
	
	public ResourceStorage cost(int index) {
		return costs.storage(index);
	}
}
