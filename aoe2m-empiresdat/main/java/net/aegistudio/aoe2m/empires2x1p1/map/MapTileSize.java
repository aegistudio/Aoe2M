package net.aegistudio.aoe2m.empires2x1p1.map;

import java.io.IOException;

import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class MapTileSize {
	public Wrapper<Short> width = new Container<>((short)0);	
	
	public Wrapper<Short> height = new Container<>((short)0);	
	
	public Wrapper<Short> deltaZ = new Container<>((short)0);
	
	public void translate(Translator translator) throws IOException {
		translator.signed16(width);
		translator.signed16(height);
		translator.signed16(deltaZ);
	}
}
