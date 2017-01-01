package net.aegistudio.aoe2m.wyvern.asset;

import java.io.IOException;

public class TileGamedata {
	public final String name0, name1, sound, slp;
	public final int mapHi, mapMid, mapLo, mapCliffLeft, mapCliffRight;
	public final int overlay, mask;
	public final int passable, impassable;
	public final String elevationGraphics;
	
	public final int dimension0, dimension1;
	
	// enabled,name0,name1,slp_id,sound_id,blend_priority,blend_mode,
	// map_color_hi,map_color_med,map_color_low,map_color_cliff_lt,map_color_cliff_rt,passable_terrain,
	// impassaable_terrain,elevation_graphics,terrain_dimension0,terrain_dimension1
	public TileGamedata(String line) throws IOException {
		String[] parameters = line.split(",");
		
		name0 = parameters[1];
		name1 = parameters[2];
		
		slp = parameters[3] + ".slp";
		
		sound = parameters[4];
		overlay = Integer.parseInt(parameters[5]);
		mask = Integer.parseInt(parameters[6]);
		
		mapHi = Integer.parseInt(parameters[7]);
		mapMid = Integer.parseInt(parameters[8]);
		mapLo = Integer.parseInt(parameters[9]);
		mapCliffLeft = Integer.parseInt(parameters[10]);
		mapCliffRight = Integer.parseInt(parameters[11]);
		
		passable = Integer.parseInt(parameters[12]);
		impassable = Integer.parseInt(parameters[13]);
		
		elevationGraphics = parameters[14];
		
		dimension0 = Integer.parseInt(parameters[15]);
		dimension1 = Integer.parseInt(parameters[16]);
	}
}
