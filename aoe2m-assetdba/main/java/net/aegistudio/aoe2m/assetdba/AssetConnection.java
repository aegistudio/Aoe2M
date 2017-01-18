package net.aegistudio.aoe2m.assetdba;

import java.awt.Color;

public interface AssetConnection {
	public AssetManager<SlpImage> blendomatic();
	
	public AssetManager<TileGamedata> terrain();
	
	public AssetManager<GraphicsGamedata> graphics();
	
	public Color[] playerPalette();
}
