package net.aegistudio.aoe2m.assetdba.unit;

public enum EnumUnitType {
	EYE_CANDY,
	FLAG,
	DEAD_OR_FISH,
	BIRD,
	PROJECTILE,
	LIVING,
	BUILDING,
	TREE;
	
	public <E extends Exception> void build(UnitGamedata gamedata, UnitDataBuilder<E> dataBuilder) throws E {
		if(ordinal() >= FLAG.ordinal()) 
			gamedata.speed = dataBuilder.flagSpeed(gamedata.speed);
		
		if(ordinal() >= DEAD_OR_FISH.ordinal())
			gamedata.walking = dataBuilder.walking(gamedata.walking);
		
		if(ordinal() >= BIRD.ordinal())
			gamedata.discover = dataBuilder.discover(gamedata.discover);
		
		if(ordinal() >= PROJECTILE.ordinal())
			gamedata.combat = dataBuilder.combat(gamedata.combat);
		
		if(ordinal() == PROJECTILE.ordinal())
			gamedata.projectile = dataBuilder.projectile(gamedata.projectile);
		
		if(ordinal() >= LIVING.ordinal())
			gamedata.production = dataBuilder.production(gamedata.production);
		
		if(ordinal() >= BUILDING.ordinal())
			gamedata.building = dataBuilder.building(gamedata.building);
	}
	
	public static <E extends Exception> void buildInternal(
			UnitGamedata gamedata, UnitDataBuilder<E> dataBuilder) throws E{
		gamedata.type.build(gamedata, dataBuilder);
	}
}