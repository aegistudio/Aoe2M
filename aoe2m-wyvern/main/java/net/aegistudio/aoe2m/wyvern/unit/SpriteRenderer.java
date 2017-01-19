package net.aegistudio.aoe2m.wyvern.unit;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class SpriteRenderer extends BasicRenderer implements GraphicsRenderer {
	protected final TextureManager textureManager;
	
	public SpriteRenderer(GraphicsManager manager, TileOutline outline, TextureManager textureManager) {
		super(manager, outline);
		this.textureManager = textureManager;
	}
	
	@Override
	public void prepare() throws LWJGLException {
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void subDraw(Terrain terrain, GraphicsInstruction instruction, GraphicsSprite sprite) throws LWJGLException {
		// Use the texture.
		SlpParentTexture texture = sprite.normalTexture;
		textureManager.bind(texture, TextureBinding.instance);
		
		// Retrieve texture information.
		int index = sprite.whichTexture((int)instruction.frame, (int)instruction.angle);
		SlpTexture slpTexture = texture.get(index);
		
		// Render actually.
		actualDraw(terrain, instruction, sprite,
				() -> slpTexture.topLeft(TextureBinding.instance), 
				() -> slpTexture.topRight(TextureBinding.instance), 
				() -> slpTexture.bottomRight(TextureBinding.instance), 
				() -> slpTexture.bottomLeft(TextureBinding.instance));
	}

	@Override
	public void cleanup() throws LWJGLException {	}
}
