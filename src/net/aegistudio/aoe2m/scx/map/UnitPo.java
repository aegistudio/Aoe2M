package net.aegistudio.aoe2m.scx.map;

import net.aegistudio.aoe2m.scx.Wrapper;

public class UnitPo {
	public Wrapper<Float> positionX = new Wrapper<Float>(0.0f);
	public Wrapper<Float> positionY = new Wrapper<Float>(0.0f);
	public Wrapper<Float> positionHeight = new Wrapper<Float>(2.0f);
	
	public Wrapper<Long> unitId = new Wrapper<Long>(0l);
	public Wrapper<Short> unitType = new Wrapper<Short>((short) 0);
	public Wrapper<Byte> indicator = new Wrapper<Byte>((byte)2);
	
	public Wrapper<Float> rotation = new Wrapper<Float>(0.f);
	public Wrapper<Short> animationFrame = new Wrapper<Short>((short) 0);
	public Wrapper<Long> garrisonedId = new Wrapper<Long>(-1l);
}
