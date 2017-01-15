package net.aegistudio.aoe2m.wyvern.unit;

import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;

public class GraphicsSprite {
	protected final GraphicsGamedata gamedata;
	protected final SlpParentTexture texture;
	
	public GraphicsSprite(GraphicsGamedata gamedata) {
		this.gamedata = gamedata;
		this.texture = new SlpParentTexture(gamedata.slp);
	}
	
	public int whichTexture(int frame, int angle) {
		return ((frame % gamedata.frameCount) + gamedata.frameCount
				* (angle % gamedata.angleCount)) % texture.count();
	}
}