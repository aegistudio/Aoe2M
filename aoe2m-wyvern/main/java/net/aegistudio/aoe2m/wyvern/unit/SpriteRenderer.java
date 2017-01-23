package net.aegistudio.aoe2m.wyvern.unit;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.asset.SpriteShaderProgram;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureBufferObject;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class SpriteRenderer extends BasicRenderer implements GraphicsRenderer {
	protected final SpriteShaderProgram spriteProgram;
	protected final TextureBufferObject profileTexture;
	protected final Arbitrator arbitrator;
	protected final TextureManager textureManager;
	
	public SpriteRenderer(SpriteShaderProgram spriteProgram, 
			TextureBufferObject profileTexture, Arbitrator arbitrator,
			GraphicsManager manager, TileOutline outline, TextureManager textureManager) {
		super(manager, outline);
		this.textureManager = textureManager;
		this.profileTexture = profileTexture;
		this.arbitrator = arbitrator;
		this.spriteProgram = spriteProgram;
	}
	
	@Override
	public void prepare() throws LWJGLException {
		spriteProgram.use();
	}
	
	public void subDraw(Terrain terrain, GraphicsInstruction instruction, 
			GraphicsSprite sprite, int slpTextureIndex, SlpSubImage subImage) throws LWJGLException {
		
		// Use the texture.
		SlpParentTexture texture = sprite.normalTexture;
		textureManager.bind(texture, spriteProgram.normal);
		SlpTexture slpTexture = texture.get(slpTextureIndex);
		
		// Use the profile.
		textureManager.bind(profileTexture, 
				spriteProgram.priorityMap.map);
		
		// Set priority.
		arbitrator.priority(spriteProgram.priority, terrain, instruction);
		
		// Render actually.
		actualDraw(terrain, instruction, sprite, subImage,
				() -> slpTexture.topLeft(spriteProgram.normal), 
				() -> slpTexture.topRight(spriteProgram.normal), 
				() -> slpTexture.bottomRight(spriteProgram.normal), 
				() -> slpTexture.bottomLeft(spriteProgram.normal));
	}

	@Override
	public void cleanup() throws LWJGLException {
		spriteProgram.unuse();
	}
}
