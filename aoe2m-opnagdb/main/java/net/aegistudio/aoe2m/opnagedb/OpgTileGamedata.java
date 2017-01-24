package net.aegistudio.aoe2m.opnagedb;

import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.TileGamedata;
import net.aegistudio.aoe2m.media.Storage;

import static net.aegistudio.aoe2m.assetdba.AssetConnection.*;

public class OpgTileGamedata extends TileGamedata {
	// enabled,name0,name1,slp_id,sound_id,blend_priority,blend_mode,
	// map_color_hi,map_color_med,map_color_low,map_color_cliff_lt,map_color_cliff_rt,passable_terrain,
	// impassaable_terrain,elevation_graphics,terrain_dimension0,terrain_dimension1
	public OpgTileGamedata(AssetListener perfLog, Storage terrainRoot, String[] parameters) throws IOException {
		int slpIndex = Integer.parseInt(parameters[3]);
		perfLog.initAsset(TILE_NAME, TILE_CLASS, slpIndex);
		
		name0 = parameters[1];
		name1 = parameters[2];
		
		slp = OpgSlpImage.open(
				() -> perfLog.initArchive(TILE_NAME, TILE_CLASS, slpIndex), 
				() -> perfLog.readyArchive(TILE_NAME, TILE_CLASS, slpIndex),
				terrainRoot, slpIndex + ".slp");
		
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
		
		perfLog.readyAsset(TILE_NAME, TILE_CLASS, slpIndex);
	}
}
