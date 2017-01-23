package net.aegistudio.aoe2m.wyvern.unit;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.render.SlpParentTexture;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class NullRenderer extends BasicRenderer {
	public final TextureManager textureManager;
	public NullRenderer(GraphicsManager manager, TileOutline outline, 
			TextureManager textureManager) {
		super(manager, outline);
		this.textureManager = textureManager;
	}

	@Override
	public void prepare() throws LWJGLException {	}

	@Override
	public void subDraw(Terrain terrain, GraphicsInstruction instruction, 
	GraphicsSprite sprite, int slpTextureIndex, SlpSubImage subImage) throws LWJGLException {
		// Use the texture.
		SlpParentTexture texture = sprite.normalTexture;
		textureManager.bind(texture, TextureBinding.instance);
		SlpTexture slpTexture = texture.get(slpTextureIndex);
		
		// Render actually.
		actualDraw(terrain, instruction, sprite, subImage,
				() -> slpTexture.topLeft(TextureBinding.instance), 
				() -> slpTexture.topRight(TextureBinding.instance), 
				() -> slpTexture.bottomRight(TextureBinding.instance), 
				() -> slpTexture.bottomLeft(TextureBinding.instance));
	}

	@Override
	public void cleanup() throws LWJGLException {	}
}
