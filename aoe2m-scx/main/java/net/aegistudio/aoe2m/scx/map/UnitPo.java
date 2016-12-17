package net.aegistudio.aoe2m.scx.map;

import java.io.IOException;

import net.aegistudio.aoe2m.scx.FieldTranslator;
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
	
	public void buildUnit(FieldTranslator translator) throws IOException {
		translator.float32(positionX);
		translator.float32(positionY);
		translator.float32(positionHeight);
		
		translator.unsigned32(unitId);
		translator.signed16(unitType);
		translator.signed8(indicator);
		
		EnumIndicator.getBuilder(indicator.getValue())
			.buildUnit(this, translator);
	}
	
	public static void build(UnitPo unit, FieldTranslator translator) throws IOException {
		unit.buildUnit(translator);
	}
}
