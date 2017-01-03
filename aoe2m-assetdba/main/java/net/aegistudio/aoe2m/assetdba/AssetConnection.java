package net.aegistudio.aoe2m.assetdba;

import net.aegistudio.aoe2m.assetdba.terrain.BlendomaticManager;
import net.aegistudio.aoe2m.assetdba.terrain.TileAssetManager;

public interface AssetConnection {
	public BlendomaticManager blendomatic();
	
	public TileAssetManager terrain();
}
