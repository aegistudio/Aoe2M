package net.aegistudio.aoe2m.empires2x1p1.restriction;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class RestrictionSprites {
	public final Wrapper<Integer> graphicsEnter = new Container<Integer>(-1);
	public final Wrapper<Integer> graphicsExit = new Container<Integer>(-1);
	public final Wrapper<Integer> graphicsWalk = new Container<Integer>(-1);
	public final Wrapper<Float> spriteRate = new Container<Float>(0.0f);
	
	public void translate(FieldTranslator translator) throws IOException {
		translator.signed32(graphicsEnter);
		translator.signed32(graphicsExit);
		translator.signed32(graphicsWalk);
		translator.float32(spriteRate);
	}
}
