package net.aegistudio.aoe2m.assetdba;

import java.util.function.BiConsumer;

public interface AssetManager<G> {
	public int max();
	
	public G query(int id);
	
	public void iterate(BiConsumer<Integer, G> iterator);
}
