package net.aegistudio.aoe2m.empires2x1p1.graphics;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class GraphicsDelta {
	public Wrapper<Integer> graphics = new Container<>(0);
	
	public Wrapper<Short> uk0 = new Container<>((short)0);
	
	public Wrapper<Short> uk1 = new Container<>((short)0);
	
	public Wrapper<Short> uk2 = new Container<>((short)0);
	
	public Wrapper<Short> directionX = new Container<>((short)0);
	
	public Wrapper<Short> directionY = new Container<>((short)0);
	
	public Wrapper<Short> uk3 = new Container<>((short)0);
	
	public Wrapper<Short> uk4 = new Container<>((short)0);
	
	public void translate(Translator translator) throws IOException {
		translator.unsigned16(graphics);
		translator.signed16(uk0);
		translator.signed16(uk1);
		translator.signed16(uk2);
		translator.signed16(directionX);
		translator.signed16(directionY);
		translator.signed16(uk3);
		translator.signed16(uk4);
	}
}
