package net.aegistudio.aoe2m.assetdba.terrain;

import net.aegistudio.aoe2m.assetdba.blob.SlpImage;

public abstract class TileGamedata {
	public String name0, name1, sound;
	public int mapHi, mapMid, mapLo, mapCliffLeft, mapCliffRight;
	public int overlay, mask;
	public int passable, impassable;
	public String elevationGraphics;
	
	public int dimension0, dimension1;
	
	public abstract SlpImage open();
}
