package net.aegistudio.aoe2m.wyvern.unit;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.SlpSubImage;
import net.aegistudio.aoe2m.wyvern.Terrain;
import net.aegistudio.aoe2m.wyvern.tile.TileOutline;

public abstract class BasicRenderer implements GraphicsRenderer {
	protected final GraphicsManager manager;
	protected final TileOutline outline;
	
	public BasicRenderer(GraphicsManager manager, TileOutline outline) {
		this.manager = manager;
		this.outline = outline;
	}
	
	public static interface VertexBound {
		public void bound() throws LWJGLException;
	}
	
	public void draw(Terrain terrain, GraphicsInstruction instruction) throws LWJGLException {
		// Retrive sprite.
		int id = instruction.sprite;
		GraphicsSprite sprite = manager.require(id);
		if(sprite == null) return;
		
		int index = sprite.whichTexture((int)instruction.frame, (int)instruction.angle);
		SlpSubImage subImage = sprite.subImages[index];
		
		subDraw(terrain, instruction, sprite, index, subImage);
	}
	
	protected void subDraw(Terrain terrain, GraphicsInstruction instruction, GraphicsSprite sprite, 
			int slpTextureIndex, SlpSubImage subImage) throws LWJGLException {
		actualDraw(terrain, instruction, sprite, subImage, 
				() -> {}, () -> {}, () -> {}, () -> {});
	}
	
	public void actualDraw(Terrain terrain, GraphicsInstruction instruction, 
			GraphicsSprite sprite, SlpSubImage subImage,
			VertexBound topLeft, VertexBound topRight, 
			VertexBound bottomRight, VertexBound bottomLeft) throws LWJGLException {
		
		// Model transform to image position.
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glLoadIdentity();
		outline.any((u, v) -> glTranslated(u, v, 0), terrain, 
				instruction.x, instruction.y, instruction.z);
		
		// Retrieve texture information.
		int w = subImage.w;		int h = subImage.h;
		int cx = subImage.cx;	int cy = subImage.cy;
		
		// Translate to image center.
		glTranslated(-cx, -h + cy, 0);
		
		// Actually render.
		glBegin(GL_QUADS);
			topLeft.bound();		glVertex2d(0, 0);
			topRight.bound(); 		glVertex2d(w, 0);
			bottomRight.bound(); 	glVertex2d(w, h);
			bottomLeft.bound(); 	glVertex2d(0, h);
		glEnd();
			
		// Reset model transform matrix.
		glPopMatrix();
	}
}
