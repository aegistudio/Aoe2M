package net.aegistudio.aoe2m.empires2x1p1.unit;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class DamageGraphics {
	public Wrapper<Short> id = Container.short0();
	
	public Wrapper<Byte> percent = Container.byte0();
	
	public Wrapper<Byte> oldApplyMode = Container.byte0();
	
	public Wrapper<Byte> applyMode = Container.byte0();
	
	public void translate(FieldTranslator translator) throws IOException {
		translator.signed16(id);
		translator.signed8(percent);
		translator.signed8(oldApplyMode);
		translator.signed8(applyMode);		
	}
}
