package net.aegistudio.aoe2m.opnagedb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiConsumer;

import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.TileGamedata;

public class OpgTileManager implements AssetManager<TileGamedata> {
	protected final TileGamedata[] tiles;
	public OpgTileManager(File root) throws IOException {
		File parent = new File(root, "terrain");
		File terrainGamedata = new File(new File(new File(root, "gamedata"), 
				"gamedata-empiresdat"), "0000-terrains.docx");
		
		try(BufferedReader gamedataReader = new BufferedReader(new FileReader(terrainGamedata))) {
			tiles = gamedataReader.lines()
				.filter(CsvFilter::filter).map(CsvFilter::map)
				.filter(params -> params[0].equals("1"))	// enabled
				.map(FunctionWrapper.mapIgnoreExcept(
						params -> new OpgTileGamedata(parent, params)))
				.toArray(TileGamedata[]::new);
		}	
	}
	
	@Override
	public TileGamedata query(int terrainId) {
		return tiles[terrainId];
	}

	@Override
	public int max() {
		return tiles.length;
	}

	@Override
	public void iterate(BiConsumer<Integer, TileGamedata> iterator) {
		for(int i = 0; i < tiles.length; i ++)
			iterator.accept(i, tiles[i]);
	}
}
