package net.aegistudio.aoe2m.opnagedb;

import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.AssetConnection;
import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import net.aegistudio.aoe2m.assetdba.NullAssetListener;
import net.aegistudio.aoe2m.assetdba.PlayerPalette;
import net.aegistudio.aoe2m.assetdba.unit.UnitGamedata;
import net.aegistudio.aoe2m.media.Storage;

public class OpgAssetConnection implements AssetConnection {
	public OpgAssetConnection(Storage root, AssetListener perfLog) throws IOException {
		perfLog.initDatabase();
		
		palette = new OpgPlayerPalette(perfLog, root);
		blendomatic = new OpgBlendomaticManager(perfLog, root);
		tile = new OpgTileManager(perfLog, root);
		graphics = new OpgGraphicsManager(perfLog, palette, root);
		
		perfLog.readyDatabase();
	}
	
	public OpgAssetConnection(Storage root) throws IOException {
		this(root, new NullAssetListener());
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

	@Override
	public AssetManager<UnitGamedata> unit(int civ) {
		// NOT YET IMPLEMENTED!
		return null;
	}
}
