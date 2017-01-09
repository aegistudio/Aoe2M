package net.aegistudio.aoe2m.wyvern.unit;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureBinding;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public class SpriteRenderer implements GraphicsRenderer {
	protected final GraphicsManager graphicsManager;
	protected final TileOutline outline;
	protected final TextureManager textureManager;
	
	public SpriteRenderer(TileOutline outline, GraphicsManager graphicsManager,
			TextureManager textureManager) {
		this.outline = outline;
		this.graphicsManager = graphicsManager;
		this.textureManager = textureManager;
	}
	
	@Override
	public void prepare() throws LWJGLException {
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void draw(Terrain terrain, double x, double y, double z, 
			int graphics, int frame, int angle) throws LWJGLException {
		// Retrive sprite.
		GraphicsSprite sprite = this.graphicsManager.require(graphics);
		if(sprite == null) return;
		
		// Model transform to image position.
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glLoadIdentity();
		outline.any((u, v) -> glTranslated(u, v, 0), terrain, x, y, z);
		
		// Use the texture.
		textureManager.bind(sprite.texture, TextureBinding.instance);
		
		// Retrieve texture information.
		int index = sprite.whichTexture(frame, angle);
		SlpTexture slpTexture = sprite.texture.get(index);
		SlpSubImage subImage = sprite.gamedata.slp.subTextures()[index];
		
		// Retrieve texture information.
		int w = subImage.w;		int h = subImage.h;
		int cx = subImage.cx;	int cy = subImage.cy;
		
		// Translate to image center.
		glTranslated(-cx, -h + cy, 0);
		
		// Actually render.
		glBegin(GL_QUADS);
			slpTexture.topLeft(TextureBinding.instance);		glVertex2d(0, 0);
			slpTexture.topRight(TextureBinding.instance); 		glVertex2d(w, 0);
			slpTexture.bottomRight(TextureBinding.instance); 	glVertex2d(w, h);
			slpTexture.bottomLeft(TextureBinding.instance); 	glVertex2d(0, h);
		glEnd();
			
		// Reset model transform matrix.
		glPopMatrix();
	}

	@Override
	public void cleanup() throws LWJGLException {	}
}
