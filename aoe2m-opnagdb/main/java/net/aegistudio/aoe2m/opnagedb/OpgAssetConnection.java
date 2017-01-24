package net.aegistudio.aoe2m.opnagedb;

import java.io.File;
import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.AssetConnection;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import net.aegistudio.aoe2m.assetdba.PlayerPalette;

public class OpgAssetConnection implements AssetConnection {
	public OpgAssetConnection(File root) throws IOException {
		palette = new OpgPlayerPalette(root);
		blendomatic = new OpgBlendomaticManager(root);
		tile = new OpgTileManager(root);
		graphics = new OpgGraphicsManager(palette, root);
	}
	
	protected OpgBlendomaticManager blendomatic;
	public OpgBlendomaticManager blendomatic() {
		return blendomatic;
	}

	protected OpgTileManager tile;
	public OpgTileManager terrain() {
		return tile;
	}
	
	protected OpgGraphicsManager graphics;
	public AssetManager<GraphicsGamedata> graphics() {
		return graphics;
	}
	
	protected OpgPlayerPalette palette;
	public PlayerPalette playerPalette() {
		return palette;
	}
}
