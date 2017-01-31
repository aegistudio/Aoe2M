package net.aegistudio.aoe2m.opnagedb.unit;

import net.aegistudio.aoe2m.assetdba.unit.DamageGraphics;
import net.aegistudio.aoe2m.assetdba.unit.UnitGamedata;

public class OpgUnitGamedata extends UnitGamedata {

	public OpgUnitGamedata() {
		
	}
	
	@Override
	public int damageLength() {
		return 0;
	}

	@Override
	public DamageGraphics damageGraphics(int index) {
		return null;
	}

}
