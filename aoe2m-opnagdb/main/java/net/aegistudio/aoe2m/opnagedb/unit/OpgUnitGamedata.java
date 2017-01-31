package net.aegistudio.aoe2m.opnagedb.unit;

import net.aegistudio.aoe2m.assetdba.unit.BuildingData;
import net.aegistudio.aoe2m.assetdba.unit.CombatData;
import net.aegistudio.aoe2m.assetdba.unit.DamageGraphics;
import net.aegistudio.aoe2m.assetdba.unit.DiscoverData;
import net.aegistudio.aoe2m.assetdba.unit.EnumUnitType;
import net.aegistudio.aoe2m.assetdba.unit.ProductionData;
import net.aegistudio.aoe2m.assetdba.unit.ProjectileData;
import net.aegistudio.aoe2m.assetdba.unit.ResourceStorage;
import net.aegistudio.aoe2m.assetdba.unit.UnitDataBuilder;
import net.aegistudio.aoe2m.assetdba.unit.UnitGamedata;
import net.aegistudio.aoe2m.assetdba.unit.WalkingData;
import net.aegistudio.aoe2m.media.Storage;

public class OpgUnitGamedata extends UnitGamedata {
	public final Storage storage;
	public OpgUnitGamedata(EnumUnitType unitType, Storage storage) {
		this.type = unitType;
		this.storage = storage;
		
		type.build(this, new UnitDataBuilder<RuntimeException>() {
			@Override
			public float flagSpeed(float input) throws RuntimeException {
				return input;
			}

			@Override
			public WalkingData walking(WalkingData walking) throws RuntimeException {
				return walking == null? new WalkingData() : walking;
			}

			@Override
			public DiscoverData discover(DiscoverData discover) throws RuntimeException {
				return discover == null? new DiscoverData() : discover;
			}

			@Override
			public CombatData combat(CombatData combat) throws RuntimeException {
				return null;
			}

			@Override
			public ProjectileData projectile(ProjectileData projectile) throws RuntimeException {
				return null;
			}

			@Override
			public ProductionData production(ProductionData production) throws RuntimeException {
				return null;
			}

			@Override
			public BuildingData building(BuildingData buildingData) throws RuntimeException {
				return null;
			}
		});
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
