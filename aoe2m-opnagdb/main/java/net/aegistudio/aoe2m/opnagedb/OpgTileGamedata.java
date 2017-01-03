package net.aegistudio.aoe2m.opnagedb;

import java.io.File;
import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.blob.SlpImage;
import net.aegistudio.aoe2m.assetdba.terrain.TileGamedata;

public class OpgTileGamedata extends TileGamedata {
	protected final SlpImage slp;
	
	// enabled,name0,name1,slp_id,sound_id,blend_priority,blend_mode,
	// map_color_hi,map_color_med,map_color_low,map_color_cliff_lt,map_color_cliff_rt,passable_terrain,
	// impassaable_terrain,elevation_graphics,terrain_dimension0,terrain_dimension1
	public OpgTileGamedata(File terrainRoot, String line) throws IOException {
		String[] parameters = line.split(",");
		
		name0 = parameters[1];
		name1 = parameters[2];
		
		slp = new OpgSlpImage(terrainRoot, parameters[3] + ".slp");
		
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

	@Override
	public SlpImage open() {
		return slp;
	}
}
