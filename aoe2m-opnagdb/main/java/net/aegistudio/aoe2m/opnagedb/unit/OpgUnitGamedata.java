package net.aegistudio.aoe2m.opnagedb.unit;

import net.aegistudio.aoe2m.assetdba.unit.DamageGraphics;
import net.aegistudio.aoe2m.assetdba.unit.DiscoverData;
import net.aegistudio.aoe2m.assetdba.unit.ProjectileData;
import net.aegistudio.aoe2m.assetdba.unit.ResourceStorage;
import net.aegistudio.aoe2m.assetdba.unit.UnitGamedata;
import net.aegistudio.aoe2m.assetdba.unit.WalkingData;
import net.aegistudio.uio.media.Storage;
import net.aegistudo.aoe2m.unittype.EnumUnitType;
import net.aegistudo.aoe2m.unittype.UnitBuilder;

public class OpgUnitGamedata extends UnitGamedata implements UnitBuilder<RuntimeException> {
	public final Storage storage;
	public OpgUnitGamedata(EnumUnitType unitType, Storage storage) {
		this.type = unitType;
		this.storage = storage;
		
		type.build(this);
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

	@Override
	public void flagSpeed() throws RuntimeException {
		
	}

	@Override
	public void walking() throws RuntimeException {
		walking = new WalkingData();
	}

	@Override
	public void discover() throws RuntimeException {
		discover = new DiscoverData();
	}

	@Override
	public void combat() throws RuntimeException {
		combat = new OpgCombatData();
	}

	@Override
	public void projectile() throws RuntimeException {
		projectile = new ProjectileData();
	}

	@Override
	public void production() throws RuntimeException {
		production = new OpgProductionData();
	}

	@Override
	public void building() throws RuntimeException {
		building = new OpgBuildingData();
	}
}
