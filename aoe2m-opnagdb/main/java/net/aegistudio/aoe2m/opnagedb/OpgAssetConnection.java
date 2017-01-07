package net.aegistudio.aoe2m.opnagedb;

import java.io.File;
import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.AssetConnection;

public class OpgAssetConnection implements AssetConnection {
	public OpgAssetConnection(File root) throws IOException {
		blendomatic = new OpgBlendomaticManager(root);
		tileAsset = new OpgTileAssetManager(root);
	}
	
	protected OpgBlendomaticManager blendomatic;
	public OpgBlendomaticManager blendomatic() {
		return blendomatic;
	}

	protected OpgTileAssetManager tileAsset;
	public OpgTileAssetManager terrain() {
		return tileAsset;
	}

}
