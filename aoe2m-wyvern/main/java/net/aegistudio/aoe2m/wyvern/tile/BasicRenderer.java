package net.aegistudio.aoe2m.wyvern.tile;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.render.Texture;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.terrain.Terrain;

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
		Texture texture = metadata.getTexture(x, y);
		textureManager.bind(metadata.texture, TextureBinding.instance);
		
		GL11.glBegin(GL11.GL_QUADS);
			texture.bottomLeft(TextureBinding.instance); 
			outline.bottomLeft(GL11::glVertex2d, x, y);
			
			texture.bottomRight(TextureBinding.instance);
			outline.bottomRight(GL11::glVertex2d, x, y);
			
			texture.topRight(TextureBinding.instance);
			outline.topRight(GL11::glVertex2d, x, y);
			
			texture.topLeft(TextureBinding.instance);
			outline.topLeft(GL11::glVertex2d, x, y);
		GL11.glEnd();
	}
}
