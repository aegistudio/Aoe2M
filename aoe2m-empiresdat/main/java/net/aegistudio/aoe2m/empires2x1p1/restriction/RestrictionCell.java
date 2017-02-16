package net.aegistudio.aoe2m.empires2x1p1.restriction;

import java.io.IOException;

import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class RestrictionCell {
	public final Wrapper<Float> accessible = Container.float0();
	
	public final Wrapper<Integer> graphicsEnter = Container.int1m();
	public final Wrapper<Integer> graphicsExit = Container.int1m();
	public final Wrapper<Integer> graphicsWalk = Container.int1m();
	public final Wrapper<Float> spriteRate = Container.float0();
	
	public void accessible(Translator translator) throws IOException {
		translator.float32(accessible);
	}
	
	public void sprite(Translator translator) throws IOException {
		translator.signed32(graphicsEnter);
		translator.signed32(graphicsExit);
		translator.signed32(graphicsWalk);
		translator.float32(spriteRate);
	}
}
