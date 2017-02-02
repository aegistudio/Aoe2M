package net.aegistudio.aoe2m.empires2x1p1.terrain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Wrapper;

public class TerrainBlob {
	public List<Wrapper<Byte>> blob0 = new ArrayList<>();
	
	public List<Wrapper<Integer>> blob1 = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void translate(FieldTranslator translator) throws IOException, CorruptionException {
		translator.array(21, blob0, Container::byte0, translator::signed8);
		translator.array(157, blob1, Container::int0, translator::signed32);
	}
}
