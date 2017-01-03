package net.aegistudio.aoe2m.assetdba.terrain;

public interface TileAssetManager {
	public TileGamedata query(int terrainId);
	
	public TileGamedata[] list();
}
