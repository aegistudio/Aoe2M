package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class BaseZone {
	public final Wrapper<Integer> uk0 = new Container<>(0);
	
	public final Wrapper<Integer> baseTerrain = new Container<>(0);
	
	public final Wrapper<Integer> playerSpacing = new Container<>(0);
	
	public final Wrapper<Integer> uk1 = new Container<>(0);
	
	public final Wrapper<Integer> uk2 = new Container<>(0);
	
	public final Wrapper<Integer> uk3 = new Container<>(0);
	
	public final Wrapper<Integer> uk4 = new Container<>(0);
	
	public final Wrapper<Integer> uk5 = new Container<>(0);
	
	public final Wrapper<Integer> startAreaRadius = new Container<>(0);
	
	public final Wrapper<Integer> uk6 = new Container<>(0);
	
	public final Wrapper<Integer> uk7 = new Container<>(0);
	
	public void translate(Translator translator) throws IOException {
		translator.signed32(uk0);
		translator.signed32(baseTerrain);
		translator.signed32(playerSpacing);
		translator.signed32(uk1);
		translator.signed32(uk2);
		translator.signed32(uk3);
		translator.signed32(uk4);
		translator.signed32(uk5);
		translator.signed32(startAreaRadius);
		translator.signed32(uk6);
		translator.signed32(uk7);
	}
}
