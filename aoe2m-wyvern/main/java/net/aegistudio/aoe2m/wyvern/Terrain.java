package net.aegistudio.aoe2m.wyvern;

public interface Terrain {
	/**
	 * Retrieve the tile id at specific location.
	 */
	public int tile(int x, int y);
	
	/**
	 * Retrieve the elevation at specific location.
	 */	
	public int elevation(int x, int y);
	
	/**
	 * If the tile is under selection.
	 */
	public boolean selected(int x, int y);
	
	/**
	 * Retrieve the width of the terrain.
	 */
	public int width();
	
	/**
	 * Retrieve the height of the terrain.
	 */	
	public int height();
}