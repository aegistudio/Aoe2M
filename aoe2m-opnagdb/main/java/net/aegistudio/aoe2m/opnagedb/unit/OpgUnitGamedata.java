package net.aegistudio.aoe2m.opnagedb.unit;

import net.aegistudio.aoe2m.assetdba.unit.DamageGraphics;
import net.aegistudio.aoe2m.assetdba.unit.ResourceStorage;
import net.aegistudio.aoe2m.assetdba.unit.UnitGamedata;
import net.aegistudio.aoe2m.media.Storage;

public class OpgUnitGamedata extends UnitGamedata {
	public final Storage storage;
	public OpgUnitGamedata(Storage storage) {
		this.storage = storage;
	}

	public OpgDamageGraphics damageGraphics;
	public int damageLength() {
		return damageGraphics.count();
	}

	public DamageGraphics damageGraphics(int index) {
		return damageGraphics.damageGraphics(index);
	}

	public OpgResourceStorage resourceStorage;
	public ResourceStorage storage(int index) {
		return resourceStorage.storage(index);
	}
	
	public int storageLength() {
		return resourceStorage.count();
	}
}
