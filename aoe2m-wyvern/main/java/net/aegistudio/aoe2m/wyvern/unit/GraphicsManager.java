package net.aegistudio.aoe2m.wyvern.unit;

import net.aegistudio.aoe2m.assetdba.AssetConnection;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;

public class GraphicsManager {
	public final GraphicsSprite[] sprites;
	
	public GraphicsManager(AssetConnection connection) {
		AssetManager<GraphicsGamedata> graphics = connection.graphics();
		sprites = new GraphicsSprite[graphics.max()];
		connection.graphics().iterate(
				(gid, data) -> sprites[gid] = data.slp != null?
						new GraphicsSprite(data) : null);
	}
	
	public GraphicsSprite require(int id) {
		if(id < 0 || id > sprites.length) return null;
		return sprites[id];
	}
}
