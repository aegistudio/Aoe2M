package net.aegistudio.aoe2m.empires2x1p1.terrain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.CorruptException;
import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public class TerrainBlob {
	public List<Wrapper<Byte>> blob0 = new ArrayList<>();
	
	public List<Wrapper<Integer>> blob1 = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(Translator translator) throws IOException, CorruptException {
		translator.array(21, blob0, Container::byte0, 
				Translator.reverse(Translator::signed8));
		translator.array(157, blob1, Container::int0, 
				Translator.reverse(Translator::signed32));
	}
}
