package net.aegistudio.aoe2m.assetdba;

import java.util.function.Supplier;

public class TileGamedata {
	public String name0, name1, sound;
	public int mapHi, mapMid, mapLo, mapCliffLeft, mapCliffRight;
	public Supplier<SlpImage> slp;
	public int overlay, mask;
	public int passable, impassable;
	public String elevationGraphics;
	
	public int dimension0, dimension1;
}
