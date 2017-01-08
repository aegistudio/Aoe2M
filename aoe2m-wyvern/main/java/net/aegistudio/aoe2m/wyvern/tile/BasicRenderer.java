package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.Texture;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;

public class BasicRenderer implements TileRenderer {
	public final TextureManager textureManager;
	public final TileManager tile;
	public final TileOutline outline;
	public BasicRenderer(TileManager tileManagger, TextureManager textureManager, TileOutline outline) {
		this.tile = tileManagger;
		this.textureManager = textureManager;
		this.outline = outline;
	}

	public void prepare() throws LWJGLException {	}
	
	@Override
	public void render(Terrain terrain, int x, int y) throws LWJGLException {
		TileMetadata metadata = tile.require(terrain.tile(x, y));
		if(metadata == null) return;
		
		textureManager.bind(metadata.texture, TextureBinding.instance);
		
		Texture unknownTexture = metadata.getTexture(x, y);
		SlpTexture texture = (SlpTexture) unknownTexture;
		
		if(unknownTexture == null) return;
		if(!(unknownTexture instanceof SlpTexture)) return;
		
		GL11.glBegin(GL11.GL_QUADS);
			texture.left(TextureBinding.instance); 
			outline.left(GL11::glVertex2d, terrain, x, y);
			
			texture.bottom(TextureBinding.instance);
			outline.bottom(GL11::glVertex2d, terrain, x, y);
			
			texture.right(TextureBinding.instance);
			outline.right(GL11::glVertex2d, terrain, x, y);
			
			texture.top(TextureBinding.instance);
			outline.top(GL11::glVertex2d, terrain, x, y);
		GL11.glEnd();
	}

	@Override
	public void cleanup() throws LWJGLException {
		
	}
}
