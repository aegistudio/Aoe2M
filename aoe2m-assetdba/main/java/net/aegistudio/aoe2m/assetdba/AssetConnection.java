package net.aegistudio.aoe2m.assetdba;

public interface AssetConnection {
	public AssetManager<SlpImage> blendomatic();
	
	public AssetManager<TileGamedata> terrain();
	
	public AssetManager<GraphicsGamedata> graphics();
}
