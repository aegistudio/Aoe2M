package net.aegistudio.aoe2m.wyvern.unit;

import net.aegistudio.aoe2m.assetdba.AssetConnection;
import net.aegistudio.aoe2m.assetdba.AssetManager;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;

public class GraphicsManager {
	public final AssetManager<GraphicsGamedata> graphics;
	public final GraphicsSprite[] sprites;
	
	public GraphicsManager(AssetConnection connection) {
		graphics = connection.graphics();
		sprites = new GraphicsSprite[graphics.max()];
	}
	
	public GraphicsSprite require(int id) {
		if(id < 0 || id > sprites.length) return null;
		if(sprites[id] == null) {
			GraphicsGamedata data = graphics.query(id);
			if(data.slp != null)
				sprites[id] = new GraphicsSprite(data);
		}
		return sprites[id];
	}
}
