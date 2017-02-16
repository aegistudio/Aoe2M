package net.aegistudio.aoe2m.empires2x1p1.terrain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class TerrainBorder {
	public Wrapper<Short> enabled = new Container<>((short)0);
	
	public Wrapper<String> name0 = new Container<>("");
	
	public Wrapper<String> name1 = new Container<>("");
	
	public Wrapper<Integer> resource = new Container<>(0);
	
	public Wrapper<Integer> uk0 = new Container<>(0);
	
	public Wrapper<Integer> uk1 = new Container<>(0);
	
	public Wrapper<Byte> color0 = new Container<>((byte)0);
	
	public Wrapper<Byte> color1 = new Container<>((byte)0);
	
	public Wrapper<Byte> color2 = new Container<>((byte)0);
	
	public Wrapper<Byte> uk2 = new Container<>((byte)0);
	
	public Wrapper<Integer> uk3 = new Container<>(0);
	
	public Wrapper<Integer> uk4 = new Container<>(0);
	
	public List<BorderFrameData> frames = new ArrayList<>();
	
	public Wrapper<Short> frameCount = new Container<>((short)0);
	
	public Wrapper<Short> uk5 = new Container<>((short)0);
	
	public Wrapper<Short> uk6 = new Container<>((short)0);
	
	public Wrapper<Short> uk7 = new Container<>((short)0);
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.signed16(enabled);
		translator.string(13, name0);
		translator.string(13, name1);
		
		translator.signed32(resource);
		translator.signed32(uk0);
		translator.signed32(uk1);
		
		translator.signed8(color0);
		translator.signed8(color1);
		translator.signed8(color2);
		translator.signed8(uk2);
		translator.signed32(uk3);
		translator.signed32(uk4);
		
		translator.array(230, frames, BorderFrameData::new, 
				BorderFrameData::translate);
		translator.signed16(frameCount);
		translator.signed16(uk5);
		translator.signed16(uk6);
		translator.signed16(uk7);
	}
}
