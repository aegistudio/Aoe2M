package net.aegistudio.aoe2m.opnagedb;

import java.io.File;
import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.AssetConnection;
import net.aegistudio.aoe2m.assetdba.terrain.BlendomaticManager;
import net.aegistudio.aoe2m.assetdba.terrain.TileAssetManager;

public class OpgAssetConnection implements AssetConnection {
	public OpgAssetConnection(File root) throws IOException {
		blendomatic = new OpgBlendomaticManager(root);
		tileAsset = new OpgTileAssetManager(root);
	}
	
	protected BlendomaticManager blendomatic;
	public BlendomaticManager blendomatic() {
		return blendomatic;
	}

	protected TileAssetManager tileAsset;
	public TileAssetManager terrain() {
		return tileAsset;
	}

}
