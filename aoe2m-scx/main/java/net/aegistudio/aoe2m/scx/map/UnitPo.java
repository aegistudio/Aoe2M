package net.aegistudio.aoe2m.scx.map;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;
import net.aegistudio.aoe2m.scx.StringFormater;

public class UnitPo {
	public Wrapper<Float> positionX = new Container<Float>(0.0f);
	public Wrapper<Float> positionY = new Container<Float>(0.0f);
	public Wrapper<Float> positionZ = new Container<Float>(2.0f);
	
	public Wrapper<Long> unitId = new Container<Long>(0l);
	public Wrapper<Short> unitType = new Container<Short>((short) 0);
	public Wrapper<Byte> indicator = new Container<Byte>((byte)2);
	
	public Wrapper<Float> rotation = new Container<Float>(0.f);
	public Wrapper<Short> animationFrame = new Container<Short>((short) 0);
	public Wrapper<Integer> garrisonedId = new Container<Integer>(-1);
	
	public void build(Translator translator) throws IOException {
		translator.float32(positionX);
		translator.float32(positionY);
		translator.float32(positionZ);
		
		translator.unsigned32(unitId);
		translator.signed16(unitType);
		translator.signed8(indicator);
		
		EnumIndicator.getBuilder(indicator.getValue())
			.buildUnit(this, translator);
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
