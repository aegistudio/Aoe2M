package net.aegistudio.aoe2m.scx.map;

import java.io.IOException;

import net.aegistudio.aoe2m.scx.FieldTranslator;
import net.aegistudio.aoe2m.scx.StringFormater;
import net.aegistudio.aoe2m.scx.Wrapper;

public class UnitPo {
	public Wrapper<Float> positionX = new Wrapper<Float>(0.0f);
	public Wrapper<Float> positionY = new Wrapper<Float>(0.0f);
	public Wrapper<Float> positionZ = new Wrapper<Float>(2.0f);
	
	public Wrapper<Long> unitId = new Wrapper<Long>(0l);
	public Wrapper<Short> unitType = new Wrapper<Short>((short) 0);
	public Wrapper<Byte> indicator = new Wrapper<Byte>((byte)2);
	
	public Wrapper<Float> rotation = new Wrapper<Float>(0.f);
	public Wrapper<Short> animationFrame = new Wrapper<Short>((short) 0);
	public Wrapper<Integer> garrisonedId = new Wrapper<Integer>(-1);
	
	public void buildUnit(FieldTranslator translator) throws IOException {
		translator.float32(positionX);
		translator.float32(positionY);
		translator.float32(positionZ);
		
		translator.unsigned32(unitId);
		translator.signed16(unitType);
		translator.signed8(indicator);
		
		EnumIndicator.getBuilder(indicator.getValue())
			.buildUnit(this, translator);
	}
	
	public static void build(UnitPo unit, FieldTranslator translator) throws IOException {
		unit.buildUnit(translator);
	}
	
	public String toString() {
		StringFormater toString = new StringFormater(this);
		toString.add("Id", unitId);
		toString.add("Type", unitType);
		toString.add("DataType", EnumIndicator.getBuilder(
				indicator.getValue()));
		toString.line();
		
		toString.add("X", positionX);
		toString.add("Y", positionY);
		toString.add("Z", positionZ);
		toString.line();
		
		toString.add("Garrison", garrisonedId);
		toString.add("Rotation", rotation);
		toString.add("Frame", animationFrame);
		
		return toString.toString();
	}
}
