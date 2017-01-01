package net.aegistudio.aoe2m.wyvern.asset;

import java.io.File;
import java.io.IOException;

import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;
import net.aegistudio.aoe2m.wyvern.tile.TileManager;
import net.aegistudio.aoe2m.wyvern.tile.TileMetadata;

public class TileAssetManager implements TileManager {
	private final TileMetadata[] tiles;
	public TileAssetManager(int maxTile) throws IOException {
		tiles = new TileMetadata[maxTile];
		File parent = new File(new File("assets"), "terrain");
		for(int i = 0; i < maxTile; i ++) try {
			int idx = 15000 + i;
			SlpTextureAsset asset = new SlpTextureAsset(parent, Integer.toString(idx) + ".slp");
			
			tiles[i] = new TileMetadata();
			tiles[i].texture = new SlpParentTexture(asset, asset.descriptor());
			tiles[i].atlasian = (int) Math.sqrt(tiles[i].texture.count());
		}
		catch(IOException e) {
			continue;
		}
	}

	@Override
	public TileMetadata require(int id) {
		if(id < 0 || id >= tiles.length) return null;
		return tiles[id];
	}
}
