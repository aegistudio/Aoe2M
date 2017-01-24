package net.aegistudio.aoe2m.assetdba;

public interface AssetConnection {
	public static final String BLENDOMATIC_NAME = "blendomatic";
	public static final Class<?> BLENDOMATIC_CLASS = SlpImage.class;
	public AssetManager<SlpImage> blendomatic();
	
	public static final String TILE_NAME = "terrain";
	public static final Class<?> TILE_CLASS = TileGamedata.class;
	public AssetManager<TileGamedata> terrain();
	
	public static final String GRAPHICS_NAME = "graphics";
	public static final Class<?> GRAPHICS_CLASS = GraphicsGamedata.class;
	public AssetManager<GraphicsGamedata> graphics();
	
	public PlayerPalette playerPalette();
}
