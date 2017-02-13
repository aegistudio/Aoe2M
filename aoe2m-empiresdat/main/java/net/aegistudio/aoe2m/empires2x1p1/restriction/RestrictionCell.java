package net.aegistudio.aoe2m.empires2x1p1.restriction;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class RestrictionCell {
	public final Wrapper<Float> accessible = new Container<>(0f);
	
	public final Wrapper<Integer> graphicsEnter = new Container<Integer>(-1);
	public final Wrapper<Integer> graphicsExit = new Container<Integer>(-1);
	public final Wrapper<Integer> graphicsWalk = new Container<Integer>(-1);
	public final Wrapper<Float> spriteRate = new Container<Float>(0f);
	
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
