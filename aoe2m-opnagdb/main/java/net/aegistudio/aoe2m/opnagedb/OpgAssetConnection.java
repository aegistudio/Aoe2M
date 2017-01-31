package net.aegistudio.aoe2m.opnagedb;

import java.io.IOException;

import net.aegistudio.aoe2m.assetdba.AssetConnection;
import net.aegistudio.aoe2m.assetdba.AssetListener;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import net.aegistudio.aoe2m.assetdba.NullAssetListener;
import net.aegistudio.aoe2m.assetdba.PlayerPalette;
import net.aegistudio.aoe2m.assetdba.unit.Civilization;
import net.aegistudio.aoe2m.assetdba.unit.UnitGamedata;
import net.aegistudio.aoe2m.media.Storage;
import net.aegistudio.aoe2m.opnagedb.unit.OpgCivManager;
import net.aegistudio.aoe2m.opnagedb.unit.OpgUnitSubsystem;

public class OpgAssetConnection implements AssetConnection {
	public OpgAssetConnection(Storage root, AssetListener perfLog) throws IOException {
		perfLog.initDatabase();
		
		palette = new OpgPlayerPalette(perfLog, root);
		blendomatic = new OpgBlendomaticManager(perfLog, root);
		tile = new OpgTileManager(perfLog, root);
		graphics = new OpgGraphicsManager(perfLog, palette, root);
		language = new OpgStringManager(perfLog, root);
		civilization = new OpgCivManager(perfLog, root);
		units = new OpgUnitSubsystem(civilization, perfLog, root);
		
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

	protected OpgCivManager civilization;
	public AssetManager<Civilization> civilization(int civ) {
		return civilization;
	}
	
	protected OpgUnitSubsystem units;
	public AssetManager<UnitGamedata> unit(int civ) {
		return units.unit(civ);
	}

	protected OpgStringManager language;
	public AssetManager<String> language(String locale) {
		return language.locale(locale);
	}
}
