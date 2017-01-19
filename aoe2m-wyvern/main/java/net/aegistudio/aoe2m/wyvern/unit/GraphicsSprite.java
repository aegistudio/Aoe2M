package net.aegistudio.aoe2m.wyvern.unit;

import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;

public class GraphicsSprite {
	protected final GraphicsGamedata gamedata;
	protected final SlpSubImage[] subImages;
	protected final SlpParentTexture normalTexture;
	protected final SlpParentTexture playerTexture;
	protected final SlpParentTexture obstructTexture;
	
	public GraphicsSprite(GraphicsGamedata gamedata) {
		this.gamedata = gamedata;
		subImages = gamedata.slp.subTextures();
		normalTexture 	= new SlpParentTexture(gamedata.slp, img -> img.normal());
		playerTexture 	= new SlpParentTexture(gamedata.slp, img -> img.player());
		obstructTexture = new SlpParentTexture(gamedata.slp, img -> img.obstruct());
	}
	
	public int whichTexture(int frame, int angle) {
		return ((frame % gamedata.frameCount) + gamedata.frameCount
				* (angle % gamedata.angleCount)) % subImages.length;
	}
}