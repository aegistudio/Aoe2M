package net.aegistudio.aoe2m.wyvern.asset;

import java.io.IOException;
import java.util.Arrays;

import net.aegistudio.aoe2m.assetdba.terrain.TileAssetManager;
import net.aegistudio.aoe2m.wyvern.tile.TileManager;
import net.aegistudio.aoe2m.wyvern.tile.TileMetadata;

public class TileMetaManager implements TileManager {
	private final TileMetadata[] tiles;
	public TileMetaManager(TileAssetManager assetManager, Blendomatic blendomatic) throws IOException {
		tiles =	Arrays.stream(assetManager.list())
				.map(asset -> { try {
						return new TileMetadata(blendomatic, asset);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}})
				.toArray(TileMetadata[]::new);
	}

	@Override
	public TileMetadata require(int id) {
		if(id < 0 || id >= tiles.length) return null;
		return tiles[id];
	}
}
