package net.aegistudio.aoe2m.assetdba.unit;

public interface UnitDataBuilder<E extends Exception> {
	public float flagSpeed(float input) throws E;
	
	public WalkingData walking(WalkingData walking) throws E;
	
	public DiscoverData discover(DiscoverData discover) throws E;
	
	public CombatData combat(CombatData combat) throws E;
	
	public ProjectileData projectile(ProjectileData projectile) throws E;
	
	public ProductionData production(ProductionData production) throws E;
	
	public BuildingData building(BuildingData buildingData) throws E;
}