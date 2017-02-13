package net.aegistudio.aoe2m.empires2x1p1;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class PlayerColor {
	public final Wrapper<Integer> id = new Container<Integer>(0);
	public final Wrapper<Integer> palette = new Container<Integer>(0);
	public final Wrapper<Integer> color = new Container<Integer>(0);
	public final Wrapper<Integer> uk0 = new Container<Integer>(0);
	public final Wrapper<Integer> uk1 = new Container<Integer>(0);
	public final Wrapper<Integer> minimap = new Container<Integer>(0);
	public final Wrapper<Integer> uk3 = new Container<Integer>(0);
	public final Wrapper<Integer> uk4 = new Container<Integer>(0);
	public final Wrapper<Integer> statistics = new Container<Integer>(0);	
	
	public void translate(Translator translator) throws IOException {
		translator.signed32(id);
		translator.signed32(palette);
		translator.signed32(color);
		translator.signed32(uk0);
		translator.signed32(uk1);
		translator.signed32(minimap);
		translator.signed32(uk3);
		translator.signed32(uk4);
		translator.signed32(statistics);
	}
}
