package net.aegistudio.aoe2m.wyvern.tile;

import net.aegistudio.aoe2m.wyvern.render.ParentTexture;
import net.aegistudio.aoe2m.wyvern.render.Texture;

public class TileMetadata {
	public int atlasian;
	public ParentTexture texture;
	public Texture getTexture(int x, int y) {
		int id = 0;
		id += y % atlasian;
		id *= atlasian;
		id += x % atlasian;
		return texture.get(id);
	}
	
	public int overlay;			// Overlay order.
	public boolean shouldOverlay(TileMetadata another) {
		return overlay > another.overlay;
	}
	
	public int influence;		// Influence level.
	
	public ParentTexture blendomatic;
}
