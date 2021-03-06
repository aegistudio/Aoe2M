package net.aegistudio.aoe2m.assetdba;

import net.aegistudio.aoe2m.assetdba.unit.Civilization;
import net.aegistudio.aoe2m.assetdba.unit.UnitGamedata;

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
	
	public static final String UNIT_NAME = "unit";
	public static final Class<?> UNIT_CLASS = UnitGamedata.class;
	public AssetManager<UnitGamedata> unit(int civ);

	public static final String CIV_NAME = "civilization";
	public static final Class<?> CIV_CLASS = Civilization.class;
	public AssetManager<Civilization> civilization(int civ);
	
	public static final String LANGUAGE_NAME = "language";
	public static final Class<?> LANGUAGE_CLASS = String.class;
	public AssetManager<String> language(String locale);
}
