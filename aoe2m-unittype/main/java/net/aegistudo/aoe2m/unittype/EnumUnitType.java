package net.aegistudo.aoe2m.unittype;

public enum EnumUnitType {
	EYE_CANDY,
	FLAG,
	DEAD_OR_FISH,
	BIRD,
	PROJECTILE,
	LIVING,
	BUILDING,
	TREE;
	
	public <E extends Exception> void build(UnitBuilder<E> dataBuilder) throws E {
		if(ordinal() == TREE.ordinal())
			return;	// done.
		
		if(ordinal() >= FLAG.ordinal()) 
			dataBuilder.flagSpeed();
		
		if(ordinal() >= DEAD_OR_FISH.ordinal())
			dataBuilder.walking();
		
		if(ordinal() >= BIRD.ordinal())
			dataBuilder.discover();
		
		if(ordinal() >= PROJECTILE.ordinal())
			dataBuilder.combat();
		
		if(ordinal() == PROJECTILE.ordinal())
			dataBuilder.projectile();
		
		if(ordinal() >= LIVING.ordinal())
			dataBuilder.production();
		
		if(ordinal() >= BUILDING.ordinal())
			dataBuilder.building();
	}
}
