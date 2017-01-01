package net.aegistudio.aoe2m.wyvern.tile;

import java.io.File;
import java.io.IOException;

import net.aegistudio.aoe2m.wyvern.asset.Blendomatic;
import net.aegistudio.aoe2m.wyvern.asset.SlpTextureAsset;
import net.aegistudio.aoe2m.wyvern.asset.TileGamedata;
import net.aegistudio.aoe2m.wyvern.render.ParentTexture;
import net.aegistudio.aoe2m.wyvern.render.Texture;

public class TileMetadata {
	public TileMetadata(File parent, Blendomatic blendomatics, TileGamedata gamedata) throws IOException {
		texture = new SlpTextureAsset(parent, gamedata.slp).toTexture();
		atlassian = gamedata.dimension0;
		overlay = gamedata.overlay;
		blendomatic = blendomatics.getMaskTexture(gamedata.mask);
	}
	
	public final int atlassian;
	public final ParentTexture texture;
	public Texture getTexture(int x, int y) {
		int id = 0;
		id += y % atlassian;
		id *= atlassian;
		id += x % atlassian;
		return texture.get(id);
	}
	
	public final int overlay;			// Overlay order.
	public final ParentTexture blendomatic;
	public boolean shouldOverlay(TileMetadata another) {
		return overlay > another.overlay;
	}
}
