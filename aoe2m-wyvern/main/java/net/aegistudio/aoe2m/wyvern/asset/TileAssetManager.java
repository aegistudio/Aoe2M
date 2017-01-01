package net.aegistudio.aoe2m.wyvern.asset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.aegistudio.aoe2m.wyvern.tile.TileManager;
import net.aegistudio.aoe2m.wyvern.tile.TileMetadata;

public class TileAssetManager implements TileManager {
	private final TileMetadata[] tiles;
	public TileAssetManager(Blendomatic blendomatic) throws IOException {
		File asset = new File("assets");
		File parent = new File(asset, "terrain");
		File terrainGamedata = new File(new File(new File(asset, "gamedata"), 
				"gamedata-empiresdat"), "0000-terrains.docx");
		
		try(BufferedReader gamedataReader = new BufferedReader(new FileReader(terrainGamedata))) {
			tiles = gamedataReader.lines()
					.filter(line -> line.length() > 0 && line.startsWith("1,"))
					.map(line -> {
						try {
							TileGamedata tileGamedata = new TileGamedata(line);
							TileMetadata tileItem = new TileMetadata(parent, blendomatic, tileGamedata);
							return tileItem;
						}
						catch(IOException e) {
							e.printStackTrace();
							return null;
						}
					}).toArray(TileMetadata[]::new);
		}
	}

	@Override
	public TileMetadata require(int id) {
		if(id < 0 || id >= tiles.length) return null;
		return tiles[id];
	}
}
