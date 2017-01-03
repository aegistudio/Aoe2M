package net.aegistudio.aoe2m.opnagedb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import net.aegistudio.aoe2m.assetdba.terrain.TileAssetManager;
import net.aegistudio.aoe2m.assetdba.terrain.TileGamedata;

public class OpgTileAssetManager implements TileAssetManager {
	protected final TileGamedata[] tiles;
	public OpgTileAssetManager(File root) throws IOException {
		File parent = new File(root, "terrain");
		File terrainGamedata = new File(new File(new File(root, "gamedata"), 
				"gamedata-empiresdat"), "0000-terrains.docx");
		
		try(BufferedReader gamedataReader = new BufferedReader(new FileReader(terrainGamedata))) {
			tiles = gamedataReader.lines()
				.filter(line -> line.length() > 0 && line.startsWith("1,"))
				.map(line -> {
					try {
						return new OpgTileGamedata(parent, line);
					}
					catch(IOException e) {
						e.printStackTrace();
						return null;
					}
				}).toArray(TileGamedata[]::new);
		}	
	}
	
	@Override
	public TileGamedata query(int terrainId) {
		return tiles[terrainId];
	}

	@Override
	public TileGamedata[] list() {
		return Arrays.copyOf(tiles, tiles.length);
	}
}
