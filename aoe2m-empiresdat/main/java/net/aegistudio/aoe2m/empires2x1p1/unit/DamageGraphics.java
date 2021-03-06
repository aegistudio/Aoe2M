package net.aegistudio.aoe2m.empires2x1p1.unit;

import java.io.IOException;

import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class DamageGraphics {
	public Wrapper<Short> id = Container.short0();
	
	public Wrapper<Byte> percent = Container.byte0();
	
	public Wrapper<Byte> oldApplyMode = Container.byte0();
	
	public Wrapper<Byte> applyMode = Container.byte0();
	
	public void translate(Translator translator) throws IOException {
		translator.signed16(id);
		translator.signed8(percent);
		translator.signed8(oldApplyMode);
		translator.signed8(applyMode);		
	}
}
