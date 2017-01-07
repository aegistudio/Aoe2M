package net.aegistudio.aoe2m.wyvern.asset;

import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.AssetConnection;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.TileGamedata;
import net.aegistudio.aoe2m.wyvern.tile.TileManager;
import net.aegistudio.aoe2m.wyvern.tile.TileMetadata;

public class TileMetaManager implements TileManager {
	private final TileMetadata[] tiles;
	public TileMetaManager(AssetConnection connection, Blendomatic blendomatic) throws IOException {
		AssetManager<TileGamedata> assetManager = connection.terrain();
		tiles = new TileMetadata[assetManager.max()];
		assetManager.iterate((i, asset) -> { try {
			tiles[i] = new TileMetadata(blendomatic, asset);
		} catch (Exception e) {	e.printStackTrace();	}});
	}

	@Override
	public TileMetadata require(int id) {
		if(id < 0 || id >= tiles.length) return null;
		return tiles[id];
	}
}
